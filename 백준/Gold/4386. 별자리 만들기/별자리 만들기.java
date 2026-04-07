import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	public int to;
	public double weight;

	public Edge(int to, double weight) {
		super();
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge e) {
		return Double.compare(this.weight, e.weight);
	}
}

public class Main {
	static double calcDist(double[] p1, double[] p2) { // distance between two points
		return Math.sqrt(Math.pow(Math.abs(p1[0] - p2[0]), 2) + Math.pow(Math.abs(p1[1] - p2[1]), 2));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[][] nodes = new double[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nodes[i][0] = Double.parseDouble(st.nextToken()); // x
			nodes[i][1] = Double.parseDouble(st.nextToken()); // y
		}
		
		double len = 0;
		
		// prim algorithm
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean selected[] = new boolean[N];
		
		// insert initial node
		pq.add(new Edge(0, 0));
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(selected[e.to]) {
				continue;
			}
			
			selected[e.to] = true;
			len += e.weight;
			
			for(int i = 0; i < N; i++) {
				if(e.to != i && !selected[i]) {
					pq.add(new Edge(i, calcDist(nodes[e.to], nodes[i])));
				}
			}
		}
		
		System.out.println(String.format("%.2f", len));
	}
}
