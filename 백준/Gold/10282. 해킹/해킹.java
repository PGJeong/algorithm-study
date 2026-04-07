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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken()); // start vertex
			
			List<Edge>[] graph = new List[V+1];
			
			for(int i = 1; i < V+1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph[to].add(new Edge(from, weight));
			}
			
			// dijkstra algorithm
			int[] dist = new int[V+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			// insert initial vertex
			dist[S] = 0;
			pq.add(new Edge(S, 0));
			
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
			
			int infected = 0;
			int time = 0;
			
			for(int i = 1; i < dist.length; i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					infected++;
					time = Math.max(time, dist[i]);
				}
			}
			
			System.out.println(infected + " " + time);
		}
	}
}
