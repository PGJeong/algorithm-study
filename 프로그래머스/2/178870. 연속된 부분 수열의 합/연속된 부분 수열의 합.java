class Solution {
    public int[] solution(int[] sequence, int k) {
        int s = 0;
        int e = 0;
        int sum = sequence[0];
        
        int[] seq = new int[2];
        int len = Integer.MAX_VALUE;
        
        while (s <= e && e < sequence.length) {
            if (sum == k) {
                if (e - s + 1 < len) {
                    seq[0] = s;
                    seq[1] = e;
                    len = e - s + 1;
                }
                e++;
                if (e < sequence.length) sum += sequence[e];
                
            } else if (sum < k) {
                e++;
                if (e < sequence.length) sum += sequence[e];
                
            } else {
                s++;
                sum -= sequence[s - 1];
            }
        }
        
        return seq;
    }
}
