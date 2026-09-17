import java.util.*;

class Solution {
    int max = 0; // 최대 방문 횟수
    boolean[] visited;
    
    void explore(int k, int[][] dungeons, int cnt) {
        max = Math.max(max, cnt);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                explore(k - dungeons[i][1], dungeons, cnt + 1);
                visited[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        for (int i = 0; i < dungeons.length; i++) {
            if (k >= dungeons[i][0]) {
                visited[i] = true;
                explore(k - dungeons[i][1], dungeons, 1);
                visited[i] = false;
            }
        }
        
        return max;
    }
}
