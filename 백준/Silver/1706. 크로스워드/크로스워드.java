import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();
        
        char[][] map = new char[row][col];
        ArrayList<String> words = new ArrayList<>();
        
        for (int r = 0; r < row; r++) {
            String line = sc.nextLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = line.charAt(c);
            }
        }
        
        for (int r = 0; r < row; r++) {
            StringBuilder word = new StringBuilder();
            for (int c = 0; c < col; c++) {
                if (map[r][c] != '#') {
                    word.append(map[r][c]);
                } else {
                    if (word.length() >= 2) {
                        words.add(word.toString());
                    }
                    word.setLength(0);
                }
            }
            if (word.length() >= 2) {
                words.add(word.toString());
            }
        }
        
        for (int c = 0; c < col; c++) {
            StringBuilder word = new StringBuilder();
            for (int r = 0; r < row; r++) {
                if (map[r][c] != '#') {
                    word.append(map[r][c]);
                } else {
                    if (word.length() >= 2) {
                        words.add(word.toString());
                    }
                    word.setLength(0);
                }
            }
            if (word.length() >= 2) {
                words.add(word.toString());
            }
        }
        
        Collections.sort(words);
        System.out.println(words.get(0));
    }
}
