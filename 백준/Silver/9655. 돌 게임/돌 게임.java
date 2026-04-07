import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();  // 주어진 돌의 개수
		boolean[] DP = new boolean[1001]; // DP[n] : 주어진 돌이 n개일 때 승자 (상근: true, 창영: false)
		
		// DP 배열 초깃값
		DP[1] = true;
		DP[2] = false;
		DP[3] = true;
		
		// DP 점화식 : DP[i] = !DP[i-1]
		for(int i = 4; i <= n; i++) {
			DP[i] = !DP[i-1];
		}
		
		if(DP[n]) System.out.println("SK");
		else System.out.println("CY");
		
		sc.close();
	}
}
