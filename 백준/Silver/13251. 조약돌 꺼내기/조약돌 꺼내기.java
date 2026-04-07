import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int color; // 색상의 개수
	private static int[] stone; // 각 색상의 조약돌 개수
	private static int pick; // 뽑을 조약돌 개수
	private static int all = 0; // 전체 조약돌 개수
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		color = Integer.parseInt(br.readLine());
		stone = new int[color];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < color; i++) {
			stone[i] = Integer.parseInt(st.nextToken());
			all += stone[i];
		}
		
		pick = Integer.parseInt(br.readLine());
	}
	
	public static void main(String[] args) throws IOException {
		init();	
		double prob = 0;
		
		for(int i = 0; i < color; i++) {
			double p = 1; // 뽑은 조약돌이 i번째 색상일 확률
			int c = stone[i]; // i번째 색상의 조약돌 개수
			int a = all; // 남은 조약돌 개수
			
			for(int j = 0; j < pick; j++) {
				p *= (double)c / a;
				c--;
				a--;
			}
			
			prob += p;
		}
		
		System.out.println(prob);
	}
}
