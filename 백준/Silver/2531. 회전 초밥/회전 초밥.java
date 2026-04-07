import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, d, k, c; // 벨트 위 접시수, 초밥의 가짓수, 연속해서 먹는 접시수, 쿠폰번호
	private static int[] belt; // 회전 초밥 벨트
	
	private static int calc(int index) { // 먹을 수 있는 초밥의 가짓수 계산
		boolean[] check = new boolean[d + 1];
		int count = 0;
		
		for(int i = index; i != (index + k) % N; i = (i + 1) % N) {
			if(!check[belt[i]]) {
				check[belt[i]] = true;
				count++;
			}
		}
		
		if(!check[c]) count++;
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		belt = new int[N];
		
		for(int i = 0; i < N; i++)
			belt[i] = Integer.parseInt(br.readLine());
		
		// solution
		int res = Integer.MIN_VALUE; // 초밥의 가짓수의 최댓값
		
		for(int i = 0; i < N; i++)
			res = Math.max(res, calc(i));
		
		System.out.println(res);
	}
}
