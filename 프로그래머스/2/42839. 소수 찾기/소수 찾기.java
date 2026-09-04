import java.util.*;

class Solution {
    boolean[] isSelected;
    HashSet<Integer> checked;
    int count = 0;
    
    void checkPrime(int n) {
        if (checked.contains(n)) return;
        checked.add(n);
        
        if (n < 2) return;
        
        for (int i = 2; (i*i) <= n; i++) {
            if (n % i == 0) return;
        }
        
        count++;
    }
    
    void makeNumber(String n, String numbers) {
        // 현재 번호 확인
        checkPrime(Integer.parseInt(n));
        
        // 자릿수 추가
        for (int i = 0; i < numbers.length(); i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                makeNumber(n + numbers.charAt(i), numbers);
                isSelected[i] = false;
            }
        }
    }
    
    public int solution(String numbers) {
        isSelected = new boolean[numbers.length()];
        checked = new HashSet<>();
        
        for (int i = 0; i < numbers.length(); i++) {
            isSelected[i] = true;
            makeNumber("" + numbers.charAt(i), numbers);
            isSelected[i] = false;
        }
        
        return count;
    }
}
