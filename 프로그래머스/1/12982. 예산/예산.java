import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        
        int answer = 0;
        
        for (int req : d) {
            if (budget - req >= 0) {
                budget -= req;
                answer++;
            }
        }
        
        return answer;
    }
}
