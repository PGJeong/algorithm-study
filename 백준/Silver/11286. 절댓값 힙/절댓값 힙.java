import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if (abs1 == abs2) return Integer.compare(o1, o2);
            else return Integer.compare(abs1, abs2);
        });

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                // 출력
                if (pq.isEmpty()) res.append("0\n");
                else res.append(pq.poll()).append("\n");

            } else {
                // 입력
                pq.add(num);
            }
        }

        System.out.println(res);
    }
}
