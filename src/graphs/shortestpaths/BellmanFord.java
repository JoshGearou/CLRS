package graphs.shortestpaths;

import java.util.Arrays;

public class BellmanFord {

    private int[] bellmanFord(int[][][] adj, int src) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i=0; i<n-1; i++) {
            for (int u=0; u<n; u++) {
                for (int j=0; j<adj[u].length; j++) {
                    int[] vertex = adj[u][j];
                    int v = vertex[0];
                    int weight = vertex[1];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v] {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }
        return dist;
    }
}
