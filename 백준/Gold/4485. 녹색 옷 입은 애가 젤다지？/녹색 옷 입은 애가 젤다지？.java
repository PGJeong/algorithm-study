import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int r, c, weight;

	public Edge(int r, int c, int weight) {
		this.r = r;
		this.c = c;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return Integer.compare(this.weight, e.weight);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int cnt = 0;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			if(N == 0) {
				break;
			}
			
			for(int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// dijkstra algorithm
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			// insert initial vertex
			dist[0][0] = map[0][0];
			pq.add(new Edge(0, 0, map[0][0]));
			
			// find shortest path
			while(!pq.isEmpty()) {
				Edge curr = pq.poll();
				
				if(curr.weight > dist[curr.r][curr.c]) {
					continue;
				}
				
				for(int d = 0; d < delta.length; d++) {
					int nr = curr.r + delta[d][0];
					int nc = curr.c + delta[d][1];
					
					if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
						continue;
					}
					
					int viaWeight = curr.weight + map[nr][nc];
					
					if(viaWeight < dist[nr][nc]) {
						dist[nr][nc] = viaWeight;
						pq.add(new Edge(nr, nc, viaWeight));
					}
				}
			}
			
			cnt++;
			System.out.println("Problem " + cnt + ": " + dist[N-1][N-1]);
		}
	}
}
