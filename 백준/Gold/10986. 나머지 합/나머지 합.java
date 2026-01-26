import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sums = new long[N + 1];
        long[] count = new long[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
            count[(int) (sums[i] % M)]++;
        }

        long answer = count[0];

        for (int i = 0; i < M; i++) {
            long num = count[i];
            answer += num * (num - 1) / 2;
        }

        System.out.println(answer);
    }
}
