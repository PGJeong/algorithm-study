import java.util.*;

class Process {
    public int pri, loc; // 우선순위, 위치
    
    public Process(int pri, int loc) {
        this.pri = pri;
        this.loc = loc;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayDeque<Process> q = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a, b) * -1;
        });
        
        for(int i = 0; i < priorities.length; i++) {
            q.addLast(new Process(priorities[i], i));
            pq.add(priorities[i]);
        }
        
        int count = 0;
        
        while(!q.isEmpty()) {
            Process proc = q.removeFirst();
            int pri = pq.poll();
            
            if(proc.pri != pri) {
                // 큐에 대기중인 우선순위가 더 높은 프로세스가 있으면
                q.addLast(proc);
                pq.add(pri);
                
            } else {
                // 현재 실행 가능한 프로세스면
                count++;
                
                if(location == proc.loc) return count;
            }
        }
        
        return -1;
    }
}
