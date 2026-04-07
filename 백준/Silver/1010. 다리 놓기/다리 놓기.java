import java.util.Scanner;

public class Main {
	private static int n; // 테스트케이스 개수
	private static int[][] tc; // 테스트케이스
	private static int[][] dp;
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		tc = new int[n][2];
		
		for(int i = 0; i < n; i++) {
			tc[i][0] = sc.nextInt();
			tc[i][1] = sc.nextInt();
		}
		
		sc.close();
		
		dp = new int[30][30];
	}
	
	public static void main(String[] args) {
		init();
		
		for(int i = 0; i < dp.length; i++ ) {
			dp[i][i] = 1; // i개 중 i개 선택하는 경우의 수 1
			dp[i][0] = 1; // i개 중 0개 선택하는 경우의 수 1
			dp[i][1] = i; // i개 중 1개 선택하는 경우의 수 i
		}
		
		for(int r = 3; r < dp.length; r++) {
			for(int c = 2; c < dp[0].length; c++) {
				dp[r][c] = dp[r-1][c] + dp[r-1][c-1]; // 조합 점화식
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println(dp[tc[i][1]][tc[i][0]]);
		}
	}
}