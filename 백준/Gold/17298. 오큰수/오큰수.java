import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Node {
    public int index, value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] NGE = new int[N]; // 오큰수

        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Node> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.getLast().value < num) {
                Node node = stack.removeLast();
                NGE[node.index] = num;
            }

            stack.addLast(new Node(i, num));
        }

        // 오큰수가 없는 수 처리
        while (!stack.isEmpty()) {
            Node node = stack.removeLast();
            NGE[node.index] = -1;
        }

        for (int i = 0; i < N; i++) {
            res.append(NGE[i]).append(" ");
        }

        System.out.println(res);
    }
}
