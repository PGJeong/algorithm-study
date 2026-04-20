import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lesson {
    public int start, end; // 시작시간, 종료시간

    public Lesson(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 강의실 배정 전
        PriorityQueue<Lesson> lessons = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.start, o2.start);
        });

        // 강의실 배정 후
        PriorityQueue<Lesson> allotted = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.end, o2.end);
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lessons.add(new Lesson(start, end));
        }

        int room = 1; // 강의실 수

        while (!lessons.isEmpty()) {
            Lesson lesson = lessons.poll();

            if (allotted.isEmpty()) {
                allotted.add(lesson);
                continue;
            }

            if (allotted.peek().end <= lesson.start) {
                allotted.poll();
                allotted.add(lesson);

            } else {
                room++;
                allotted.add(lesson);
            }
        }

        System.out.println(room);
    }
}
