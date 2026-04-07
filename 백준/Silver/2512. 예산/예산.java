import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static int n; // 지방의 수
	private static Integer[] req; // 예상요청
	private static int budget; // 예산총액
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		n = Integer.parseInt(br.readLine());
		req = new Integer[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			req[i] = Integer.parseInt(st.nextToken());
		
		budget = Integer.parseInt(br.readLine());
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		// 예산요청 내림차순 정렬
		Arrays.sort(req, Collections.reverseOrder());
		
		// 가능한 상한액 범위 (min~max)
		int min = 0;
		int max = req[0];
		
		// 상한액 후보
		int res = 0;
		
		// 이분탐색
		while(min <= max) {
			int mid = (min + max) / 2;
			
			int sum = 0; // mid값이 상한액일 때 소요예산
			for(int i = 0; i < n; i++) {
				if(req[i] < mid) sum += req[i];
				else sum += mid;
			}
			
			if(sum <= budget) {
				min = mid + 1;
				res = mid;
			} else {
				max = mid - 1;
			}
		}
		
		System.out.println(res);
	}
}
