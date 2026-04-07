import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 수열의 길이
		int p = sc.nextInt(); // 소문제 번호
		long[] factorial = new long[n];
		boolean[] used = new boolean[n];
		
		factorial[0] = 1;
		for(int i = 1; i < factorial.length; i++)
			factorial[i] = factorial[i - 1] * (i + 1);
		
		if(p == 1) { // (소문제 1) k번째 순열 구하기
			long k = sc.nextLong();
			
			for(int i = 0; i < n - 1; i++) {
				int seq = 0; // e.g. seq가 1이면 사용되지 않은 숫자 중 1번째 숫자
				
				while(k > seq * factorial[n - i - 2]) seq++;
				
				k -= (seq - 1) * factorial[n - i - 2];
				// System.out.println("[debug] seq = " + seq);
				
				int cnt = 0;
				int num = 0; // 사용되지 않은 숫자 중 seq번째 숫자
				
				for(int j = 0; j < used.length; j++) {
					num++;
					cnt++;
					if(used[num - 1]) cnt--;
					if(cnt == seq) break;
				}
				
				used[num - 1] = true;
				System.out.print(num + " ");
			}
			
			for(int i = 0; i < used.length; i++)
				if(!used[i]) System.out.println(i + 1);
			
		} else if(p == 2) { // (소문제 2) 순열의 순서 구하기
			int[] permu = new int[n];
			
			for(int i = 0; i < n; i++)
				permu[i] = sc.nextInt();
			
			long k = 1;
			
			for(int i = 0; i < n - 1; i++) {
				int seq = 0;
				
				for(int j = 0; j < permu[i] - 1; j++) { // 현재 숫자: permu[i]
					if(used[j]) continue;
					seq++;
				}
				
				used[permu[i] - 1] = true;
				k += factorial[n - i - 2] * seq;
			}
			
			System.out.println(k);
		}
		
		sc.close();
	}
}
