import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int vertex, weight;

    public Node(int v, int w) {
        vertex = v;
        weight = w;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder res = new StringBuilder();

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine()); // start vertex

        ArrayList<Node>[] graph = new ArrayList[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from
            int v = Integer.parseInt(st.nextToken()); // to
            int w = Integer.parseInt(st.nextToken()); // weight

            graph[u].add(new Node(v, w));
        }

        // Dijkstra
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            visited[cur.vertex] = true;

            for (Node n : graph[cur.vertex]) {
                if (visited[n.vertex]) continue;

                int newDist = cur.weight + n.weight;

                if (newDist < dist[n.vertex]) {
                    dist[n.vertex] = newDist;
                    pq.add(new Node(n.vertex, newDist));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) res.append("INF\n");
            else res.append(dist[i]).append("\n");
        }

        System.out.println(res);
    }
}
