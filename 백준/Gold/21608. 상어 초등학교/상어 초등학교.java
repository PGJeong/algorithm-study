import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Seat {
	public int row, col; // 좌석 좌표
	public int friend; // 인접한 칸에 좋아하는 학생 수
	public int empty; // 인접한 칸에 비어있는 좌석 수
	public Seat(int row, int col, int friend, int empty) {
		this.row = row; this.col = col; this.friend = friend; this.empty = empty;
	}
}

public class Main {
	private static int size;
	private static int student; // 학생 수
	private static int[][] room; // 교실
	private static int[][] friend; // 좋아하는 학생 list
	
	private static final int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		student = (int) Math.pow(size, 2);
		room = new int[size][size];
		friend = new int[student][5];
		
		for(int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				friend[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static int[] findSeat(int index) {
		// 최적 좌석 선정을 위해 PriorityQueue 사용
		PriorityQueue<Seat> q = new PriorityQueue<>(new Comparator<Seat>() {
			public int compare(Seat o1, Seat o2) {
				// (1순위) 인접한 칸에 좋아하는 학생 수가 가장 많은 좌석
				if(o1.friend != o2.friend) return o2.friend - o1.friend;
				
				// (2순위) 인접한 칸에 비어있는 좌석이 가장 많은 좌석
				else if(o1.empty != o2.empty) return o2.empty - o1.empty;
				
				// (3순위) 행의 번호가 가장 작은 칸
				else if(o1.row != o2.row) return o1.row - o2.row;
				
				// (4순위) 열의 번호가 가장 작은 칸
				else return o1.col - o2.col;
			};
		});
		
		// 전 좌석 탐색
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				
				int f = 0; // 인접한 칸에 좋아하는 학생 수
				int e = 0; // 인접한 칸에 비어있는 좌석 수
				
				// 이미 선점된 좌석은 무시
				if(room[r][c] != 0)
					continue;
				
				// 사방탐색
				for(int i = 0; i < delta.length; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];
					
					if(nr < 0 || nc < 0 || nr >= size || nc >= size)
						continue;
					
					// 좋아아는 학생이 있는지 확인
					for(int j = 1; j < friend[index].length; j++)
						if(friend[index][j] == room[nr][nc]) f++;
					
					// 비어있는 좌석이 있는지 확인
					if(room[nr][nc] == 0) e++;
				}
				
				// 탐색한 좌석정보 등록
				q.add(new Seat(r, c, f, e));
			}
		}
		
		// 최적 좌석의 좌표 return;
		Seat s = q.poll();
		return new int[] {s.row, s.col};
	}
	
	private static int satisfaction() { // 만족도 산출
		int sum = 0;
		
		// 전 좌석 탐색
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				int studentNo = room[r][c]; // 학생 번호
				int index = -1; // 좋아하는 학생이 적힌 friend 배열의 index
				int f = 0; // 인접한 칸에 좋아하는 학생 수
				
				// 해당 학생의 friend 배열의 index 산출
				for(int i = 0; i < friend.length; i++) {
					if(friend[i][0] == studentNo) {
						index = i;
						break;
					}
				}
				
				// 사방탐색
				for(int i = 0; i < delta.length; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];
					
					if(nr < 0 || nc < 0 || nr >= size || nc >= size)
						continue;
					
					// 좋아아는 학생이 있는지 확인
					for(int j = 1; j < friend[index].length; j++)
						if(friend[index][j] == room[nr][nc]) f++;
				}
				
				// 만족도 조사
				if(f == 1) sum += 1;
				else if(f == 2) sum += 10;
				else if(f == 3) sum += 100;
				else if(f == 4) sum += 1000;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int i = 0; i < student; i++) {
			int studentNo = friend[i][0];

			// 자리 선정
			int[] seat = findSeat(i);
			
			// 해당 자리에 학생 등록
			room[seat[0]][seat[1]] = studentNo;
		}
		
		System.out.println(satisfaction());
	}
}
