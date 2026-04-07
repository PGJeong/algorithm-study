import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	private static int len; // 굴다리 길이
	private static int num; // 가로등 개수
	private static int[] loc; // 가로등 위치
	
	private static void init() {
		Scanner sc = new Scanner(System.in);
		len = Integer.parseInt(sc.nextLine());
		num = Integer.parseInt(sc.nextLine());
		loc = new int[num];
		
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		for(int i = 0; i < num; i++)
			loc[i] = Integer.parseInt(st.nextToken());
		
		sc.close();
	}
	
	// 매개변수로 주어진 높이로 굴다리 전역을 커버하는지
	private static boolean isCovered(int height) {
		// 가로등의 커버범위
		int left = loc[0] - height, right = loc[0] + height;
		
		// (1) 우선 굴다리의 시작부분을 커버하는지 확인
		if(left > 0) return false;
		
		// (2) 두 번째 가로등부터 빈 틈없이 굴다리를 커버하는지 순차확인
		for(int i = 1; i < num; i++) {
			// (2-1) 이전 가로등의 오른쪽 커버범위까지, 현재 가로등의 왼쪽 커버범위가 도달하는지
			if(loc[i] - height > right) return false;			
			// (2-2) 가로등의 오른쪽 커버범위 갱신
			right = loc[i] + height;
		}
		
		// (3) 굴다리의 끝부분을 커버하는지 확인
		if(right >= len) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		init();
		
		int min = 1, max = len; // 가능한 가로등의 높이 범위		
		int res = -1; // 가로등 높이 후보
		
		// 이분탐색
		while(min <= max) {
			int mid = (min + max) / 2;
			
			if(isCovered(mid)) {
				max = mid - 1;
				res = mid;
			} else {
				min = mid + 1;
			}
		}
		
		System.out.println(res);
	}
}
