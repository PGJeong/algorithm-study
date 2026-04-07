import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int room; // 시험장 수
	private static int[] people; // 각 시험장의 응시자 수
	private static int a; // 총감독관이 감시할 수 있는 응시자 수
	private static int b; // 부감독관이 감시할 수 있는 응시자 수
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		room = Integer.parseInt(st.nextToken());
		people = new int[room];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < room; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
	}
	
	private static long solution() {
		// (오류수정) "시험장 수", "각 시험장의 응시자 수"가 최대 1,000,000이므로 부감독관의 수는 long 타입에 저장해야 함
		// 총감독관의 수
		long numA = room; // 총감독관은 각각의 시험장에 1명씩
		
		// 부감독관의 수
		long numB = 0;
		for(int i = 0; i < room; i++) {
			if(people[i] - a > 0) // 부감독관이 필요한 경우에만 연산해야 함
				numB += (people[i] - a) / b + ((people[i] - a) % b == 0 ? 0 : 1);
		}
		
		return numA + numB;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solution());
	}
}
