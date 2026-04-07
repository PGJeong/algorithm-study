import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		List<int[]>[] graph = new List[V+1];
		
		for(int i = 1; i < V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new int[] {to, weight});
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// dijkstra algorithm
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] e1, int[] e2) -> Integer.compare(e1[1], e2[1]));
		
		// insert initial vertex
		dist[start] = 0;
		pq.add(new int[] {start, 0});
		
		// find shortest path
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			if(curr[1] > dist[curr[0]]) {
				continue;
			}
			
			for(int[] next : graph[curr[0]]) {
				int viaLen = dist[curr[0]] + next[1];
				
				if(viaLen < dist[next[0]]) {
					dist[next[0]] = viaLen;
					pq.add(new int[] {next[0], viaLen});
				}
			}
		}
		
		System.out.println(dist[end]);
	}
}
