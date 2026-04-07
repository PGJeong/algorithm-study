import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // number of items
		int k = Integer.parseInt(st.nextToken()); // maximum weight
		int[] w = new int[n+1]; // weights
		int[] v = new int[n+1]; // values
		int[][] dp = new int[n+1][k+1]; // DP array
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= k; j++) {
				if(j - w[i] >= 0) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i]);
				else dp[i][j] = dp[i-1][j];
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
