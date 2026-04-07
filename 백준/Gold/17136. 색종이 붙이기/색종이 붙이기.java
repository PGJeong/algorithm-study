import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] map = new boolean[10][10];
	static int[] papers = { 5, 5, 5, 5, 5 };
	static int min = Integer.MAX_VALUE;
	
	static void search(int row, int col, int cnt) {
		// 탐색완료 - 모든 칸을 다 채움
		if(row >= 10) {
			min = Math.min(min, cnt);
			return;
		}
		
		// 열 수 초과
		if(col >= 10) {
			search(row + 1, 0, cnt); // 다음 행으로 이동
			return;
		}
		
		// 0이 적힌 칸 - 부착대상 X
		if(!map[row][col]) {
			search(row, col + 1, cnt); // 다음 칸으로 이동
			return;
		}
		
		// 1이 적힌 칸 - 부착대상 O
		for(int i = 4; i >= 0; i--) {
			if(papers[i] > 0 && canAttach(row, col, i + 1)) {
				papers[i]--;
				attach(row, col, i + 1, false);
				
				search(row, col + 1, cnt + 1);
				
				papers[i]++;
				attach(row, col, i + 1, true);
			}
		}
	}
	
	static boolean canAttach(int row, int col, int size) {
		if(row + size > 10 || col + size > 10) return false;
		
		for(int r = row; r < row + size; r++) {
			for(int c = col; c < col + size; c++) {
				if(!map[r][c]) return false;
			}
		}
		
		return true;
	}
	
	static void attach(int row, int col, int size, boolean flag) {
		for(int r = row; r < row + size; r++) {
			for(int c = col; c < col + size; c++) {
				map[r][c] = flag;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int r = 0; r < 10; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 10; c++)
				map[r][c] = st.nextToken().equals("1");
		}
		
		search(0, 0, 0);
		System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
	}
}
