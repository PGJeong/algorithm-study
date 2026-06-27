import java.util.*;

class Music implements Comparable<Music> {
    String genre;
    int play, index;
    
    Music(String genre, int play, int index) {
        this.genre = genre;
        this.play = play;
        this.index = index;
    }
    
    @Override
    public int compareTo(Music o) {
        if (this.play != o.play) return Integer.compare(this.play, o.play) * -1;
        else return Integer.compare(this.index, o.index);
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> sum = new HashMap<>(); // 장르별 재생횟수 합계
        HashMap<String, PriorityQueue<Music>> list = new HashMap(); // 장르별 재생횟수 목록
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i]; // 장르
            int play = plays[i]; // 재생횟수
            
            sum.put(genre, sum.getOrDefault(genre, 0) + play);
            
            PriorityQueue pq = list.getOrDefault(genre, new PriorityQueue<Music>());
            pq.add(new Music(genre, play, i));
            list.put(genre, pq);
        }
        
        PriorityQueue<Music> pq1 = new PriorityQueue<>();
        
        for (String genre : sum.keySet()) {
            pq1.add(new Music(genre, sum.get(genre), -1));
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        while(!pq1.isEmpty()) {
            String genre = pq1.poll().genre;
            PriorityQueue<Music> pq2 = list.get(genre);
            int count = 0;
            
            while(!pq2.isEmpty() && count < 2) {
                answer.add(pq2.poll().index);
                count++;
            }
        }
        
        int[] result = new int[answer.size()];
        
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
}
