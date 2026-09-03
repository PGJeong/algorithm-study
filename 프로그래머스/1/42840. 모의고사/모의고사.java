class Solution {
    public int[] solution(int[] answers) {
        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] cnt = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
            cnt[0] += (answers[i] == pattern1[i % pattern1.length]) ? 1 : 0;
            cnt[1] += (answers[i] == pattern2[i % pattern2.length]) ? 1 : 0;
            cnt[2] += (answers[i] == pattern3[i % pattern3.length]) ? 1 : 0;
        }
        
        int max = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
        int len = 0;
        
        for (int i = 0; i < 3; i++) {
            if (cnt[i] == max) len++;
        }
        
        int[] ans = new int[len];
        int idx = 0;
        
        for (int i = 0; i < 3; i++) {
            if (cnt[i] == max) {
                ans[idx] = i + 1;
                idx++;
            }
        }
     
        return ans;
    }
}
