import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    	parent = new int[V];
    	rank = new int[V];
    	
    	for(int i = 0; i < V; i++) {
    		parent[i] = i;
    	}
    	
    	for(int i = 1; i <= E; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		if(!union(a, b)) {
    			System.out.println(i);
    			return;
    		}
    	}
    	
    	System.out.println(0);
    }
}
