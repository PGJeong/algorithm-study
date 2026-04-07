import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int m = Integer.parseInt(br.readLine());
		boolean[] flag = new boolean[21];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int arg;
			
			switch(st.nextToken()) {
			case "add":
				arg = Integer.parseInt(st.nextToken());
				flag[arg] = true;
				break;
				
			case "remove":
				arg = Integer.parseInt(st.nextToken());
				flag[arg] = false;
				break;
				
			case "check":
				arg = Integer.parseInt(st.nextToken());
				if(flag[arg]) sb.append(1 + "\n");
				else sb.append(0 + "\n");
				break;
				
			case "toggle":
				arg = Integer.parseInt(st.nextToken());
				flag[arg] = !flag[arg];
				break;
				
			case "all":
				for(int j = 0; j < flag.length; j++)
					flag[j] = true;
				break;
				
			case "empty":
				for(int j = 0; j < flag.length; j++)
					flag[j] = false;
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}
