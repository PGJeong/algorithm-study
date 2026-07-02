import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int days = (100 - progresses[i] + speeds[i] - 1) / speeds[i]; // 소요기간(올림)
            q.addLast(days);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int lastDay = q.removeFirst(); // 마지막 배포일
        int deploys = 1; // 현재 배포 Count
        
        while (!q.isEmpty()) {
            int currDay = q.removeFirst();
            
            if (currDay > lastDay) {
                list.add(deploys);
                lastDay = currDay;
                deploys = 1;
                
            } else {
                deploys++;
            }
        }
        
        list.add(deploys);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
