import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point2 {
	public int r, c;
	public Point2(int r, int c) { this.r = r; this.c = c; }
	@Override
	public boolean equals(Object obj) {
		if(this.r == ((Point2)obj).r && this.c == ((Point2)obj).c)
			return true;
		else
			return false;
	}
}

class Move {
	public int sec;
	public String dir;
	public Move(int sec, String dir) { this.sec = sec; this.dir = dir; }
}

public class Main {
	private static int size;
	private static int k; // 사과의 개수
	private static int l; // 방향전환 횟수
	private static int[][] map;  // 0: 빈 칸, 1: 사과
	private static Queue<Move> moves; // 방향전환 명령
	
	private static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북쪽(상)부터 시계방향
	private static int currentDir; // 현재 진행방향 (우90 회전 시 +1, 좌90 회전 시 -1)
	
	private static ArrayList<Point2> snake; // 뱀
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			// (오류수정 1.) 입력값이 1-based index로 주어지므로 0-based index로 변환 (index - 1)
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		
		l =Integer.parseInt(br.readLine());
		moves = new LinkedList<>();
		
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			moves.add(new Move(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		snake = new ArrayList<>();
		
		snake.add(0, new Point2(0, 0)); // 초기 뱀의 길이는 1, 뱀의 위치는 0,0
		currentDir = 1; // 초기 진행방향은 오른쪽
	}
	
	private static int game() {
		int sec = 0; // 시간(초)
		
		Move next = moves.poll();
		
		while(true) {
			// 한 번 이동 시 1초++
			sec++;
						
			// 방향전환 명령 확인
			if(next != null && sec == next.sec + 1) { // (오류수정 2.) "X초가 끝난 뒤에 회전"이라고 함
				switch(next.dir) {
				case "L": // 왼쪽으로 90회전
					currentDir = (currentDir + 3) % 4;
					break;
				case "D": // 오른쪽으로 90회전
					currentDir = (currentDir + 1) % 4;
					break;
				}
				next = moves.poll();
			}
			
			// 이동할 좌표
			int nr = snake.get(0).r + delta[currentDir][0];
			int nc = snake.get(0).c + delta[currentDir][1];
			
			// (종료조건 1.) 벽에 부딪히면 게임종료
			if(nr < 0 || nc < 0 || nr >= size || nc >= size)
				break;
			
			// (종료조건 2.) 자기 자신의 몸에 부딪히면 게임종료
			if(snake.contains(new Point2(nr, nc))) // contains()는 내부적으로 객체의 equals()를 사용하여 요소와 비교
				break;
			
			// 몸길이를 늘려 머리를 다음칸에 위치
			snake.add(0, new Point2(nr, nc));
			
			// 이동한 칸에 사과가 있으면, 사과는 없어지고 꼬리는 변동없음
			if(map[nr][nc] == 1) {
				map[nr][nc] = 0;
			}
			// 이동한 칸에 사과가 없으면, 몸길이를 줄려 꼬리 삭제
			else {
				snake.remove(snake.size()-1);
			}
		}
		
		return sec;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(game());
	}
}
