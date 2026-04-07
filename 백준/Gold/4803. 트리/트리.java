import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int[] rank;
	static List<Integer>[] graph;
	static boolean[] visitedVertex;
	static boolean[][] visitedEdge;
	static boolean isTree; // flag
	
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
	
	static void isTree(int s) {
		visitedVertex[s] = true;
		
		for(int v : graph[s]) {
			if(!visitedEdge[s][v] && !union(s, v)) {
				isTree = false;
			}
			
			visitedEdge[s][v] = true;
			visitedEdge[v][s] = true;
			
			if(!visitedVertex[v]) {
				isTree(v);
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int caseNum = 1;
    	
    	while(true) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int V = Integer.parseInt(st.nextToken());
        	int E = Integer.parseInt(st.nextToken());
        	
        	if(V == 0 && E == 0) {
        		break;
        	}
        	
        	parent = new int[V+1];
        	rank = new int[V+1];
        	graph = new List[V+1];
        	visitedVertex = new boolean[V+1];
        	visitedEdge = new boolean[V+1][V+1];
        	
        	for(int i = 1; i < V+1; i++) {
        		parent[i] = i;
        		graph[i] = new ArrayList<>();
        	}

        	for(int i = 0; i < E; i++) {
        		st = new StringTokenizer(br.readLine());
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	
            	graph[a].add(b);
            	graph[b].add(a);
        	}
        	
        	int cnt = 0; // num of trees
        	
        	for(int i = 1; i < V+1; i++) {
        		if(!visitedVertex[i]) {
        			isTree = true; // initialize flag
            		isTree(i);
            		
            		if(isTree) {
            			cnt++;
            		}
        		}
        	}
        	
        	System.out.print("Case " + caseNum + ": ");

        	if(cnt == 0) System.out.println("No trees.");
        	else if(cnt == 1) System.out.println("There is one tree.");
        	else System.out.println("A forest of " + cnt + " trees.");
        	
        	caseNum++;
    	}
    }
}
