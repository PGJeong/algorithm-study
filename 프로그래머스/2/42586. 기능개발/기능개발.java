import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int days = (100 - progresses[i] + speeds[i] - 1) / speeds[i]; // 소요기간(올림)
            q.addLast(days);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int lastDay = 0; // 마지막 배포일
        int deploys = 0; // 현재 배포 Count
        
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
        
        list.remove(0);
        list.add(deploys);
        
        int[] res = new int[list.size()];
        
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}
