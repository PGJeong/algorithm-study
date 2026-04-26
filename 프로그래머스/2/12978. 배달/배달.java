import java.util.*;

class Edge implements Comparable<Edge> {
    public int node, dist;
    
    public Edge(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.dist, o.dist);
    }
}

class Solution {
    public int solution(int N, int[][] road, int K) {
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0]; // 마을1
            int b = road[i][1]; // 마을2
            int c = road[i][2]; // 도로
            
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        
        // Dijkstra
        int[] dist = new int[N + 1]; // 거리정보
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            
            if (curr.dist > dist[curr.node]) {
                continue;
            }
            
            for (Edge next : graph[curr.node]) {
                int newDist = curr.dist + next.dist;
                
                if (newDist < dist[next.node]) {
                    dist[next.node] = newDist;
                    pq.add(new Edge(next.node, newDist));
                }
            }
        }
        
        // 배달 가능 마을 개수 count
        int count = 0;
        
        for (int d : dist) {
            if (d <= K) count++;
        }
        
        return count;
    }
}
