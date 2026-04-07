import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int size;
	private static int originalMap[][];
	
	private static final int RIGHT = 1;
	private static final int LEFT = 2;
	private static final int UP = 3;
	private static final int DOWN = 4;
	
	private static int max = 0;
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		originalMap = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static int[][] move(int dir, int map[][]) {
		int newmap[][] = new int[size][size];
		
		int prev;
		int index;
		
		if(dir == RIGHT) {
			for(int r = 0; r < size; r++) {
				index = size - 1;
				prev = -1;
				for(int c = size-1; c >= 0; c--) {
					// 빈 칸은 무시
					if(map[r][c] == 0)
						continue;
					
					if(map[r][c] == prev) {
						// 기존 값 수정
						if(index != size-1) index++;
						newmap[r][index] = prev * 2;
						index--;
						
						// 최댓값 확인
						max = Math.max(max, prev * 2);
						
						prev = -1;
					} else {
						newmap[r][index] = map[r][c];
						index--;
						
						prev = map[r][c];
						
						// 최댓값 확인
						max = Math.max(max, map[r][c]);
					}
				}
			}
		}
		else if(dir == LEFT) {
			for(int r = 0; r < size; r++) {
				index = 0;
				prev = -1;
				for(int c = 0; c < size; c++) {
					// 빈 칸은 무시
					if(map[r][c] == 0)
						continue;
					
					if(map[r][c] == prev) {
						// 기존 값 수정
						if(index != 0) index--;
						newmap[r][index] = prev * 2;
						index++;
						
						// 최댓값 확인
						max = Math.max(max, prev * 2);
						
						prev = -1;
					} else {
						newmap[r][index] = map[r][c];
						index++;
						
						prev = map[r][c];
						
						// 최댓값 확인
						max = Math.max(max, map[r][c]);
					}
				}
			}
		}
		else if(dir == UP) {
			for(int c = 0; c < size; c++) {
				index = 0;
				prev = -1;
				for(int r = 0; r < size; r++) {
					// 빈 칸은 무시
					if(map[r][c] == 0)
						continue;
					
					if(map[r][c] == prev) {
						// 기존 값 수정
						if(index != 0) index--;
						newmap[index][c] = prev * 2;
						index++;
						
						// 최댓값 확인
						max = Math.max(max, prev * 2);
						
						prev = -1;
					} else {
						newmap[index][c] = map[r][c];
						index++;
						
						prev = map[r][c];
						
						// 최댓값 확인
						max = Math.max(max, map[r][c]);
					}
				}
			}
		}
		else if(dir == DOWN) {
			for(int c = 0; c < size; c++) {
				index = size - 1;
				prev = -1;
				for(int r = size-1; r >= 0; r--) {
					// 빈 칸은 무시
					if(map[r][c] == 0)
						continue;
					
					if(map[r][c] == prev) {
						// 기존 값 수정
						if(index != size-1) index++;
						newmap[index][c] = prev * 2;
						index--;
						
						// 최댓값 확인
						max = Math.max(max, prev * 2);
						
						prev = -1;
					} else {
						newmap[index][c] = map[r][c];
						index--;
						
						prev = map[r][c];
						
						// 최댓값 확인
						max = Math.max(max, map[r][c]);
					}
				}
			}
		}
		
		return newmap;
	}
	
	private static void dfs(int map[][], int count) {
		if(count == 5)
			return;
		
		dfs(move(RIGHT, map), count+1);
		dfs(move(LEFT, map), count+1);
		dfs(move(UP, map), count+1);
		dfs(move(DOWN, map), count+1);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(originalMap, 0);
		
		System.out.println(max);
	}
}