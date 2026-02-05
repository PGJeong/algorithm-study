import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];   // 수열
        int[] NGE = new int[N]; // 오큰수

        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(NGE, -1);

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && A[stack.getLast()] < A[i]) {
                NGE[stack.removeLast()] = A[i];
            }

            stack.addLast(i);
        }

        for (int i = 0; i < N; i++) {
            res.append(NGE[i]).append(" ");
        }

        System.out.println(res);
    }
}
