import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        
        for (int s : scoville) {
            pq.add(s);
        }
        
        while (!pq.isEmpty() && pq.peek() < K) {
            int f1 = pq.poll();
            
            if (pq.isEmpty()) return -1;
            
            int f2 = pq.poll();      
            int nf = f1 + (f2 * 2);
            
            pq.add(nf);
            count++;
        }
        
        return count;
    }
}
