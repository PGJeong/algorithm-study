import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// init
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		
		// (1) a의 개수 count
		int count = 0;
		for(int i = 0; i < input.length(); i++)
			if(input.charAt(i) == 'a') count++;
		
		// (2) count 크기의 구간에 들어갈 수 있는 b개수의 최소
		// 구간 내 존재하는 b를 구간 외부의 a와 교환하여 구간 내 문자를 모두 a로 만듦
		int si = 0;		// 구간 시작점 초기값
		int ei = count;	// 구간 종료점 초기값
		int min = Integer.MAX_VALUE; // 구간 내 b개수의 최소
		
		// count 크기의 모든 구간 탐색
		for(int i = 0; i < input.length(); i++) {
			int s = (si + i) % input.length(); // 구간 시작점
			int e = (ei + i) % input.length(); // 구간 종료점
			int tmp = 0; // 구간 내 b개수
			
			for(int j = s; j != e; j = (j+1) % input.length())
				if(input.charAt(j) == 'b') tmp++;
			
			min = Math.min(min, tmp);
		}
		
		System.out.println(min);
	}
}