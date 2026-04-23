import java.util.*;

class Solution {
    // 시간 내 n명의 사람을 심사할 수 있는지 여부
    boolean check(int n, long time, int[] times) {
        long cnt = 0;
        
        for (int t : times) {
            cnt += time / t;
        }
        
        if (cnt < n) return false;
        else return true;
    }
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long min = 0;
        long max = (long) times[times.length - 1] * n;
        
        while (min <= max) {
            long mid = (min + max) / 2;
            
            if (check(n, mid, times)) {
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
