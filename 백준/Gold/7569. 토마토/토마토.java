import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		int m = Integer.parseInt(st.nextToken()); // 가로
		int n = Integer.parseInt(st.nextToken()); // 세로
		int h = Integer.parseInt(st.nextToken()); // 높이
		int[][][] storage = new int[h][n][m]; // 보관상자
		int[][][] visited = new int[h][n][m]; // 방문여부 (일수 저장, -1이면 미방문)
		int[][] delta = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
		int unripe = 0; // 익지 않은 토마토 개수
		
		Queue<int[]> q = new LinkedList<>();
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < m; k++) {
					visited[i][j][k] = -1;
					storage[i][j][k] = Integer.parseInt(st.nextToken());
					
					if(storage[i][j][k] == 0)
						unripe++;
					
					if(storage[i][j][k] == 1) {
						q.add(new int[] {i, j, k});
						visited[i][j][k] = 0;
					}
				}
			}
		}
		
		int maxDays = 0; // 모든 토마토가 익는데 걸리는 일수
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			for(int i = 0; i < delta.length; i++) {
				int nh = curr[0] + delta[i][0];
				int nn = curr[1] + delta[i][1];
				int nm = curr[2] + delta[i][2];
				
				if(nh < 0 || nn < 0 || nm < 0 || nh >= h || nn >= n || nm >= m) continue;
				
				if(visited[nh][nn][nm] != -1 || storage[nh][nn][nm] != 0) continue;
				
				q.add(new int[] {nh, nn, nm});
				visited[nh][nn][nm] = visited[curr[0]][curr[1]][curr[2]] + 1;
				storage[nh][nn][nm] = 1; // 익은 토마토로 상태변경
				unripe--;
				
				maxDays = Math.max(maxDays, visited[nh][nn][nm]);
			}
		}
		
		if(unripe > 0) System.out.println("-1");
		else System.out.println(maxDays);
	}
}
