import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder res = new StringBuilder();
		HashSet<Character> set = new HashSet<>();
		int n = Integer.parseInt(sc.nextLine()); // 단어 개수
		
		for(int i = 0; i < n; i++) {
			String word = sc.nextLine();
			int count = 0; // 단어의 몇 번째 글자를 단축키로 지정했는지
			boolean complete = false; // 적용가능한 단축키를 찾았는지
			
			// (1순위) 단어의 첫 글자
			StringTokenizer st = new StringTokenizer(word);
			
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				
				if(!set.contains(Character.toUpperCase(s.charAt(0)))) {
					set.add(Character.toUpperCase(s.charAt(0)));
					complete = true;
					break;
				} else {
					count += s.length() + 1;
				}
			}
			
			// (2순위) 왼쪽부터 단어를 구성하는 알파벳
			if(!complete) {
				for(int j = 0; j < word.length(); j++) {
					if(!set.contains(Character.toUpperCase(word.charAt(j))) && word.charAt(j) != ' ') {
						set.add(Character.toUpperCase(word.charAt(j)));
						count = j;
						complete = true;
						break;
					}
				}
			}
			
			// 출력값
			if(complete) {
				for(int j = 0; j < word.length(); j++) {
					if(j == count) res.append("[" + word.charAt(j) + "]");
					else res.append(word.charAt(j));
				}
				res.append("\n");
			} else {
				res.append(word + "\n");
			}
		}
		
		System.out.print(res.toString());
		sc.close();
	}
}
