import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
		if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		} else if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else {
			parent[rootA] = rootB;
			rank[rootB]++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		rank = new int[V + 1];
		
		for(int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[2], b[2]));
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new int[] {Integer.parseInt(st.nextToken()),
							  Integer.parseInt(st.nextToken()),
							  Integer.parseInt(st.nextToken())});
		}
		
		int maxCost = 0;
		int sumCost = 0;
		int cnt = 0;
		
		// kruskal algorithm
		while(!pq.isEmpty()) {
			int[] path = pq.poll();
			
			if(union(path[0], path[1])) {
				maxCost = path[2];
				sumCost += path[2];
				cnt++;
			}
			
			if(cnt == V - 1) {
				break;
			}
		}
		
		System.out.println(sumCost - maxCost);
	}
}
