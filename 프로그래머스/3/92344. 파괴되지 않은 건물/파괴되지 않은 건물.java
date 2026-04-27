class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        
        // (1) 스킬 합산
        // e.g. [4][4]에 -4가 있는 경우 = (0,0)부터 (4,4)까지 -4 적용
        // e.g. (1,1)부터 (2,2)까지 -4인 경우 -> [2][2]에 -4, [1][3] 및 [3][1]에 4, [1][1]에 -4(중복제거)
        int[][] deg = new int[N][M];
        
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0]; // 1은 -, 2는 +
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if (type == 1) {
                degree *= -1;
            }
            
            deg[r2][c2] += degree;
            
            if (r1 - 1 >= 0) deg[r1 - 1][c2] -= degree;
            if (c1 - 1 >= 0) deg[r2][c1 - 1] -= degree;
            if (r1 - 1 >= 0 && c1 - 1 >= 0) deg[r1 - 1][c1 - 1] += degree; // 중복제거
        }
        
        // (2) 누적 합
        // [N][M]부터 [0][0]까지 역으로 올라오면서 누적 스킬 합산    
        for (int r = N - 1; r >= 0; r--) {
            for (int c = M - 1; c >= 0; c--) {
                if (r + 1 < N) deg[r][c] += deg[r + 1][c];
                if (c + 1 < M) deg[r][c] += deg[r][c + 1];
                if (r + 1 < N && c + 1 < M) deg[r][c] -= deg[r + 1][c + 1]; // 중복제거
            }
        }
        
        // (3) board 배열과 누적 합 배열 더해서 1 이상인 원소 count
        int count = 0;
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] + deg[r][c] > 0) count++;
            }
        }
        
        return count;
    }
}
