import java.util.Scanner;

class Main {
    static int N;
    static StringBuilder res;

    static boolean isPrime(int n) {
        for (int i = 2; (i * i) <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static void DFS(int n, int depth) {
        if (depth == N) {
            res.append(n).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i += 2) {
            int num = n * 10 + i;

            if (isPrime(num)) {
                DFS(num, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        res = new StringBuilder();

        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

        System.out.println(res.toString());
    }
}
