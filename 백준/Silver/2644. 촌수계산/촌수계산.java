import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine()); // 관계 수
		ArrayList<Integer>[] releation = new ArrayList[N+1]; // 관계
		boolean[] visited = new boolean[N+1];
		
		for(int i = 0; i < releation.length; i++)
			releation[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			releation[a].add(b);
			releation[b].add(a);
		}
		
		// solution
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {p1, 0}); // [0]:사람, [1]:촌수
		visited[p1] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			if(curr[0] == p2) {
				System.out.println(curr[1]);
				return;
			}
			
			for(int i = 0; i < releation[curr[0]].size(); i++) {
				if(visited[releation[curr[0]].get(i)] == false) { 
					q.add(new int[] {releation[curr[0]].get(i), curr[1] + 1});
					visited[releation[curr[0]].get(i)] = true;
				}
			}
		}
		
		System.out.println(-1);
	}
}
