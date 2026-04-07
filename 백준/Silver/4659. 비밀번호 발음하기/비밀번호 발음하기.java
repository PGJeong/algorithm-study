import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder res = new StringBuilder();
		
		while(true) {
			String tc = br.readLine(); // 테스트 케이스
			
			// 종료조건
			if(tc.equals("end")) break;
			
			boolean isAcceptable = false; // 단어 평가 결과			
			int countConsonant = 0;	// 모음 연속 카운트
			int countVowel = 0;		// 자음 연속 카운트
			
			Character prev = '\0';	// 이전 문자
			Character curr;			// 현재 문자
			
			for(int i = 0; i < tc.length(); i++) {
				curr = tc.charAt(i);
				
				// (조전1) 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
				if(curr == 'a' || curr == 'e' || curr == 'i' || curr == 'o' || curr == 'u') {
					isAcceptable = true;
				}
				
				// (조건2) 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
				if(curr == 'a' || curr == 'e' || curr == 'i' || curr == 'o' || curr == 'u') {
					countConsonant++;
					countVowel = 0;
				} else {
					countVowel++;
					countConsonant = 0;
				}
				
				if(countConsonant >= 3 || countVowel >= 3) {
					isAcceptable = false;
					break;
				}
				
				// (조건3) 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
				if(curr != 'e' && curr != 'o' && curr == prev) {
					isAcceptable = false;
					break;
				}
				
				prev = curr;
			}
			
			if(isAcceptable) res.append("<" + tc + "> is acceptable.\n");
			else res.append("<" + tc + "> is not acceptable.\n");
		}
		
		System.out.println(res.toString());
	}
}
