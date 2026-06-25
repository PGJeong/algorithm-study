import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        
        for (String number : phone_book) {
            set.add(number);
        }
        
        for (String number : phone_book) {
            StringBuilder tmp = new StringBuilder();
            
            for (int i = 0; i < number.length() - 1; i++) {
                tmp.append(number.charAt(i));
                
                if (set.contains(tmp.toString())) return false;
            }
        }
        
        return true;
    }
}
