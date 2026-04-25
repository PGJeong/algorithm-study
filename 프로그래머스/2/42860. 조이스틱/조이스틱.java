class Solution {
    public int solution(String name) {
        int length = name.length();
        int change = 0;         // 알파벳 변경
        int move = length - 1;  // 커서 이동(기본값은 오른쪽으로 끝까지 이동)

        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            
            // 알파벳 변경 횟수 합산
            change += Math.min(c - 'A', 'Z' - c + 1);
            
            // 커서 순회 경로 탐색
            int next = i + 1; // 다음 목표 index
            
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            
            // (1) 원점 -> 현위치에서 좌측으로 유턴 -> 다음 목표
            move = Math.min(move, i * 2 + (length - next));
            
            // (2) 원점에서 좌측으로 -> 다음 목표에서 유턴 -> 현위치
            move = Math.min(move, (length - next) * 2 + i);
        }
        
        return change + move;
    }
}
