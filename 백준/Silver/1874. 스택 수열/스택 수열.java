import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();
        int cnt = 2; // 오름차순 수
        
        stack.addLast(1);
        res.append("+\n");

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine()); // 현재 수열 수

            if (num >= cnt) {
                while (num >= cnt) {
                    stack.addLast(cnt);
                    res.append("+\n");
                    cnt++;
                }

                stack.removeLast();
                res.append("-\n");

            } else {
                if (num == stack.getLast()) {
                    stack.removeLast();
                    res.append("-\n");

                } else {
                    res.setLength(0);
                    res.append("NO");
                    break;
                }
            }
        }

        System.out.println(res);
    }
}
