import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken()); // 거리 정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시

        ArrayList<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }

        // BFS
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(dist, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        dist[X] = 0;
        queue.addLast(X);
        visited[X] = true;

        while (!queue.isEmpty()) {
            int curr = queue.removeFirst();

            if (dist[curr] >= K) {
                continue;
            }

            for (int v : graph[curr]) {
                if (!visited[v]) {
                    queue.addLast(v);
                    visited[v] = true;
                    dist[v] = dist[curr] + 1;
                }
            }
        }
        
        StringBuilder res = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                res.append(i).append("\n");
            }
        }

        if (res.length() > 0) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }

    }
}
