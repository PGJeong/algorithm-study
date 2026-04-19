import java.util.*;

class Job {
    public int no, req, dur; // 작업번호, 요청시각, 소요시간
    
    public Job(int no, int req, int dur) {
        this.no = no;
        this.req = req;
        this.dur = dur;
    }
}

class Solution {
    public int solution(int[][] jobs) {        
        PriorityQueue<Job> requests = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.req, o2.req);
        });
        
        PriorityQueue<Job> jobQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.dur != o2.dur) {
                // 1순위: 소요시간이 짧은 순
                return Integer.compare(o1.dur, o2.dur);
                
            } else if (o1.req != o2.req) {
                // 2순위: 요청시간이 빠른 순
                return Integer.compare(o1.req, o2.req);
                
            } else {
                // 3순위: 작업번호가 작은 순
                return Integer.compare(o1.no, o2.no);
            }
        });
            
        for (int i = 0; i < jobs.length; i++) {
            requests.add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        
        int time = 0;   // 현재시각
        int total = 0;  // 누적시간(반환시간)
        
        while (!requests.isEmpty() || !jobQueue.isEmpty()) {
            // 현재시각 기준 유효한 요청은 모두 jobQueue에 삽입
            while (!requests.isEmpty() && requests.peek().req <= time) {
                jobQueue.add(requests.poll());
            }
            
            if (jobQueue.isEmpty()) {
                time++;
                continue;
            }
            
            Job job = jobQueue.poll();
            total += time + job.dur - job.req;
            time += job.dur;
        }
        
        return total / jobs.length;
    }
}
