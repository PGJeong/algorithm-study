import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int from, to;
	public double weight;

	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return Double.compare(this.weight, e.weight);
	}
}

public class Main {
	static int[] parent;
	static int[] rank;
	
	static int find(int v) {
		if(parent[v] != v) {
			parent[v] = find(parent[v]); // path compression
		}
		
		return parent[v];
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) {
			return false;
		}
		
		// union by rank
		if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		} else {
			parent[rootB] = rootA;
			rank[rootA]++;
		}
		
		return true;
	}
	
	static double calcDist(int[] p1, int[] p2) {
		return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int V = Integer.parseInt(st.nextToken());
    	int E = Integer.parseInt(st.nextToken());
    	int[][] nodes = new int[V+1][2];
    	
    	for(int i = 1; i < V+1; i++) {
    		st = new StringTokenizer(br.readLine());
    		nodes[i][0] = Integer.parseInt(st.nextToken());
    		nodes[i][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	// kruskal algorithm
    	parent = new int[V+1];
    	rank = new int[V+1];
    	
    	for(int i = 1; i < V+1; i++) {
    		parent[i] = i;
    	}
    	
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	
    	int cnt = 0; // 누적 간선 수
    	double sum = 0; // 누적 가중치
    	
    	// 이미 연결된 통로 처리
    	for(int i = 0; i < E; i++) {
    		st = new StringTokenizer(br.readLine());
    		if(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
    			cnt++;
    		}
    	}
    	
    	// 간선 후보 추가
    	for(int i = 1; i < V+1; i++) {
    		for(int j = 1; j < V+1; j++) {
    			if(i != j) {
    				pq.add(new Edge(i, j, calcDist(nodes[i], nodes[j])));
    			}
    		}
    	}
    	
    	while(!pq.isEmpty()) {
    		Edge e = pq.poll();
    		
    		if(union(e.from, e.to)) {
    			cnt++;
    			sum += e.weight;
    		}
    		
    		if(cnt == V-1) {
    			break;
    		}
    	}
    	
    	System.out.printf("%.2f", sum);
    }
}
