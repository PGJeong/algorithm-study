import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int p; // 테스트 케이스 수
	private static int[][] tc; // 테스트 케이스 
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		p = Integer.parseInt(br.readLine());
		tc = new int[p][20];
		
		for(int i = 0; i < p; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			for(int j = 0; j < 20; j++) {
				tc[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int i = 0; i < p; i++) {
			int cnt = 0; // 뒤로 물러난 횟수
			
			for(int j = 0; j < 20; j++) {
				for(int k = 0; k < j; k++) {
					// 현재 학생(tc[i][j])보다 앞에 있는 사람 중 키가 큰 학생의 수만큼 더한다
					if(tc[i][k] > tc[i][j]) cnt++;
				}
			}
			System.out.println((i+1) + " " + cnt);
		}
	}
}
