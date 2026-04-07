import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Edge {
	public int vertex, dist;
	public Edge(int vertex, int dist) { this.vertex = vertex; this.dist = dist; }
}

public class Main {
	// Memo: 트리를 인접행렬로 구현 시 메모리 초과 -> 인접리스트로 구현
	private static int v;
	private static ArrayList<Edge>[] tree;
	private static boolean[] visited;
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		
		v = sc.nextInt() + 1; // 정점 번호는 1부터 시작
		tree = new ArrayList[v];
		
		for(int i = 1; i < v; i++)
			tree[i] = new ArrayList<Edge>();
		
		for(int i = 1; i < v; i++) {
			int a = sc.nextInt();
			while(true) {
				int b = sc.nextInt();
				
				if(b == -1) break;
				
				int dist = sc.nextInt();
				tree[a].add(new Edge(b, dist));
			}
		}
		
		sc.close();
	}
	
	private static Edge BFS(int start) {
		// start 정점을 기준으로 가장 거리가 먼 "정점"과 "거리"를 return
		int max = 0;
		int vertex = start;
		
		Queue<Edge> q = new LinkedList<>();
		
		// 시작정점 삽입
		q.add(new Edge(start, 0));
		visited[start] = true;
		
		// BFS 탐색
		while(!q.isEmpty()) {
			Edge next = q.poll();
			
			// start 정점기준 최대 거리 갱신
			int tmp = Math.max(max, next.dist);
			if(max != tmp) { // 최대값 갱신이 필요한 경우
				max = tmp;
				vertex = next.vertex;
			}
			
			for(Edge e : tree[next.vertex]) {
				if(visited[e.vertex] == false) {
					// start 정점부터 e 정점까지의 거리 합산
					q.add(new Edge(e.vertex, e.dist + next.dist));
					visited[e.vertex] = true;
				}
			}
		}
		
		return new Edge(vertex, max);
	}
	
	public static void main(String[] args) {
		init();
		
		// (1.) 임의의 노드에서 가장 긴 경로로 연결된 노드는 트리의 지름에 해당하는 두 노드 중 하나
		visited = new boolean[v];
		Edge a = BFS(1);	
		// (2.) a를 기준으로 가장 거리가 먼 정점까지의 거리는 트리의 지름
		visited = new boolean[v];
		Edge b = BFS(a.vertex);
		
		System.out.println(b.dist);
	}
}
