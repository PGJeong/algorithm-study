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
	static int V, E, D, S, G, H;
	static List<Edge>[] graph;
	
	static int[] dijkstra(int s) {
		int[] dist = new int[V+1];
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
					dist[next.to] = viaWeight;
					pq.add(new Edge(next.to, viaWeight));
				}
			}
		}
		
		return dist;
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder res = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int t = 0; t < T; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		V = Integer.parseInt(st.nextToken()); // 교차로 개수
    		E = Integer.parseInt(st.nextToken()); // 도로 개수
    		D = Integer.parseInt(st.nextToken()); // 목적지 후보 개수
    		
    		st = new StringTokenizer(br.readLine());
    		S = Integer.parseInt(st.nextToken()); // 출발지
    		G = Integer.parseInt(st.nextToken()); // Edge(G, H) = 출몰 도로
    		H = Integer.parseInt(st.nextToken());
    		
    		graph = new List[V+1];
    		int[] dest = new int[D]; // 목적지 후보
    		
    		for(int i = 1; i < V+1; i++) {
    			graph[i] = new ArrayList<>();
    		}
    		
    		for(int i = 0; i < E; i++) {
    			st = new StringTokenizer(br.readLine());
    			int from = Integer.parseInt(st.nextToken());
    			int to = Integer.parseInt(st.nextToken());
    			int weight = Integer.parseInt(st.nextToken());
    			
    			graph[from].add(new Edge(to, weight));
    			graph[to].add(new Edge(from, weight));
    		}
    		
    		for(int i = 0; i < D; i++) {
    			dest[i] = Integer.parseInt(br.readLine());
    		}
    		
    		Arrays.sort(dest);
    		
    		int[] distS = dijkstra(S);
    		int[] distG = dijkstra(G);
    		int[] distH = dijkstra(H);
    		
    		for(int i = 0; i < D; i++) {
    			int S_D = distS[dest[i]]; // 경로 S-목적지
    			int S_G_H = distS[G] + distG[H] + distH[dest[i]]; // 경로 S-G-H-목적지
    			int S_H_G = distS[H] + distH[G] + distG[dest[i]]; // 경로 S-H-G-목적지
    			
    			if(S_D == S_G_H || S_D == S_H_G) {
    				res.append(dest[i] + " ");
    			}
    		}
    		
    		res.append("\n");
    	}
    	System.out.println(res);
    }
}
