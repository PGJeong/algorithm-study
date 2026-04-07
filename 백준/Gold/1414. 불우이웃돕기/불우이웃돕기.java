import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int[] parent;
	static int[] rank;
	
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
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] e1, int[] e2) -> Integer.compare(e1[2], e2[2]));
		int N = Integer.parseInt(br.readLine());
		
		// init parent[]
		parent = new int[N];
		rank = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		// input read
		int sumOfLength = 0;
		
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0; j < N; j++) {
				if(data.charAt(j) == '0') {
					// pq.add(new int[] {i, j, 0});
					continue;
				} else if(data.charAt(j) >= 'a' && data.charAt(j) <= 'z') {
					pq.add(new int[] {i, j, data.charAt(j) - 'a' + 1});
					sumOfLength += data.charAt(j) - 'a' + 1;
				} else {
					pq.add(new int[] {i, j, data.charAt(j) - 'A' + 27});
					sumOfLength += data.charAt(j) - 'A' + 27;
				}
			}
		}
		
		// kruskal
		int numOfEdges = 0; // 추가한 간선 개수
		int lenOfEdges = 0; // 추가한 간선 가중치 합
		
		while(!pq.isEmpty()) {
			int[] edge = pq.poll();
			
			if(union(edge[0], edge[1])) {
				numOfEdges++;
				lenOfEdges += edge[2];
			}
			
			if(numOfEdges == N - 1) {
				break;
			}
		}
		
		if(numOfEdges == N - 1) {
			System.out.println(sumOfLength - lenOfEdges);
		} else {
			System.out.println(-1);
		}
	}
}
