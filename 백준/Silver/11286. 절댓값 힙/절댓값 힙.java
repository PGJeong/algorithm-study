import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    // 절댓값이 같으면 가장 작은 수 우선
                    return o1 - o2;

                } else {
                    // 절댓값이 작은 수 우선
                    return Math.abs(o1) - Math.abs(o2);
                }
            }
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
