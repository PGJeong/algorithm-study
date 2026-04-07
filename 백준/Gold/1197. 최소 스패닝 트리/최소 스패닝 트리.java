import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());	
		ArrayList<int[]>[] graph = new ArrayList[V+1];
		
		for(int i = 0; i < V+1; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new int[] {b, w});
			graph[b].add(new int[] {a, w});
		}
		
		// prim algorithm
		// [0]: 트리와 연결된 정점, [1]: 해당 정점 연결 비용
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
		boolean[] selected = new boolean[V+1];
		int weightSum = 0;
		int edgeCnt = -1;
		
		// initial vertex insert
		pq.add(new int[] {1, 0}); // 초기 트리
		
		while(!pq.isEmpty()) {
			int[] edge = pq.poll();
			
			if(selected[edge[0]]) {
				continue;
			}
			
			selected[edge[0]] = true;
			weightSum += edge[1];
			edgeCnt++;
			
			for(int i = 0; i < graph[edge[0]].size(); i++) {
				int[] temp = graph[edge[0]].get(i);
				
				if(!selected[temp[0]]) {
					pq.add(new int[] {temp[0], temp[1]});
				}
			}
			
			if(edgeCnt == V-1) {
				break;
			}
		}
		
		System.out.println(weightSum);
	}
}
