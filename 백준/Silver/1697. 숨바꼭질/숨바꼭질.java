import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// init
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt(); // 시작점
		int e = sc.nextInt(); // 도착점
		sc.close();
		
		// BFS 탐색
		Queue<int[]> q = new LinkedList<>(); // q[0]: 현재위치, q[1]: 경과시간
		boolean[] visited = new boolean[100001]; // (오류수정) 도착점이 시작점보다 앞에 있을수도 있고, 시작점/도착점보다 뒤로 갈 수도 있어야 함
		
		q.add(new int[] {s, 0});
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			// 도착점에 도달한 경우
			if(curr[0] == e) {
				System.out.println(curr[1]);
				break;
			}
			
			// (1) x-1 위치로 이동
			if(curr[0]-1 >= 0 && visited[curr[0]-1] == false) {
				q.add(new int[] {curr[0]-1, curr[1]+1});
				visited[curr[0]-1] = true;
			}
			
			// (2) x+1 위치로 이동
			if(curr[0]+1 < visited.length && visited[curr[0]+1] == false) {
				q.add(new int[] {curr[0]+1, curr[1]+1});
				visited[curr[0]+1] = true;
			}
			
			// (3) 2x 위치로 이동
			if(curr[0]*2 < visited.length && visited[curr[0]*2] == false) {
				q.add(new int[] {curr[0]*2, curr[1]+1});
				visited[curr[0]*2] = true;
			}
		}
	}
}
