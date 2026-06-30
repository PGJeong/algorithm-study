import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>((n1, n2) -> n1 - n2); // 최솟값 PQ
        PriorityQueue<Integer> max = new PriorityQueue<>((n1, n2) -> n2 - n1); // 최댓값 PQ
        HashMap<Integer, Integer> cnt = new HashMap<>(); // 각 숫자의 개수
        
        for (String s : operations) {
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken(); // 명령어
            int num = Integer.parseInt(st.nextToken()); // 데이터
            
            if (op.equals("I")) {
                // 삽입
                min.add(num);
                max.add(num);
                cnt.put(num, cnt.getOrDefault(num, 0) + 1);
                
            } else {
                // 삭제
                if (num == 1) {
                    // 최댓값 삭제
                    while (!max.isEmpty()) {
                        int tmp = max.poll();
                        
                        if (cnt.get(tmp) <= 0) {
                            continue;
                            
                        } else {
                            cnt.put(tmp, cnt.get(tmp) - 1);
                            break;
                        }
                    }
                    
                } else {
                    // 최솟값 삭제
                    while (!min.isEmpty()) {
                        int tmp = min.poll();
                        
                        if (cnt.get(tmp) <= 0) {
                            continue;
                            
                        } else {
                            cnt.put(tmp, cnt.get(tmp) - 1);
                            break;
                        }
                    }
                }
            }
        }
        
        int[] answer = {0, 0};
        
        while (!max.isEmpty()) {
            answer[0] = max.poll();
            
            if (cnt.get(answer[0]) <= 0) {
                answer[0] = 0;
                continue;
            } else {
                break;
            }
        }
        
        while (!min.isEmpty()) {
            answer[1] = min.poll();
            
            if (cnt.get(answer[1]) <= 0) {
                answer[1] = 0;
                continue;
            } else {
                break;
            }
        }
        
        return answer;
    }
}
