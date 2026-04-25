class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n + 2];
        
        // 도난당한 학생은 -1
        for (int l : lost) {
            students[l]--;
        }
        
        // 여벌이 있는 학생은 +1
        for (int r : reserve) {
            students[r]++;
        }
        
        for (int i = 1; i <= n; i++) {
            if (students[i] < 0) {
                if (students[i - 1] >= 1) {
                    // 왼쪽에서 빌리기
                    students[i]++;
                    students[i - 1]--;
                    
                } else if (students[i + 1] >= 1) {
                    // 오른쪽에서 빌리기
                    students[i]++;
                    students[i + 1]--;
                }
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            if (students[i] >= 0) {
                answer++;
            }
        }
        
        return answer;
    }
}
