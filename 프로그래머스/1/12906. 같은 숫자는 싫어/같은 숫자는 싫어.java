import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        int prev = -1;
        
        for (int n : arr) {
            if (n != prev) {
                prev = n;
                queue.addLast(n);
            }
        }
        
        int[] answer = new int[queue.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = queue.removeFirst();
        }
        
        return answer;
    }
}
