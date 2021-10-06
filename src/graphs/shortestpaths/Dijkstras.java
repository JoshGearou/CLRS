package graphs.shortestpaths;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstras {

    public int[] dijkstras(int[][][] adj, int start) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>( Comparator.comparingInt(x -> dist[x[1]]));
        pq.add(new int[]{0, start});
        while (!pq.isEmpty()) {
            int[] vertex = pq.poll();
            int currDist = vertex[0];
            int u = vertex[1];
            if (currDist > dist[u]) {
                continue;
            }

            for (int[] neighbor: adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if ((long) (dist[u] + weight) < (long) dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        int[][][] adjList = new int[][][]{{{1,4},{2,4},{3,6},{4,6}},{{2,2},{0,4}},{{0,4},{1,2},{3,8}},{{0,6},{2,8},{4,9}},{{0,6},{3,9}}};
        int[] dist = new Dijkstras().dijkstras(adjList, 0);
        for (int i=0; i<dist.length; i++) {
            System.out.println("distance estimate for " + i + " is: " + dist[i]);
        }
    }
}
