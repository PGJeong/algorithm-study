import java.util.*;

class Solution {  
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        
        int count = 1;
        
        for (int n : map.values()) {
            count *= n + 1; // 해당 종류를 안입는 경우 추가
        }
        
        return count - 1; // 모두 안입는 경우 제외
    }
}
