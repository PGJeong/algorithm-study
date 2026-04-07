import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int f = sc.nextInt(); // 건물 높이
		int s = sc.nextInt(); // 현재 층수
		int g = sc.nextInt(); // 목표 층수
		int u = sc.nextInt(); // UP
		int d = sc.nextInt(); // DOWN
		sc.close();
		
		// [0]: 현재 층수, [1]: 이동 횟수
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[f + 1];
		
		q.add(new int[] {s, 0});
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			if(curr[0] == g) {
				System.out.println(curr[1]);
				return;
			}
			
			if(curr[0] + u <= f && !visited[curr[0] + u]) {
				q.add(new int[] {curr[0] + u, curr[1] + 1});
				visited[curr[0] + u] = true;
			}
			
			if(curr[0] - d >= 1 && !visited[curr[0] - d]) {
				q.add(new int[] {curr[0] - d, curr[1] + 1});
				visited[curr[0] - d] = true;
			}
		}
		
		System.out.println("use the stairs");
	}
}
