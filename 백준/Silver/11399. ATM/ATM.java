import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static int persons;
	private static int[] waiting;
	
	public static void main(String[] args) {
		// 입력값 저장
		persons = sc.nextInt();
		
		waiting = new int[persons];
		for(int i = 0; i < persons; i++)
			waiting[i] = sc.nextInt();
		
		// 로직
		Arrays.sort(waiting); // 오름차순 정렬
		
		int answer = 0;
		int temp = 0;
		for(int i = 0; i < persons; i++) {
			temp += waiting[i];
			answer += temp;
		}
		
		System.out.println(answer);
	}
}