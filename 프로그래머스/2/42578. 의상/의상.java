import java.util.*;

class Solution {  
    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String type = cloth[1];
            
            if (map.containsKey(type)) map.put(type, map.get(type) + 1);
            else map.put(type, 1);
        }
        
        int count = 1;
        
        for (String type : map.keySet()) {
            count *= map.get(type) + 1; // 해당 종류를 안입는 경우 추가
        }
        
        return count - 1; // 모두 안입는 경우 제외
    }
}
