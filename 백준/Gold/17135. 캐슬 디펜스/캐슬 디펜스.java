import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int row; // 맵의 행 수
	private static int col; // 맵의 열 수
	private static int range; // 유효사거리
	private static boolean[][] map; // 맵
	private static int[] deploy; // 궁수 배치
	private static int max; // 제거한 적의 최댓값
	
	private static int[] findEnemy(int archerCol, int stage, HashSet<List<Integer>> set) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 1순위: 가장 가까운 적
				int dist1 = Math.abs(row - stage - o1[0]) + Math.abs(archerCol - o1[1]); // o1과 궁수의 거리
				int dist2 = Math.abs(row - stage - o2[0]) + Math.abs(archerCol - o2[1]); // o2와 궁수의 거리
				
				if(dist1 != dist2) return dist1 - dist2;
				
				// 2순위: 가장 왼쪽에 있는 적
				else return o1[1] - o2[1];
			}
		});
		
		for(int r = row - 1 - stage; r >= 0; r--) {
			for(int c = 0; c < col; c++) {
				if(map[r][c]) { // 적이 있는 경우
					int dist = Math.abs(row - stage - r) + Math.abs(archerCol - c); // 궁수와의 거리
					if(dist <= range) { // 유효사거리 내에 있는 경우
						if(!set.contains(Arrays.asList(r, c))) { // 이미 제거한 적이 아니면
							pq.add(new int[] {r, c});
						}
					}
				}
			}
		}
		
		return pq.poll();
	}
	
	private static int simulation(int[] deploy) {
		HashSet<List<Integer>> set = new HashSet<>(); // 제거한 적의 정보
		int[][] killed = new int[deploy.length][2]; // 한 번의 공격에 제거한 적의 정보

		// stage: 공격 횟수(맵 내의 적을 옮기는 대신 stage 변수 사용)
		for(int stage = 0; stage < row; stage++) {
			for(int i = 0; i < deploy.length; i++) {
				int[] enemy = findEnemy(deploy[i], stage, set);
				if(enemy != null) {
					killed[i][0] = enemy[0];
					killed[i][1] = enemy[1];
				} else {
					killed[i][0] = -1;
					killed[i][1] = -1;
				}
			}
			
			// 같은 적이 여러 궁수에게 공격당할 수 있으므로 한 번의 공격이 끝날 때마다 set에 한꺼번에 추가
			for(int i = 0; i < deploy.length; i++) {
				if(killed[i][0] != -1) {
					set.add(Arrays.asList(killed[i][0], killed[i][1]));
				}
			}
		}
		
		return set.size(); // 제거한 적의 수 return
	}
	
	private static void combination(int start, int depth) {
		if(depth == 3) {
			// 궁수 3명 배치가 완료된 경우
			max = Math.max(max, simulation(deploy));
			return;
		}
		
		for(int i = start; i < col; i++) {
			deploy[depth] = i;
			combination(i + 1, depth + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		map = new boolean[row][col];
		deploy = new int[3];
		max = 0;
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < col; c++) {
				map[r][c] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
			}
		}
		
		combination(0, 0);
		System.out.println(max);
	}
}
