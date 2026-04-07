import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		List<int[]>[] graph = new ArrayList[V+1];
		
		for(int i = 0; i < V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, w});
			graph[b].add(new int[] {a, w});
		}
		
		// Prim Algorithm
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
		boolean[] selected = new boolean[V+1];
		int sum = 0;
		int cnt = 0;
		
		// 초기 노드 삽입
		pq.add(new int[] {1, 0});
		
		while(!pq.isEmpty()) {
			int[] edge = pq.poll();
			int v = edge[0];
			int w = edge[1];
			
			if(selected[v]) {
				continue;
			}
			
			selected[v] = true;
			sum += w;
			cnt++;
			
			for(int i = 0; i < graph[v].size(); i++) {
				int[] temp = graph[v].get(i);
				
				if(!selected[temp[0]]) {
					pq.add(new int[] {temp[0], temp[1]});
				}
			}
			
			if(cnt == V) {
				break;
			}
		}
		
		System.out.println(sum);
	}
}
