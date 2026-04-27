import java.util.*;

class Solution {
    HashMap<String, ArrayList<Integer>> map;
    
    // 조합 생성용 함수
    void dfs(String[] data, String key, int depth) {
        if (depth == 4) {
            if (!map.containsKey(key)) {
                // 새로운 조합이면 새 객체 생성
                map.put(key, new ArrayList<>());
            }
            
            map.get(key).add(Integer.parseInt(data[4]));
            return;
        }
        
        dfs(data, key + data[depth], depth + 1);
        dfs(data, key + "-", depth + 1); // 조건을 고려하지 않는 경우
    }
        
    public int[] solution(String[] info, String[] query) {
        // 1. 조건 조합(16가지) 만들기
        map = new HashMap<>();
        
        for (String s : info) {
            String[] data = s.split(" ");
            dfs(data, "", 0);
        }
        
        // 2. 각 조건 내 ArrayList 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        // 3. query 기반 이분탐색
        int[] result = new int[query.length];
        
        for (int i = 0; i < query.length; i++) {
            // query에서 key와 score 추출
            String[] q = query[i].split(" and | ");  // " and ", " " 기준으로 split
            
            StringBuilder key = new StringBuilder(); // key
            int score = Integer.parseInt(q[4]);      // score
            
            for (int j = 0; j < 4; j++) {
                key.append(q[j]);
            }
            
            // key, score 기반 이분탐색
            ArrayList<Integer> list = map.get(key.toString()); // 디버깅: toString() 사용 필수
            
            if (list == null) {
                // 존재하지 않는 key(조합)인 경우
                result[i] = 0;
                continue;
            }
            
            int min = 0;
            int max = list.size() - 1;
            int index = -1;
            
            while (min <= max) {
                int mid = min + (max - min) / 2;
                
                if (list.get(mid) >= score) {
                    // mid 위치의 값이 score 이상이면
                    index = mid;
                    max = mid - 1;
                    
                } else {
                    // mid 위치의 값이 score 미만이면
                    min = mid + 1;
                }
            }
            
            // key를 만족하고 score 이상인 지원자 수
            if (index == -1) result[i] = 0;
            else result[i] = list.size() - index;
        }
        
        return result;
    }
}
