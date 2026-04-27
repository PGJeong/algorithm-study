import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] prefixSum = new long[N + 1];
        long[] modCount = new long[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            prefixSum[i] = (prefixSum[i - 1] + Integer.parseInt(st.nextToken())) % M;
            modCount[(int)prefixSum[i]]++;
        }

        long answer = modCount[0];

        for (int i = 0; i < M; i++) {
            answer += modCount[i] * (modCount[i] - 1) / 2;
        }

        System.out.println(answer);
    }
}
