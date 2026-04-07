import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int a = 1;
		int b = 1;
		
		for(int i = 0; i < M; i++) {
			a *= N;
			N--;
		}
		
		for(int i = 1; i <= M; i++) {
			b *= i;
		}
		
		System.out.println(a / b);
		sc.close();
	}
}
