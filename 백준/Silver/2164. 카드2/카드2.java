import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> queue = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            queue.addLast(i);
        }

        while (queue.size() > 1) {
            queue.removeFirst();
            queue.addLast(queue.removeFirst());
        }

        System.out.println(queue.removeFirst());
    }
}
