import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size;	// 스위치 개수
		int[] state;// 스위치
		int stud;	// 학생 수
		
		size = sc.nextInt();
		state = new int[size];
		for(int i = 0; i < size; i++)
			state[i] = sc.nextInt();
		stud = sc.nextInt();
		
		for(int i = 0; i < stud; i++) {
			int sex = sc.nextInt(); // 학생 성별
			int num = sc.nextInt(); // 학생이 받은 수 (0-based 변환 필요)
			int tmp;
			
			switch(sex) {
			case 1: // 남학생
				tmp = 1;
				while(true) {
					if(num*tmp > size) break;
					state[num*tmp-1] = (state[num*tmp-1] == 0 ? 1 : 0);
					tmp++;
				}
				break;
				
			case 2: // 여학생
				tmp = 1;
				while(true) {
					if(num-tmp < 1 || num+tmp > size || state[num-tmp-1] != state[num+tmp-1]) break;
					state[num-tmp-1] = (state[num-tmp-1] == 0 ? 1 : 0);
					state[num+tmp-1] = (state[num+tmp-1] == 0 ? 1 : 0);
					tmp++;
				}
				state[num-1] = (state[num-1] == 0 ? 1 : 0);
				break;
			}
		}
		
		for(int i = 0; i < size; i++) {
			if(i != 0 && i % 20 == 0) System.out.println(); // 20개마다 개행
			System.out.print(state[i] + " ");
		}
		
		sc.close();
	}
}
