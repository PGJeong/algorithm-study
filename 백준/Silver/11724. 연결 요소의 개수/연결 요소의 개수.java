import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int n; // vertex
	private static int m; // edge
	
	private static ArrayList<Integer> graph[];
	private static boolean visit[];
	
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 인접 리스트 초기화
		graph = new ArrayList[n+1];
				
		for(int i = 0; i < n+1; i++)
			graph[i] = new ArrayList<Integer>();
				
		// 방문 배열 초기화
		visit = new boolean[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
	}
	
	private static void dfs(int v) {
		if(visit[v])
			return;
		
		visit[v] = true;
		
		for(int i = 0; i < graph[v].size(); i++) {
			dfs(graph[v].get(i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		int count = 0;
		
		for(int i = 1; i <= n; i++) {
			if(!visit[i]) {
				count++;
				dfs(i);
			}
		}
		
		System.out.println(count);
	}
}
