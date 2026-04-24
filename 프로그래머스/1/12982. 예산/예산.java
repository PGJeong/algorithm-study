import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        
        int min = 0;
        int max = d.length;
        int answer = -1;
        
        while (min <= max) {
            int mid = min + (max - min) / 2;
            
            int bud = budget;
            int cnt = 0;
            
            for (int req : d) {
                if (bud - req >= 0) {
                    bud -= req;
                    cnt++;
                }
                
                if (cnt >= mid) {
                    break;
                }
            }
            
            if (cnt >= mid) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }
        
        return answer;
    }
}
