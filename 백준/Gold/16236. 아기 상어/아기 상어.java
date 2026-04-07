import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int size;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] start; // 아기상어 위치
	private static int lv; // 아기상어 크기
	private static int eat; // 먹은 물고기 수
	
	private static final int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		map = new int[size][size];
		lv = 2;
		eat = 0;
		
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 아기상어 위치 저장
				if(map[i][j] == 9) {
					start = new int[] {i, j, 0};
					// 물고기와 혼동되지 않도록 map에서 삭제
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static int BFS(int[] loc) {
		// loc[0] : row
		// loc[1] : col
		// loc[2] : dist (아기상어로부터의 거리)
		
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			// PriorityQueue는 기본적으로 최소 힙 구조로, 가장 작은 값을 우선적으로 처리
			// 음수(-): o1이 o2보다 높은 우선순위, 양수(+): o1이 o2보다 낮은 우선순위, (0): 동일한 우선순위
			@Override
			public int compare(int[] o1, int[] o2) {
				// (1순위) 가장 가까운 물고기
				if(o1[2] != o2[2]) return o1[2] - o2[2];
				// (2순위) 가장 위에 있는 물고기
				else if(o1[0] != o2[0]) return o1[0] - o2[0];
				// (3순위) 가장 왼쪽에 있는 물고기
				else if(o1[1] != o2[1]) return o1[1] - o2[1];
				
				return 0;
			};
		});
		
		// 시작노드 삽입
		q.add(loc);
		visited[loc[0]][loc[1]] = true;
		
		while(!q.isEmpty()) {
			int[] next = q.poll();
			
			// 먹을 물고기가 있으면
			if(map[next[0]][next[1]] < lv && map[next[0]][next[1]] != 0) {
				eat++;
				map[next[0]][next[1]] = 0;
				
				if(eat == lv) {
					lv++;
					eat = 0;
				}
				
				// 아기상어 위치 갱신
				start = new int[] {next[0], next[1], 0};
				
				// 이동거리 반환
				return next[2];
			}
			
			// 사방탐색
			for(int i = 0; i < delta.length; i++) {
				int nr = next[0] + delta[i][0];
				int nc = next[1] + delta[i][1];
				
				// 좌표 유효성 검사
				if(nr < 0 || nc < 0 || nr >= size || nc >= size)
					continue;
				
				// 자신보다 큰 물고기가 있는 칸은 통과 X
				if(map[nr][nc] > lv)
					continue;
				
				// 방문여부 체크
				if(visited[nr][nc] == true)
					continue;
				
				q.add(new int[] {nr, nc, next[2]+1});
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		init();		
		int count = 0;
		
		while(true) {
			visited = new boolean[size][size];
			int dist = BFS(start);
			
			// 먹을 물고기가 없을 때까지 반복
			if(dist == -1) break;
			
			count += dist;
		}
		
		System.out.println(count);
	}
}
