import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int row, col;
	private static int k; // 회전연산 개수
	private static int[][] arr;
	private static int[][] ops; // 회전연산
	private static int[] operationOrder; // 연산순서
	private static boolean[] visited; // permutation() 수열 방문여부 check
	private static int min; // 배열의 최솟값
	
	private static int calculateArrayValue(int[][] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int r = 0; r < row; r++) {
			int cnt = 0;
			for(int c = 0; c < col; c++) {
				cnt += arr[r][c];
			}
			min = Math.min(min, cnt);
		}
		
		return min;
	}
	
	/* 연산순서를 입력받아 연산 수행 */
	private static void operate() {
		int[][] tmpArr = new int[row][col];
		
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				tmpArr[r][c] = arr[r][c];
			}
		}
		
		for(int i = 0; i < k; i++) {
			// 좌측 상단 좌표
			int[] lt = new int[] {ops[operationOrder[i]][0] - ops[operationOrder[i]][2], ops[operationOrder[i]][1] - ops[operationOrder[i]][2]};
			// 우측 하단 좌표
			int[] rb = new int[] {ops[operationOrder[i]][0] + ops[operationOrder[i]][2], ops[operationOrder[i]][1] + ops[operationOrder[i]][2]};
			
			while(lt[0] < rb[0] && lt[1] < rb[1]) {
				// 반시계 방향으로 연산 수행 후 tmpNum은 tmpArr[lt[0]][lt[1] + 1]에 저장
				int tmpNum = tmpArr[lt[0]][lt[1]];
				
				// ↑
				for(int j = lt[0]; j <= rb[0] - 1; j++) {
					tmpArr[j][lt[1]] = tmpArr[j + 1][lt[1]];
				}
				// ←
				for(int j = lt[1]; j <= rb[1] - 1; j++) {
					tmpArr[rb[0]][j] = tmpArr[rb[0]][j + 1];
				}
				// ↓
				for(int j = rb[0]; j >= lt[0] + 1; j--) {
					tmpArr[j][rb[1]] = tmpArr[j - 1][rb[1]];
				}
				// →
				for(int j = rb[1]; j >= lt[1] + 2; j--) {
					tmpArr[lt[0]][j] = tmpArr[lt[0]][j - 1];
				}
				
				tmpArr[lt[0]][lt[1] + 1] = tmpNum;
				
				// 안쪽 테두리 연산
				lt[0]++;
				lt[1]++;
				rb[0]--;
				rb[1]--;
			}
		}
		
		min = Math.min(min, calculateArrayValue(tmpArr));
	}
	
	/* 연산순서 조합 */
	private static void permutation(int depth) {
		if(depth == k) {
			operate();
			return;
		}
		
		for(int i = 0; i < k; i++) {
			if(!visited[i]) {
				visited[i] = true;
				operationOrder[depth] = i;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[row][col];
		ops = new int[k][3];
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < col; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				ops[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 1-based index to 0-based index 변환
			ops[i][0] -= 1;
			ops[i][1] -= 1;
		}
		
		operationOrder = new int[k];
		visited = new boolean[k];
		min = Integer.MAX_VALUE;
		
		permutation(0);
		System.out.println(min);
	}
}
