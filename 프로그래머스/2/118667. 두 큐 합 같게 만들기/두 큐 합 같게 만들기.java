import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int length = queue1.length;
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        long q1sum = 0; // q1 원소 합
        long q2sum = 0; // q2 원소 합
        
        for (int i = 0; i < length; i++) {
            q1.addLast(queue1[i]);
            q2.addLast(queue2[i]);
            q1sum += queue1[i];
            q2sum += queue2[i];
        }
        
        if ((q1sum + q2sum) % 2 != 0) {
            // 각 queue의 원소 합이 같을 수 없는 경우
            return -1;
        }
        
        int q1move = 0; // q1 -> q2 이동 원소 수
        int q2move = 0; // q2 -> q1 이동 원소 수
        
        while (q1move <= length * 2 && q2move <= length * 2) {
            if (q1sum == q2sum) {
                return q1move + q2move;
                
            } else if (q1sum > q2sum) {
                // q1 -> q2 이동
                int v = q1.removeFirst();
                q2.addLast(v);
                q1sum -= v;
                q2sum += v;
                q1move++;
                
            } else {
                // q2 -> q1 이동
                int v = q2.removeFirst();
                q1.addLast(v);
                q2sum -= v;
                q1sum += v;
                q2move++;
            }
        }
        
        return -1;
    }
}
