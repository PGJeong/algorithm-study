import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); // (숫자, 개수) 쌍
        
        for (String op : operations) {
            if (op.startsWith("I ")) {
                // 삽입
                int num = Integer.parseInt(op.substring(2));
                map.put(num, map.getOrDefault(num, 0) + 1);
                
            } else if (!map.isEmpty() && op.equals("D 1")) {
                // 최댓값 삭제
                int max = map.lastKey();
                map.put(max, map.get(max) - 1);
                
                if (map.get(max) <= 0) map.remove(max);
                
            } else if (!map.isEmpty() && op.equals("D -1")) {
                // 최솟값 삭제
                int min = map.firstKey();
                map.put(min, map.get(min) - 1);
                
                if (map.get(min) <= 0) map.remove(min);
            }
        }
        
        if (map.isEmpty()) return new int[] {0, 0};
        else return new int[] {map.lastKey(), map.firstKey()};
    }
}
