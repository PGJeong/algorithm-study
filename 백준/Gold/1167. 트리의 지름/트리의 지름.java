import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static ArrayList<int[]>[] graph;
    static int V, vertex, weight;
    static boolean[] visited;

    static void dfs(int v, int w) {
        visited[v] = true;

        if (w > weight) {
            weight = w;
            vertex = v;
        }

        for (int[] edge : graph[v]) {
            if (!visited[edge[0]]) {
                dfs(edge[0], w + edge[1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());

                if (to == -1) {
                    break;
                }

                int weight = Integer.parseInt(st.nextToken());

                graph[from].add(new int[] {to, weight});
            }
        }

        weight = 0;
        visited = new boolean[V + 1];
        dfs(1, 0);

        weight = 0;
        visited = new boolean[V + 1];
        dfs(vertex, 0);

        System.out.println(weight);
    }
}
