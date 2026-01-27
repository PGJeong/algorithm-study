import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int count = 0;
        int start = 0;
        int end = N - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];

            if (sum == M) {
                count++;
                start++;
                end--;
            }
            else if (sum < M) {
                start++;
            }
            else {
                end--;
            }
        }

        System.out.println(count);
    }
}
