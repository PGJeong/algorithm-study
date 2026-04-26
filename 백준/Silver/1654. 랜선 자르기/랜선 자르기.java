import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 개수
        int[] cables = new int[K];
        long min = 1;
        long max = 0;

        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cables[i]);
        }

        // 이분탐색
        long len = 0; // 랜선의 최대 길이

        while (min <= max) {
            long mid = min + (max - min) / 2;

            // mid 길이로 자를 경우 몇 개의 랜선을 만들 수 있는지 계산
            long cnt = 0;

            for (int i = 0; i < K; i++) {
                cnt += cables[i] / mid;

                if (cnt >= N) break;
            }

            if (cnt >= N) {
                // mid 길이로 N개 이상의 랜선을 만들 수 있으면
                min = mid + 1;
                len = mid;

            } else {
                // mid 길이로 N개 이상의 랜선을 만들 수 없으면
                max = mid - 1;
            }
        }

        System.out.println(len);
    }
}
