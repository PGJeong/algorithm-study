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
	static List<Edge>[] graph;
	static int V, E;
	
	static int dijkstra(int s, int e) {
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// insert initial vertex
		dist[s] = 0;
		pq.add(new Edge(s, 0));
		
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
		
		return dist[e];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new List[V+1];
		
		for(int i = 1; i < V+1; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, w));
			graph[b].add(new Edge(a, w));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		// path op1 = (1) - (v1) - (v2) - (V)
		// path op2 = (1) - (v2) - (v1) - (V)
		int path1 = dijkstra(1, v1);  // op1
		int path2 = dijkstra(1, v2);  // op2
		int path3 = dijkstra(v1, v2); // op1, op2
		int path4 = dijkstra(v2, V);  // op1
		int path5 = dijkstra(v1, V);  // op2
		
		int len1 = Integer.MAX_VALUE;
		int len2 = Integer.MAX_VALUE;
		
		if(path1 != Integer.MAX_VALUE && path3 != Integer.MAX_VALUE && path4 != Integer.MAX_VALUE) {
			len1 = path1 + path3 + path4;
		}
		
		if(path2 != Integer.MAX_VALUE && path3 != Integer.MAX_VALUE && path5 != Integer.MAX_VALUE) {
			len2 = path2 + path3 + path5;
		}
		
		int res = Math.min(len1, len2);
		
		if(res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}
}
