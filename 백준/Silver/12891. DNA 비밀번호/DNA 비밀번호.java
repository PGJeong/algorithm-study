import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int S, P, passwords;
    static int[] count, minCount;

    private static void addChar(char c) {
        switch (c) {
            case 'A': count[0]++; break;
            case 'C': count[1]++; break;
            case 'G': count[2]++; break;
            case 'T': count[3]++; break;
        }
    }

    private static void removeChar(char c) {
        switch (c) {
            case 'A': count[0]--; break;
            case 'C': count[1]--; break;
            case 'G': count[2]--; break;
            case 'T': count[3]--; break;
        }
    }

    private static void checkValid() {
        if (count[0] >= minCount[0] &&
            count[1] >= minCount[1] &&
            count[2] >= minCount[2] &&
            count[3] >= minCount[3]) {
            passwords++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken()); // 문자열 길이
        P = Integer.parseInt(st.nextToken()); // 비밀번호 길이
        String str = br.readLine(); // 문자열
        count = new int[4];         // A,C,G,T 개수 카운트
        minCount = new int[4];      // A,C,G,T 최소 개수

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            minCount[i] = Integer.parseInt(st.nextToken());
        }

        passwords = 0;

        for (int i = 0; i < P; i++) {
            addChar(str.charAt(i));
        }

        checkValid();

        for (int i = P; i < S; i++) {
            char prevCh = str.charAt(i - P);
            char newCh = str.charAt(i);

            removeChar(prevCh);
            addChar(newCh);
            checkValid();
        }

        System.out.println(passwords);
    }
}
