import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M, V;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder res;

    static void DFS(int v) {
        visited[v] = true;
        res.append(v).append(" ");

        for (int w : graph[v]) {
            if (!visited[w]) {
                DFS(w);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        res = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        // DFS
        visited = new boolean[N + 1];
        DFS(V);

        res.append("\n");

        // BFS
        visited = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(V);
        visited[V] = true;

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            res.append(v).append(" ");

            for (int w : graph[v]) {
                if (!visited[w]) {
                    queue.addLast(w);
                    visited[w] = true;
                }
            }
        }

        System.out.println(res);
    }
}
