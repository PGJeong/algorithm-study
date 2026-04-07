import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dice {
	private static Dice instance = new Dice(); // Singleton Pattern
	public static final int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3, FRONT = 4, BACK = 5;
	public int[] data = new int[6];
	
	public void roll(int dir) {
		// 동(1)
		if(dir == 1) swap(LEFT, TOP, RIGHT, BOTTOM);
		// 서(2)
		else if(dir == 2) swap(RIGHT, TOP, LEFT, BOTTOM);
		// 북(3)
		else if(dir == 3) swap(FRONT, TOP, BACK, BOTTOM);
		// 남(4)
		else if(dir == 4) swap(BACK, TOP, FRONT, BOTTOM);
	}
	
	private void swap(int i0, int i1, int i2, int i3) {
		int temp = data[i3];
		data[i3] = data[i2];
		data[i2] = data[i1];
		data[i1] = data[i0];
		data[i0] = temp;
	}
	
	private Dice() {}
	public static Dice getInstance() { return instance; }
}

public class Main {
	private static int row; // 지도의 세로크기
	private static int col; // 지도의 가로크기
	private static int[][] map;
	
	private static int[] loc; // 주사위 위치
	private static int numOrder; // 명령의 개수
	private static int[] order; // 명령
	
	private static final int[][] delta = {{0, 1}, {0, -1}, {-1, 0} ,{1, 0}}; // 동,서,북,남
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		loc = new int[2];
		loc[0] = Integer.parseInt(st.nextToken());
		loc[1] = Integer.parseInt(st.nextToken());
		numOrder = Integer.parseInt(st.nextToken());
		order = new int[numOrder];
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < numOrder; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static int move(int dir) {
		// map에서 이동할 좌표 계산
		int nr = loc[0] + delta[dir - 1][0];
		int nc = loc[1] + delta[dir - 1][1];
		
		// 좌표 유효성 검사 (유효하지 않으면 -1 return)
		if(nr < 0 || nc < 0 || nr >= row || nc >= col)
			return -1;
		
		// 주사위 위치 이동
		loc[0] = nr; loc[1] = nc;
		
		// 주사위 해당 방향으로 굴리기
		Dice dice = Dice.getInstance();
		dice.roll(dir);
		
		// 이동한 칸에 쓰여있는 수가 0이면, 주사위의 바닥면에 쓰여있는 수가 칸에 복사
		if(map[nr][nc] == 0) {
			map[nr][nc] = dice.data[Dice.BOTTOM];
		}
		// 이동한 칸에 쓰여있는 수가 0이 아니면, 칸에 쓰여있는 수가 주사위의 바닥면에 복사되며, 칸에 쓰여있는 수는 0이 됨
		else {
			dice.data[Dice.BOTTOM] = map[nr][nc];
			map[nr][nc] = 0;
		}
		
		return dice.data[Dice.TOP];
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int i = 0; i < numOrder; i++) {
			int res = move(order[i]);
			if(res != -1) System.out.println(res);
		}
	}
}
