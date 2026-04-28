class Solution {
    public int[] solution(int[] sequence, int k) {
        // 1. 부분합 배열 생성
        int[] psum = new int[sequence.length + 1];
        
        for (int i = 1; i < psum.length; i++) {
            psum[i] = psum[i - 1] + sequence[i - 1];
        }
        
        // 2. 투포인터 탐색
        int s = 1; // 시작 인덱스(1-based)
        int e = 1; // 종료 인덱스(1-based)
        
        int[] seq = null; // 부분수열 index(0-based)
        int len = Integer.MAX_VALUE; // 부분수열 길이
        
        while (s <= e && e < psum.length) {
            int sum = psum[e] - psum[s - 1];
            
            if (sum == k) {
                // 부분수열 합이 k이면
                if ((e - s + 1) < len) {
                    seq = new int[] {s - 1, e - 1}; // 0-based 변환
                    len = e - s + 1;
                }
                e++;
 
            } else if (sum < k) {
                // 부분수열 합이 k보다 작으면
                e++;
                
            } else {
                // 부분수열 합이 k보다 크면
                s++;
            }
        }
        
        return seq;
    }
}
