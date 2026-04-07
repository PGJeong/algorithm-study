import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int area, dist;

	public Edge(int area, int dist) {
		this.area = area;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge edge) {
		return Integer.compare(this.dist, edge.dist);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()) + 1; // 지역 수
		int M = Integer.parseInt(st.nextToken()); // 수색범위
		int R = Integer.parseInt(st.nextToken()); // 경로 수
		int[] T = new int[N]; // 각 지역별 아이템 수
		ArrayList<Edge>[] graph = new ArrayList[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
			graph[i] = new ArrayList<Edge>();
			T[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, d));
			graph[b].add(new Edge(a, d));
		}

		int max = Integer.MIN_VALUE;

		for (int i = 1; i < N; i++) {
			// dijkstra algorithm
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			int[] dist = new int[N];
			Arrays.fill(dist, Integer.MAX_VALUE);

			pq.add(new Edge(i, 0));
			dist[i] = 0;

			while (!pq.isEmpty()) {
				Edge curr = pq.poll();

				if (curr.dist > dist[curr.area]) {
					continue;
				}

				for(Edge next : graph[curr.area]) {
					int via = curr.dist + next.dist;

					if(via < dist[next.area]) {
						dist[next.area] = via;
						pq.add(new Edge(next.area, via));
					}
				}
			}

			int items = 0;

			for(int j = 1; j < N; j++) {
				if(dist[j] <= M) {
					items += T[j];
				}
			}

			max = Math.max(max, items);
		}

		System.out.println(max);
	}
}
