import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Boolean> map = new HashMap<>();
		
		int n;
		int capacity = 0; // 게임 정원
		int player = 0; // 사람 수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		switch(st.nextToken()) {
		case "Y": capacity = 2; break;
		case "F": capacity = 3; break;
		case "O": capacity = 4; break;
		}
		
		for(int i = 0; i < n; i++) {
			String user = br.readLine();
			if(!map.containsKey(user)) {
				map.put(user, null);
				player++;
			}
		}
		
		System.out.println(player / (capacity - 1));
	}
}
