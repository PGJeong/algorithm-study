import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int size;
	private static int[][] map;
	private static int[] heart = {-1, -1}; // 심장 좌표
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			String s = br.readLine();
			for(int j = 0; j < size; j++) {
				if(s.charAt(j) == '_') {
					map[i][j] = 0;
				} else {
					map[i][j] = 1;
					if(heart[0] == -1) heart = new int[]{i + 1, j}; // 심장 좌표
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		int armL = 0, armR = 0, waist = 0, legL = 0, legR = 0;
		int delta[], nr, nc;
		
		// 왼쪽 팔
		nr = heart[0]; nc = heart[1];
		delta = new int[] {0, -1}; // 왼쪽으로 이동
		while(true) {
			nr += delta[0];
			nc += delta[1];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size || map[nr][nc] == 0) break;
			armL++;
		}
		
		// 오른쪽 팔
		nr = heart[0]; nc = heart[1];
		delta = new int[] {0, 1}; // 오른쪽으로 이동
		while(true) {
			nr += delta[0];
			nc += delta[1];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size || map[nr][nc] == 0) break;
			armR++;
		}
		
		// 허리
		nr = heart[0]; nc = heart[1];
		delta = new int[] {1, 0}; // 아래쪽으로 이동
		while(true) {
			nr += delta[0];
			nc += delta[1];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size || map[nr][nc] == 0) break;
			waist++;
		}
		
		// 왼쪽 다리
		nr = heart[0] + waist; nc = heart[1] - 1;
		delta = new int[] {1, 0}; // 아래쪽으로 이동
		while(true) {
			nr += delta[0];
			nc += delta[1];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size || map[nr][nc] == 0) break;
			legL++;
		}
		
		// 오른쪽 다리
		nr = heart[0] + waist; nc = heart[1] + 1;
		delta = new int[] {1, 0}; // 아래쪽으로 이동
		while(true) {
			nr += delta[0];
			nc += delta[1];
			if(nr < 0 || nc < 0 || nr >= size || nc >= size || map[nr][nc] == 0) break;
			legR++;
		}
		
		System.out.println((heart[0] + 1) + " " + (heart[1] + 1));
		System.out.println(armL + " " + armR + " " + waist + " " + legL  + " " + legR);
	}
}
