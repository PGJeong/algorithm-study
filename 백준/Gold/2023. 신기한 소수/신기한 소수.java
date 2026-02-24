import java.util.Scanner;

public class Main {
	private static int digit;
	private static StringBuilder answer;
	
	private static boolean isPrime(int num) {
		if(num % 2 == 0)
			return false;
		
		for(int i = 2; i <= num/2; i++) {
			if(num % i == 0)
				return false;
		}
		
		return true;
	}
	
	private static void dfs(int n, int d) {
		if(d == digit) { // 자릿수 초과시
			answer.append(n+"\n");
			return;
		}
		
		for(int i = 1; i < 10; i+=2) { // 1,3,5,7,9
			int tmp = i + n*10;
			
			if(isPrime(tmp)) {
				dfs(tmp, d+1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		digit = Integer.parseInt(sc.next());
		answer = new StringBuilder();
		
		dfs(2, 1);
		dfs(3, 1);
		dfs(5, 1);
		dfs(7, 1);
		
		System.out.println(answer.toString());
	}
}