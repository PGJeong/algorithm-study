import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {
    static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                map[i][j] = (c == '1');
            }
        }

        // [0]: row, [1]: col, [2]: count
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{0, 0, 1});
        map[0][0] = false; // 방문처리

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                System.out.println(cur[2]);
                break;
            }

            for (int i = 0; i < delta.length; i++) {
                int nr = cur[0] + delta[i][0];
                int nc = cur[1] + delta[i][1];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if (map[nr][nc]) {
                    queue.addLast(new int[]{nr, nc, cur[2] + 1});
                    map[nr][nc] = false; // 방문처리
                }
            }
        }
    }
}
