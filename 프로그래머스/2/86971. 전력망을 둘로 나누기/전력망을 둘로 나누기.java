import java.util.*;

class Solution {
    ArrayList<Integer>[] graph;
    boolean[] visited;
    int cnt;
    int min;
    
    void dfs(int v) {
        for (int n : graph[v]) {
            if (!visited[n]) {
                visited[n] = true;
                cnt++;
                dfs(n);
            }
        }
    }
    
    public int solution(int n, int[][] wires) {
        // 그래프 생성
        graph = new ArrayList[n + 1];
        min = Integer.MAX_VALUE;
        
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 간선 하나씩 끊기
        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            
            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));
            
            visited = new boolean[n + 1];
            visited[1] = true;
            cnt = 1;
            dfs(1);
            
            min = Math.min(min, Math.abs(cnt-(n-cnt)));
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        return min;
    }
}
