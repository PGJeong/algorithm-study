import java.util.Scanner;

public class Main {
	private static int size; // list size
	private static int[] list;
	private static int score; // 태수 점수
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // list에 들어있는 점수 개수
		score = sc.nextInt();
		size = sc.nextInt();
		list = new int[size];
		
		for(int i = 0; i < size; i++)
			list[i] = -1;
					
		for(int i = 0; i < n; i++)
			list[i] = sc.nextInt();
		
		sc.close();
	}
	
	public static void main(String[] args) {
		init();
		int rank = 1; // 등수
		int same = 0; // 동점자
		
		for(int i = 0; i < size; i++) {
			if(list[i] > score) rank++;
			else if(list[i] == score) same++;
			else break;
		}
		
		if(rank + same > size) System.out.println(-1);
		else System.out.println(rank);
	}
}
