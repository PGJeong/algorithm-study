import java.util.Scanner;

public class Main {
	private static int n; // testCase의 수
	private static int[][] testCase;
	private static int[][] apt; // 아파트
	
	private static void init() {
		Scanner sc = new Scanner(System.in);	
		n = sc.nextInt();
		testCase = new int[n][2];
		
		for(int i = 0; i < n; i++) {
			testCase[i][0] = sc.nextInt();
			testCase[i][1] = sc.nextInt() - 1; // 호수는 1부터 시작
		}
		sc.close();
		
		apt = new int[15][15];
		
		for(int i = 0; i < apt.length; i++) {
			apt[0][i] = i + 1; // 0층의 i호에는 i명이 산다 (호수는 1부터 시작)
			apt[i][0] = 1; // 1호에는 무조건 1명이 산다
		}
	}
	
	public static void main(String[] args) {
		init();
		
		for(int r = 1; r < apt.length; r++) {
			for(int c = 1; c < apt.length; c++) {
				apt[r][c] = apt[r][c-1] + apt[r-1][c];
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.println(apt[testCase[i][0]][testCase[i][1]]);
		}
	}
}
