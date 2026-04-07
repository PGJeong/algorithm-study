import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int from, to, weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return Integer.compare(this.weight, e.weight);
	}
}

public class Main {
	static final int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int row, col;
	static int[][] originalMap;
	static int[][] indexedMap;
	static PriorityQueue<Edge> pathCandidate;
	static int[] parent;
	static int[] rank;
	
	static void indexSection(int r, int c, int index) {
		indexedMap[r][c] = index;
		
		for(int i = 0; i < delta.length; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			
			if(nr < 0 || nc < 0 || nr >= row || nc >= col) {
				continue;
			}
			
			if(originalMap[nr][nc] == 1 && indexedMap[nr][nc] == 0) {
				indexSection(nr, nc, index);
			}
		}
	}
	
	static void findPath(int r, int c, int from) {
		for(int i = 0; i < delta.length; i++) {
			int nr = r;
			int nc = c;
			int len = 0; // 경로 길이
			
			while(true) {
				nr += delta[i][0];
				nc += delta[i][1];
				len++;
				
				if(nr < 0 || nc < 0 || nr >= row || nc >= col || indexedMap[nr][nc] == from) {
					break;
				}
				
				if(indexedMap[nr][nc] != 0) {
					if(len - 1 > 1) {
						pathCandidate.add(new Edge(from, indexedMap[nr][nc], len - 1)); // from, to, weight
					}
					
					break;
				}
			}
		}
	}
	
	static int find(int v) {
		if(parent[v] != v) {
			parent[v] = find(parent[v]); // path compression
		}
		
		return parent[v];
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) {
			return false;
		}
		
		// union by rank
		if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		} else {
			parent[rootB] = rootA;
			rank[rootA]++;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 입력 받기 */
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		originalMap = new int[row][col];
		indexedMap = new int[row][col];
		
		for(int r = 0; r < row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < col; c++) {
				originalMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* (Step 1) 구역 번호 표시 */
		int sectionIndex = 0;
		
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				if(originalMap[r][c] == 1 && indexedMap[r][c] == 0) {
					sectionIndex++;
					indexSection(r, c, sectionIndex);
				}
			}
		}
		
		/* (Step 2) 경로 후보 탐색 */
		pathCandidate = new PriorityQueue<>();
		
		for(int r = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				if(indexedMap[r][c] != 0) {
					findPath(r, c, indexedMap[r][c]);
				}
			}
		}
		
		/* (Step 3) 최소 신장 트리 */
		int numberOfPath = 0;	// 경로 수
		int accumulatedLen = 0;	// 누적 경로 길이
		
		parent = new int[sectionIndex + 1];
		rank = new int[sectionIndex + 1];
		
		for(int i = 0; i < sectionIndex + 1; i++) {
			parent[i] = i;
		}
		
		// Kruskal 알고리즘
		while(!pathCandidate.isEmpty()) {
			Edge e = pathCandidate.poll();
			
			if(union(e.from, e.to)) {
				numberOfPath++;
				accumulatedLen += e.weight;
			}
			
			if(numberOfPath == sectionIndex - 1) {
				break;
			}
		}
		
		if(numberOfPath == sectionIndex - 1) System.out.println(accumulatedLen);
		else System.out.println(-1);
	}
}
