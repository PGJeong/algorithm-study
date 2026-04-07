import java.util.Scanner;

public class Main {
	private static int n; // 조합 nCm
	private static int m;
	private static int[][] dp;
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.close();
		
		dp = new int[n+1][n+1];
	}
	
	public static void main(String[] args) {
		init();
		
		// dp 배열 초기화
		for(int i = 0; i <= n; i++) {
			dp[i][0] = 1; // i개 중 0개 뽑는 경우의 수 = 1
			dp[i][i] = 1; // i개 중 i개 뽑는 경우의 수 = 1
			dp[i][1] = i; // i개 중 1개 뽑는 경우의 수 = i
		}
		
		// 기존 값을 이용하여 빈 칸 채우기
		for(int r = 3; r <= n; r++) {
			for(int c = 2; c <= n; c++) {
				dp[r][c] = dp[r-1][c] + dp[r-1][c-1]; // 조합 점화식
				dp[r][c] %= 10007;
			}
		}
		
		//System.out.println(dp[n][m] % 10007);
		System.out.println(dp[n][m]);
	}
}
