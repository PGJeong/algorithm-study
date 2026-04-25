import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        HashSet<Integer> los = new HashSet<>(); // 도난 여부
        HashSet<Integer> res = new HashSet<>(); // 여벌 여부
        
        for (int l : lost) {
            los.add(l);
        }
        
        for (int r : reserve) {
            res.add(r);
        }
        
        Arrays.sort(reserve);
        
        int cnt = 0; // 빌려준 체육복 수
        
        for (int r : reserve) {
            if (los.contains(r)) {
                cnt++;
                los.remove(r);
                
            } else if (!res.contains(r - 1) && los.contains(r - 1)) {
                cnt++;
                los.remove(r - 1);
                
            } else if (!res.contains(r + 1) && los.contains(r + 1)) {
                cnt++;
                los.remove(r + 1);
            }
        }
        
        return n - lost.length + cnt;
    }
}
