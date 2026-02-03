import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Node {
    int index, value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Deque<Node> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (!deque.isEmpty() && deque.getFirst().index < i-L+1) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.getLast().value > num) {
                deque.removeLast();
            }

            deque.addLast(new Node(i, num));

            result.append(deque.getFirst().value).append(" ");
        }

        System.out.println(result);
    }
}
