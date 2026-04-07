import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	private static void DFS(int r, int c, int h, int[][] map, boolean[][] visited) {
		visited[r][c] = true;
		
		for(int i = 0; i < delta.length; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			
			if(nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) continue;
			
			if(visited[nr][nc]) continue;
			
			if(map[nr][nc] > h) DFS(nr, nc, h, map, visited);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		int[][] map = new int[size][size];
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		
		int safezone = 0; // 안전영역의 최대개수
		
		for(int h = 0; h <= max; h++) {
			int count = 0; // 강수량이 h일때 안전영역 개수
			boolean[][] visited = new boolean[size][size];
			
			for(int r = 0; r < size; r++) {
				for(int c = 0; c < size; c++) {
					if(map[r][c] > h && !visited[r][c]) {
						DFS(r, c, h, map, visited);
						count++;
					}
				}
			}
			
			safezone = Math.max(safezone, count);
		}
		
		System.out.println(safezone);
	}
}
