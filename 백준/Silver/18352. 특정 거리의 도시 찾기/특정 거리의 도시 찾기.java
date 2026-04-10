import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    public int vertex, weight;

    public Edge(int v, int w) {
        vertex = v;
        weight = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}

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

        // Dijkstra
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.weight > dist[curr.vertex]) {
                continue;
            }

            for (int v : graph[curr.vertex]) {
                int newDist = curr.weight + 1;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Edge(v, newDist));
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
