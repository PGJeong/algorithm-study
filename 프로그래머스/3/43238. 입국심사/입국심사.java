import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long answer = 0;
        long min = 0;
        long max = (long) times[times.length - 1] * n;
        
        while (min <= max) {
            long mid = (min + max) / 2;
            
            long cnt = 0; // mid 시간 내에 처리할 수 있는 사람 수
            
            for (int t : times) {
                cnt += mid / t;
            }
            
            if (cnt >= n) {
                // mid 시간에 n명의 사람을 처리할 수 있으면
                answer = mid;
                max = mid - 1;
                
            } else {
                // mid 시간에 n명의 사람을 처리할 수 없으면
                min = mid + 1;
            }
        }
        
        return answer;
    }
}
