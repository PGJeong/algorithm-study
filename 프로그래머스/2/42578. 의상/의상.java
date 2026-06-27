import java.util.*;

class Solution {
    int[] count;
    int answer = 0;
    
    void dfs(int sum, int index) {
        if (index == count.length) {
            answer += sum;
            return;
        }
        
        dfs(sum * count[index], index + 1);
        dfs(sum, index + 1);
    }
    
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String type = cloth[1];
            
            if (map.containsKey(type)) map.put(type, map.get(type) + 1);
            else map.put(type, 1);
        }
        
        count = new int[map.size()];
        int index = 0;
        
        for (String key : map.keySet()) {
            count[index] = map.get(key);
            index++;
        }
        
        dfs(1, 0);
        return answer - 1; // 모두 선택 안하는 경우 제외
    }
}
