import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        
        for (String number : phone_book) {
            set.add(number);
        }
        
        for (String number : phone_book) {
            for (int i = 0; i < number.length() - 1; i++) { // 자기자신 비교 방지
                if (set.contains(number.substring(0, i + 1))) return false; // 0 ~ i-1까지 파싱
            }
        }
        
        return true;
    }
}
