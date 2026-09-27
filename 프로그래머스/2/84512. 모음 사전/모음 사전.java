import java.util.*;

class Solution {
    String[] alphabet = {"A", "E", "I", "O", "U"};
    ArrayList<String> dict;
    
    void makeWord(String word, int len) {
        if(len == 5) return;
        
        for(String c : alphabet) {
            dict.add(word + c);
            makeWord(word + c, len + 1);
        }
    }
    
    public int solution(String word) {
        dict = new ArrayList<>();
        makeWord("", 0);
        
        for(int i = 0; i < dict.size(); i++) {
            if(dict.get(i).equals(word)) {
                return i + 1;
            }
        }
        
        return -1;
    }
}
