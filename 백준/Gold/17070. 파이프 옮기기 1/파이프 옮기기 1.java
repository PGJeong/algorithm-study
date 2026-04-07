import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int count;
	private static int size;
	private static boolean[][] map;
	
	private static void search(int r, int c, int prevState) {
		if(r == size-1 && c == size-1) {
			count++;
			return;
		}
		
		int nr, nc;
		
		// 0: → 방향
		nr = r + 0;
		nc = c + 1;
		if(nr >= 0 && nc >= 0 && nr < size && nc < size) {
			if(map[nr][nc]) {
				if(prevState == 0 || prevState == 1) {
					search(nr, nc, 0);
				}
			}
		}
		
		// 1: ↘ 방향
		nr = r + 1;
		nc = c + 1;
		if(nr >= 0 && nc >= 0 && nr < size && nc < size) {
			if(map[nr][nc] && map[nr-1][nc] && map[nr][nc-1]) {
				if(prevState == 0 || prevState == 1 || prevState == 2) {
					search(nr, nc, 1);
				}
			}
		}
		
		// 2: ↓ 방향
		nr = r + 1;
		nc = c + 0;
		if(nr >= 0 && nc >= 0 && nr < size && nc < size) {
			if(map[nr][nc]) {
				if(prevState == 1 || prevState == 2) {
					search(nr, nc, 2);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		map = new boolean[size][size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = st.nextToken().equals("0") ? true : false;
			}
		}
		
		count = 0; // 방법의 수	
		search(0, 1, 0);
		
		System.out.println(count);
	}
}
