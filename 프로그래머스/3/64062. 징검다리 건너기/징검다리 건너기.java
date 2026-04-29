class Solution {
    public int solution(int[] stones, int k) {
        // 이분탐색
        int min = 0;
        int max = 200000000;
        int result = -1;
        
        while (min <= max) {
            int mid = min + (max - min) / 2;
            
            if (check(stones, k, mid)) {
                result = mid;
                min = mid + 1;
                
            } else {
                max = mid - 1;
            }
        }
        
        return result;
    }
    
    // p명의 사람이 건널 수 있는지 확인하는 함수
    boolean check(int[] stones, int k, int p) {
        int cnt = 0;
        
        for (int s : stones) {
            if (s - p < 0) {
                cnt++;
                if (cnt >= k) return false; // k개 이상을 건너뛰어야 하면
                
            } else {
                cnt = 0;
            }
        }
        
        return true;
    }
}
