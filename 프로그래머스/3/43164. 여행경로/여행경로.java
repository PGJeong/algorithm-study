import java.util.*;

class Solution {
    String[][] tickets;
    boolean[] isUsed; // 항공권 사용여부
    String[] path; // 방문 경로
    boolean flag;
    
    void search(int count) {
        if(count == path.length) {
            flag = true;
            return;
        }
        
        if(flag) return;
        
        for(int i = 0; i < tickets.length; i++) {
            if(!flag && !isUsed[i] && tickets[i][0].equals(path[count - 1])) {
                isUsed[i] = true;
                path[count] = tickets[i][1];
                search(count + 1);
                isUsed[i] = false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        Arrays.sort(tickets, (a, b) -> {
           return a[1].compareTo(b[1]);
        });
        
        isUsed = new boolean[tickets.length];
        path = new String[tickets.length + 1];
        path[0] = "ICN";
        search(1);
        
        return path;
    }
}
