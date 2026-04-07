import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M; // 보드의 세로, 가로
	private static char[][] board;
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		
		for(int r = 0; r < N; r++) {
			String str = br.readLine();
			for(int c = 0; c < M; c++)
				board[r][c] = str.charAt(c);
		}
	}
	
	private static char[][] move(int dir, char[][] board) {
		// (오류수정) 2차원 배열의 경우 clone()을 사용하면, 내부 1차원 배열들의 참조가 복사됨 (얕은복사)
		char[][] newboard = new char[N][M];
		
		boolean flag_S = false; // 게임성공 flag
		boolean flag_F = false; // 게임실패 flag
		
		int[] locR = null; // 빨간구슬 좌표
		int[] locB = null; // 파란구슬 좌표
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				// 2차원 배열 복사
				newboard[r][c] = board[r][c];
				
				// 각 구슬 좌표 저장
				if(newboard[r][c] == 'R') { 
					locR = new int[] {r, c};
					newboard[r][c] = '.'; // 위치 저장 후 빈칸으로 변경
				}
				if(newboard[r][c] == 'B') { 
					locB = new int[] {r, c};
					newboard[r][c] = '.'; // 위치 저장 후 빈칸으로 변경
				}
			}
		}
		
		char char1, char2;
		int[] loc1, loc2;
		
		// 이동
		// 상
		if(dir == 0) {
			// 좌표 상 위에 있는 구슬부터 이동 (동일 열에 있는 경우 대비)
			if(locR[0] < locB[0]) {
				char1 = 'R';
				char2 = 'B';
				loc1 = locR;
				loc2 = locB;
			} else {
				char1 = 'B';
				char2 = 'R';
				loc1 = locB;
				loc2 = locR;
			}
			
			// 1번째로 이동할 구슬 (char1)
			while(true) {
				loc1[0]--; // 위로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc1[0]][loc1[1]] == '#' || newboard[loc1[0]][loc1[1]] == char2) {
					loc1[0]++;
					newboard[loc1[0]][loc1[1]] = char1;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char1 == 'R' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char1 == 'B' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
			
			// 2번째로 이동할 구슬 (char2)
			while(true) {
				loc2[0]--; // 위로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc2[0]][loc2[1]] == '#' || newboard[loc2[0]][loc2[1]] == char1) {
					loc2[0]++;
					newboard[loc2[0]][loc2[1]] = char2;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char2 == 'R' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char2 == 'B' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
		}
		
		// 하
		else if(dir == 1) {
			// 좌표 상 아래에 있는 구슬부터 이동 (동일 열에 있는 경우 대비)
			if(locR[0] > locB[0]) {
				char1 = 'R';
				char2 = 'B';
				loc1 = locR;
				loc2 = locB;
			} else {
				char1 = 'B';
				char2 = 'R';
				loc1 = locB;
				loc2 = locR;
			}
			
			// 1번째로 이동할 구슬 (char1)
			while(true) {
				loc1[0]++; // 아래로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc1[0]][loc1[1]] == '#' || newboard[loc1[0]][loc1[1]] == char2) {
					loc1[0]--;
					newboard[loc1[0]][loc1[1]] = char1;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char1 == 'R' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char1 == 'B' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
			
			// 2번째로 이동할 구슬 (char2)
			while(true) {
				loc2[0]++; // 아래로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc2[0]][loc2[1]] == '#' || newboard[loc2[0]][loc2[1]] == char1) {
					loc2[0]--;
					newboard[loc2[0]][loc2[1]] = char2;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char2 == 'R' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char2 == 'B' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
		}
		
		// 좌
		else if(dir == 2) {
			// 좌표 상 왼쪽에 있는 구슬부터 이동 (동일 행에 있는 경우 대비)
			if(locR[1] < locB[1]) {
				char1 = 'R';
				char2 = 'B';
				loc1 = locR;
				loc2 = locB;
			} else {
				char1 = 'B';
				char2 = 'R';
				loc1 = locB;
				loc2 = locR;
			}
			
			// 1번째로 이동할 구슬 (char1)
			while(true) {
				loc1[1]--; // 왼쪽으로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc1[0]][loc1[1]] == '#' || newboard[loc1[0]][loc1[1]] == char2) {
					loc1[1]++;
					newboard[loc1[0]][loc1[1]] = char1;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char1 == 'R' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char1 == 'B' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
			
			// 2번째로 이동할 구슬 (char2)
			while(true) {
				loc2[1]--; // 왼쪽으로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc2[0]][loc2[1]] == '#' || newboard[loc2[0]][loc2[1]] == char1) {
					loc2[1]++;
					newboard[loc2[0]][loc2[1]] = char2;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char2 == 'R' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char2 == 'B' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
		}
		
		// 우
		else if(dir == 3) {
			// 좌표 상 오른쪽에 있는 구슬부터 이동 (동일 행에 있는 경우 대비)
			if(locR[1] > locB[1]) {
				char1 = 'R';
				char2 = 'B';
				loc1 = locR;
				loc2 = locB;
			} else {
				char1 = 'B';
				char2 = 'R';
				loc1 = locB;
				loc2 = locR;
			}
			
			// 1번째로 이동할 구슬 (char1)
			while(true) {
				loc1[1]++; // 오른쪽으로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc1[0]][loc1[1]] == '#' || newboard[loc1[0]][loc1[1]] == char2) {
					loc1[1]--;
					newboard[loc1[0]][loc1[1]] = char1;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char1 == 'R' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char1 == 'B' && newboard[loc1[0]][loc1[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
			
			// 2번째로 이동할 구슬 (char2)
			while(true) {
				loc2[1]++; // 오른쪽으로 한 칸 이동
				
				// 앞에 벽이 있거나, 앞에 다른 구슬이 있으면 위로 이동 중단
				if(newboard[loc2[0]][loc2[1]] == '#' || newboard[loc2[0]][loc2[1]] == char1) {
					loc2[1]--;
					newboard[loc2[0]][loc2[1]] = char2;
					break;
				}
				
				// 만약 빨간구슬이 구멍을 통해 빠져나가면 게임종료 (성공)
				if(char2 == 'R' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_S = true;
					break;
				}
				
				// 만약 파란구슬이 구멍을 통해 빠져나가면 게임종료 (실패)
				if(char2 == 'B' && newboard[loc2[0]][loc2[1]] == 'O') {
					flag_F = true;
					break;
				}
			}
		}
		
		// return값의 board[0][0]이 'F'이면 실패 표시
		if (flag_F) return new char[][] {{'F'}};
		
		// return값의 board[0][0]이 'S'이면 성공 표시 (빨간구슬이 빠져나가더라도 파란구슬이 빠져나가면 실패처리)
		if (flag_S) return new char[][] {{'S'}};
		
		return newboard;
	}
	
	private static int BFS(char[][] board) {
		Queue<char[][]> q_board = new LinkedList<>(); // 보드 상태 저장
		Queue<Integer> q_dir = new LinkedList<>(); // 직전 방향 저장
		Queue<Integer> q_cnt = new LinkedList<>(); // 누적 이동 횟수
		
		// 초깃값 세팅
		q_board.add(board);
		q_dir.add(-1);
		q_cnt.add(0);
		
		// BFS 탐색
		while(!q_board.isEmpty()) {
			char[][] curr_board = q_board.poll();
			int curr_dir = q_dir.poll();
			int curr_cnt = q_cnt.poll();
			
			// 이동횟수가 10번을 초과한 경우
			if(curr_cnt > 10)
				break;
			
			// 게임종료(성공)인 경우, 이동횟수 return
			if(curr_board[0][0] == 'S')
				return curr_cnt;
			
			// 게임종료(실패)인 경우, 건너뛰기
			if(curr_board[0][0] == 'F')
				continue;
			
			// 상하좌우 이동
			if(curr_dir != 0) { // 상
				q_board.add(move(0, curr_board));
				q_dir.add(0);
				q_cnt.add(curr_cnt + 1);
			}
			if(curr_dir != 1) { // 하
				q_board.add(move(1, curr_board));
				q_dir.add(1);
				q_cnt.add(curr_cnt + 1);
			}
			if(curr_dir != 2) { // 좌
				q_board.add(move(2, curr_board));
				q_dir.add(2);
				q_cnt.add(curr_cnt + 1);
			}
			if(curr_dir != 3) { // 우
				q_board.add(move(3, curr_board));
				q_dir.add(3);
				q_cnt.add(curr_cnt + 1);
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(BFS(board));
	}
}
