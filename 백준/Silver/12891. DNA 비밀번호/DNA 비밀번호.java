import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()); // 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 비밀번호 길이
        String str = br.readLine();     // 문자열
        int[] count = new int[4];       // A,C,G,T 개수 카운트
        int[] minCount = new int[4];    // A,C,G,T 최소 개수

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            minCount[i] = Integer.parseInt(st.nextToken());
        }

        int passwords = 0;

        for (int i = 0; i < P; i++) {
            char ch = str.charAt(i);

            switch (ch) {
                case 'A': count[0]++; break;
                case 'C': count[1]++; break;
                case 'G': count[2]++; break;
                case 'T': count[3]++; break;
            }
        }

        if (count[0] >= minCount[0] &&
            count[1] >= minCount[1] &&
            count[2] >= minCount[2] &&
            count[3] >= minCount[3]) {
            passwords++;
        }

        for (int i = P; i < S; i++) {
            char prevCh = str.charAt(i - P);
            char newCh = str.charAt(i);

            switch (prevCh) {
                case 'A': count[0]--; break;
                case 'C': count[1]--; break;
                case 'G': count[2]--; break;
                case 'T': count[3]--; break;
            }

            switch (newCh) {
                case 'A': count[0]++; break;
                case 'C': count[1]++; break;
                case 'G': count[2]++; break;
                case 'T': count[3]++; break;
            }

            if (count[0] >= minCount[0] &&
                count[1] >= minCount[1] &&
                count[2] >= minCount[2] &&
                count[3] >= minCount[3]) {
                passwords++;
            }
        }

        System.out.println(passwords);
    }
}
