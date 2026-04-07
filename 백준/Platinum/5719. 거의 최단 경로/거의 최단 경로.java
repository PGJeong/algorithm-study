import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int to, weight;

	public Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return Integer.compare(this.weight, e.weight);
	}
}

public class Main {
	static int V, E, S, D;
	static List<Edge>[] graph;
	static List<Integer>[] prevNode; // previous node of shortest path
	static boolean[][] shortestPath; // edges included in shortest path
	
	static int[] dijkstra(int s, boolean flag) {
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// insert start node
		dist[s] = 0;
		pq.add(new Edge(s, 0));
		
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if(curr.weight > dist[curr.to]) {
				continue;
			}
			
			for(Edge next : graph[curr.to]) {
				int viaWeight = curr.weight + next.weight;
				
				if(viaWeight < dist[next.to]) {
					if(!flag && !shortestPath[curr.to][next.to]) {
						dist[next.to] = viaWeight;
						pq.add(new Edge(next.to, viaWeight));
						
					} else if(flag) {
						dist[next.to] = viaWeight;
						pq.add(new Edge(next.to, viaWeight));
						prevNode[next.to].clear();
						prevNode[next.to].add(curr.to);
					}
				} else if(flag && viaWeight == dist[next.to]) {
					prevNode[next.to].add(curr.to);
				}
			}
		}
		
		return dist;
	}
	
	static void removePath(int d, boolean[] visited) {
		if(d == S || visited[d]) {
			return;
		}
		
		visited[d] = true;
		
		for(int s : prevNode[d]) {
			shortestPath[s][d] = true;
			removePath(s, visited);
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	while(true) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		V = Integer.parseInt(st.nextToken()); // num of vertexes
    		E = Integer.parseInt(st.nextToken()); // num of edges
    		
    		if(V == 0 && E == 0) {
    			break;
    		}
    		
    		st = new StringTokenizer(br.readLine());
    		S = Integer.parseInt(st.nextToken()); // start vertex
    		D = Integer.parseInt(st.nextToken()); // destination vertex
    		graph = new List[V];
    		prevNode = new List[V];
    		shortestPath = new boolean[V][V];
    		
    		for(int i = 0; i < V; i++) {
    			graph[i] = new ArrayList<>();
    		}
    		
    		for(int i = 0; i < V; i++) {
    			prevNode[i] = new ArrayList<>();
    		}
    		
    		for(int i = 0; i < E; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		int weight = Integer.parseInt(st.nextToken());
        		
        		graph[from].add(new Edge(to, weight));
    		}
    		
    		// 최단 경로
    		dijkstra(S, true);
    		
    		// 최단 경로 edge 삭제
    		removePath(D, new boolean[V]);
    		
    		// 거의 최단 경로
    		int[] almostDist = dijkstra(S, false);
    		
    		if(almostDist[D] != Integer.MAX_VALUE) {
    			System.out.println(almostDist[D]);
    		} else {
    			System.out.println(-1);
    		}
    	}
    }
}
