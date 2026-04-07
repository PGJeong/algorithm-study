import java.util.Scanner;

public class Main {
	private static int[] numbers;
	private static boolean[] selected;
	
	private static void permutation(int depth, int n, int r) {
		if(depth == r) {
			for(int num : numbers) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(!selected[i]) {
				numbers[depth] = i;
				selected[i] = true;
				
				permutation(depth + 1, n, r);
				
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		sc.close();
		
		numbers = new int[r];
		selected = new boolean[n + 1];
		
		permutation(0, n, r);
	}
}
