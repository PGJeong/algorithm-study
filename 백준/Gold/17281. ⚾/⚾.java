import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int inning;
	private static int[][] data;
	private static int[] permutation;
	private static boolean[] isSelected;
	private static int max;
	
	private static void simulate() {
		int seq = 0; // 현재차례
		int score = 0; // 누적점수
		
		for(int i = 0; i < inning; i++) {	
			int out = 0; // 누적아웃
			int status = 0; // 현재상황 (비트마스킹 이용)
			
			while(out < 3) {
				int res = data[i][permutation[seq]]; // 현재 타자의 결과
				
				if(res == 0) {
					out++;
				} else {
					status = status << res | 1 << (res - 1); // 기존 주자 이동 | 타자 루에 추가
					score += Integer.bitCount(status >> 3); // 홈에 들어온 주자 수 계산
					status = status & (1 << 3) - 1; // 3루 까지만 유지, 나머지는 0
				}
				
				seq = (seq + 1) % 9;
			}
		}
		
		max = Math.max(max, score);
	}
	
	private static void permute(int cnt) {
		if(cnt == 9) {
			simulate();
			return;
		}
		
		// 4번 타자 고정
		if(cnt == 3) cnt++;
		
		for(int i = 0; i < 9; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				permutation[cnt] = i;
				permute(cnt + 1);
				isSelected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		data = new int[inning][9];
		permutation = new int[9];
		isSelected = new boolean[9];
		max = 0;
		
		for(int i = 0; i < inning; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++)
				data[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 1번 선수 4번 타자 고정
		permutation[3] = 0;
		isSelected[0] = true;	
		permute(0);
		
		System.out.println(max);
	}
}
