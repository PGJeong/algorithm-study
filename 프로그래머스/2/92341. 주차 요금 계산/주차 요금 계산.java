import java.util.*;

class Solution {
    int calcMin(String[] inTime, String[] outTime) {
        int inH  = Integer.parseInt(inTime[0]);
        int inM  = Integer.parseInt(inTime[1]);
        int outH = Integer.parseInt(outTime[0]);
        int outM = Integer.parseInt(outTime[1]);
        
        return (outH * 60 + outM) - (inH * 60 + inM);
    }
    
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, String> entry = new HashMap<>();  // 입차기록 (차량번호, 입차시각)
        TreeMap<String, Integer> total = new TreeMap<>(); // 주차시간 (차량번호, 주차시간)
        
        for (String record : records) {
            String[] r = record.split(" "); // [0]: 시각, [1]: 차량번호, [2]: 구분
            
            if (r[2].equals("IN")) {
                // 입차
                entry.put(r[1], r[0]);
                
            } else {
                // 출차
                String[] inTime  = entry.get(r[1]).split(":");
                String[] outTime = r[0].split(":");
                
                total.put(r[1], total.getOrDefault(r[1], 0) + calcMin(inTime, outTime)); // 합산 필수
                entry.remove(r[1]); // 제거 필수
            }
        }
        
        // 출차 기록이 없는 경우
        for (String number : entry.keySet()) {
            String[] inTime  = entry.get(number).split(":");
            String[] outTime = {"23", "59"};
            
            total.put(number, total.getOrDefault(number, 0) + calcMin(inTime, outTime)); // 합산 필수
        }
        
        // 요금 계산
        int[] fee = new int[total.size()];
        int index = 0;
        
        while (!total.isEmpty()) {
            Map.Entry<String, Integer> first = total.pollFirstEntry();
            int totalM = first.getValue();
            
            if (totalM <= fees[0]) {
                // 기본시간 이하
                fee[index] = fees[1];
                
            } else {
                // 기본시간 초과
                if ((totalM - fees[0]) % fees[2] == 0)
                    fee[index] = fees[1] + (totalM - fees[0]) / fees[2] * fees[3];
                else
                    fee[index] = fees[1] + ((totalM - fees[0]) / fees[2] + 1) * fees[3];
            }
            
            index++;
        }

        return fee;
    }
}
