import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 각 빌딩의 높이
        int[] idx = new int[N]; // 가장 오른쪽에 있는 자신보다 작은 빌딩 인덱스

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 단조 스택

        for (int i = 0; i < N; i++) {
            // 스택에 있는 빌딩의 높이 <= 현재 빌딩의 높이
            while (!stack.isEmpty() && arr[stack.getLast()] <= arr[i]) {
                idx[stack.removeLast()] = i - 1;
            }

            stack.addLast(i);
        }

        // 스택에 남은 빌딩은 모두 idx를 N-1로
        while (!stack.isEmpty()) {
            idx[stack.removeLast()] = N - 1;
        }

        long sum = 0;

        for (int i = 0; i < N; i++) {
            sum += idx[i] - i;
        }

        System.out.println(sum);
    }
}
