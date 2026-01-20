import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int sum = 0;
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            if (num > max) max = num;
        }

        System.out.println((double) sum / max * 100 / size);
    }
}
