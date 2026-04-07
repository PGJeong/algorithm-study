import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	private static int size;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 사방탐색
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		size = Integer.parseInt(sc.nextLine());
		map = new int[size][size];
		visited = new boolean[size][size];
		
		for(int r = 0; r < size; r++) {
			String str = sc.nextLine();
			for(int c = 0; c < size; c++) 
				map[r][c] = (str.charAt(c) == '0' ? 0 : 1);
		}
		sc.close();
	}
	
	private static int DFS(int r, int c) {
		int count = 1; // 단지내 집의 수
		visited[r][c] = true;
		
		// 사방탐색
		for(int i = 0; i < delta.length; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			
			// 좌표가 유효하지 않은 경우
			if(nr < 0 || nc < 0 || nr >= size || nc >= size) continue;
			
			// 이미 방문한 좌표인 경우 || 빈 칸인 경우
			if(visited[nr][nc] || map[nr][nc] == 0) continue;
			
			count += DFS(nr, nc);
			visited[nr][nc] = true;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		init();
		
		int count = 0; // 총 단지 수
		ArrayList<Integer> arr = new ArrayList<>(); // 각 단지내 집의 수
		
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				if(map[r][c] == 1 && visited[r][c] == false) {
					count++;
					arr.add(DFS(r, c));
				}
			}
		}
		
		// arr 오름차순 정렬
		Collections.sort(arr); // 배열 정렬 시 Arrays.sort(), 컬렉션 정렬 시 Collections.sort() 사용
		
		System.out.println(count);
		for(int i : arr)
			System.out.println(i);
	}
}