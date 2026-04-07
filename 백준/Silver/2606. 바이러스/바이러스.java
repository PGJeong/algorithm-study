import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int node, edge;
	private static ArrayList<Integer>[] graph;
	private static boolean[] visited;
	
	private static int DFS(int v) {
		int cnt = 1;
		visited[v] = true;
		
		for(int i = 0; i < graph[v].size(); i++) {
			if(!visited[graph[v].get(i)])
				cnt += DFS(graph[v].get(i));
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		node = Integer.parseInt(br.readLine());
		edge = Integer.parseInt(br.readLine());
		graph = new ArrayList[node+1];
		visited = new boolean[node+1];
		
		for(int i = 0; i < graph.length; i++)
			graph[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < edge; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// solution
		System.out.println(DFS(1) - 1);
	}
}
