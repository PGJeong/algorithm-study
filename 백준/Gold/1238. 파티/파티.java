import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
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
	static int V, E, X;
	static List<Edge>[] graph;
	
	static int[] dijkstra(int start) {
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// insert initial vertex
		dist[start] = 0;
		pq.add(new Edge(start, 0));
		
		// find shortest path
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if(curr.weight > dist[curr.to]) {
				continue;
			}
			
			for(Edge next : graph[curr.to]) {
				int viaWeight = curr.weight + next.weight;
				
				if(viaWeight < dist[next.to]) {
					dist[next.to] = viaWeight;
					pq.add(new Edge(next.to, viaWeight));
				}
			}
		}
		
		return dist;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()); // party venue
		graph = new List[V+1];
		int[] time = new int[V+1]; // round-trip time
		
		for(int i = 1; i < V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, weight));
		}
		
		// arrival
		for(int i = 1; i <= V; i++) {
			int[] dist = dijkstra(i);
			time[i] += dist[X];
		}
		
		// departure
		int[] dist = dijkstra(X);
		for(int i = 1; i <= V; i++) {	
			time[i] += dist[i];
		}
		
		int max = 0;
		
		for(int i = 0; i < time.length; i++) {
			max = Math.max(max, time[i]);
		}
		
		System.out.println(max);
	}
}
