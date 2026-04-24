import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int min = 0;
        int max = distance;
        int answer = -1;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            
            // mid가 거리의 최솟값이 가능한지 check
            int cnt = 0;    // mid가 최솟값이 되기 위해 제거해야 하는 바위 수
            int prev = 0;   // 이전 바위 지점(위치)
            
            for (int r : rocks) {
                if (r - prev < mid) {
                    cnt++;
                } else {
                    prev = r;
                }
                
                if (cnt > n) {
                    break;
                }
            }
            
            if (distance - prev < mid) {
                // 마지막 바위 - 도착지점도 계산해야
                cnt++;
            }
            
            // 이분탐색
            if (cnt > n) {
                // mid가 최솟값이 될 수 없으면
                max = mid - 1;
                
            } else {
                // mid가 최솟값이 될 수 있으면
                answer = mid;
                min = mid + 1;
            }
        }
        
        return answer;
    }
}
