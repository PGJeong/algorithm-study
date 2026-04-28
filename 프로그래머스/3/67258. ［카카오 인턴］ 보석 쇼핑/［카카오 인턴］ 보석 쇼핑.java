import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 1. 보석 종류 개수 확인
        HashSet<String> set = new HashSet<>();
        
        for (String s : gems) {
            set.add(s);
        }
        
        int type = set.size(); // 보석 종류 개수
        
        // 2. 투포인터 탐색
        int[] seq = new int[2];      // 가장 짧은 구간 (0-based)
        int len = Integer.MAX_VALUE; // 구간 길이
        
        HashMap<String, Integer> map = new HashMap<>();
        map.put(gems[0], 1);
        int s = 0;
        int e = 0;
        
        while (s <= e && e < gems.length) {
            if (map.size() >= type) {
                // 모든 종류의 보석이 포함되면
                // 구간 갱신
                if (e - s + 1 < len) {
                    len = e - s + 1;
                    seq[0] = s;
                    seq[1] = e;
                }
                
                // map에서 gems[s] 보석 1개 감소
                map.put(gems[s], map.get(gems[s]) - 1);
                
                if (map.get(gems[s]) <= 0) {
                    map.remove(gems[s]);
                }
                
                s++;
                
            } else {
                // 모든 종류의 보석이 포함되지 않으면
                e++;
                
                // map에서 gems[e] 보석 1개 증가
                if (e < gems.length && map.containsKey(gems[e])) {
                    map.put(gems[e], map.get(gems[e]) + 1);
                    
                } else if (e < gems.length) {
                    map.put(gems[e], 1);
                }
            }
        }
        
        // 1-based 변환
        seq[0]++;
        seq[1]++;
        
        return seq;
    }
}
