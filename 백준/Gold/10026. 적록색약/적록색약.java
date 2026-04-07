import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int size;
	private static char pic[][];
	private static boolean visit[][];
	private static int delta[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 사방탐색을 위한 delta (동서남북)
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		
		pic = new char[size][size];
		
		for(int i = 0; i < size; i++) {
			String str = br.readLine();
			
			for(int j = 0; j < size; j++) {
				pic[i][j] = str.charAt(j);
			}
		}
	}
	
	private static void dfs(int x, int y, char color) {
		if(pic[x][y] != color)
			return;
		
		visit[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + delta[i][0];
			int ny = y + delta[i][1];
			if(nx >= 0 && ny >= 0 && nx < size && ny < size) {
				if(visit[nx][ny] == false)
					dfs(nx, ny, color);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		// 정상 case
		int countA = 0;
		visit = new boolean[size][size]; // visit 초기화
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(visit[i][j] == false) {
					dfs(i, j, pic[i][j]);
					countA++;
				}
			}
		}
		
		// 적록색약 case
		int countB = 0;
		visit = new boolean[size][size]; // visit 초기화
		
		// 적록 구분이 안되므로 G를 R로 변경하여 구역통합
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(pic[i][j] == 'G')
					pic[i][j] = 'R';
			}
		}
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(visit[i][j] == false) {
					dfs(i, j, pic[i][j]);
					countB++;
				}
			}
		}
		
		System.out.println(countA + " " + countB);
	}
}
