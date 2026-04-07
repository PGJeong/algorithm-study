import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static String exp; // expression
	private static int len; // length of expression
	private static int max;

	private static int calc(int n1, char op, int n2) {
		if (op == '+') return n1 + n2;
		else if (op == '-') return n1 - n2;
		else return n1 * n2;
	}

	private static void DFS(int index, boolean parentheses, int res) {
		if(index >= len) {
			max = Math.max(max, res);
			return;
		}
		
		int newres;
		
		if(parentheses) {
			// 괄호를 사용한 경우
			int tmp = calc(exp.charAt(index+1) - '0', exp.charAt(index+2), exp.charAt(index+3) - '0');
			newres = calc(res, exp.charAt(index), tmp);
			
			// 재귀호출
			if(index+7 < len) DFS(index+4, true, newres);
			DFS(index+4, false, newres);
			
		} else {
			// 괄호를 사용하지 않은 경우
			newres = calc(res, exp.charAt(index), exp.charAt(index+1) - '0');
			
			// 재귀호출
			if(index+5 < len) DFS(index+2, true, newres);
			DFS(index+2, false, newres);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		len = Integer.parseInt(br.readLine());
		exp = br.readLine();

		max = Integer.MIN_VALUE;
		
		if(1+3 < len) DFS(1, true, exp.charAt(0) - '0');
		DFS(1, false, exp.charAt(0) - '0');
		
		System.out.println(max);
	}
}
