import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int V = Integer.parseInt(st.nextToken()); // num of city
    	int E = Integer.parseInt(st.nextToken()); // num of cables
    	int P = Integer.parseInt(st.nextToken()); // num of power plant
    	int[] power = new int[P]; // city including power plant
    	List<Edge>[] graph = new List[V+1];
    	
    	for(int i = 1; i < V+1; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	
    	for(int i = 0; i < P; i++) {
    		power[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	for(int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int weight = Integer.parseInt(st.nextToken());
    		
    		graph[from].add(new Edge(to, weight));
    		graph[to].add(new Edge(from, weight));
    	}
    	
    	// prim algorithm
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	boolean[] selected = new boolean[V+1];
    	
    	// insert start vertex
    	for(int i = 0; i < P; i++) {
    		pq.add(new Edge(power[i], 0));
    	}
    	
    	int len = 0;
    	
    	while(!pq.isEmpty()) {
    		Edge curr = pq.poll();
    		
    		if(selected[curr.to]) {
    			continue;
    		}
    		
    		selected[curr.to] = true;
    		len += curr.weight;
    		
    		for(Edge next : graph[curr.to]) {
    			pq.add(next);
    		}
    	}
    	
    	System.out.println(len);
    }
}
