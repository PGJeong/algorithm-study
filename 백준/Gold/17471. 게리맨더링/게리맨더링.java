import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int zones;
	static int[] population;
	static boolean[][] graph;
	static boolean[] zone1; // 1선거구
	static boolean[] zone2; // 2선거구
	static boolean[] visited;
	static int popu1; // 1선거구 인구
	static int popu2; // 2선거구 인구
	static int min;
	
	static void DFS(int zoneNo, boolean[] zone) {
		visited[zoneNo] = true;
		
		for(int i = 0; i < zones; i++)
			if(graph[zoneNo][i] && zone[i] && !visited[i]) DFS(i, zone);
	}
	
	static boolean isValid() {
		boolean flag1 = false; // zone1에 최소 하나의 선거구가 존재하는지
		boolean flag2 = false; // zone2에 최소 하나의 선거구가 존재하는지
		popu1 = 0;
		popu2 = 0;
		
		// 1선거구 유효성 확인
		visited = new boolean[zones];
		for(int i = 0; i < zones; i++) {
			if(zone1[i]) { DFS(i, zone1); break; }
		}
		
		for(int i = 0; i < zones; i++) {
			if(zone1[i]) { flag1 = true; popu1 += population[i]; }
			if(visited[i] != zone1[i]) return false;
		}
		
		// 2선거구 유효성 확인
		visited = new boolean[zones];
		for(int i = 0; i < zones; i++) {
			if(zone2[i]) { DFS(i, zone2); break; }
		}
		
		for(int i = 0; i < zones; i++) {
			if(zone2[i]) { flag2 = true; popu2 += population[i]; }
			if(visited[i] != zone2[i]) return false;
		}
		
		if(flag1 && flag2) return true;
		else return false;
	}
	
	static void subset(int index) {
		if(index == zones) {
			for(int i = 0; i < zones; i++)
				zone2[i] = !zone1[i];
			
			if(isValid()) { // 2개의 선거구가 유효하면
				min = Math.min(min, Math.abs(popu1 - popu2));
			}
			
			return;
		}
		
		zone1[index] = true;
		subset(index + 1);
		
		zone1[index] = false;
		subset(index + 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		zones = Integer.parseInt(br.readLine());
		population = new int[zones];
		graph = new boolean[zones][zones];
		zone1 = new boolean[zones];
		zone2 = new boolean[zones];
		min = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < zones; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < zones; i++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < adj; j++)
				graph[i][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		
		subset(0);
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
}
