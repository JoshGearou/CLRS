package graphs.shortestpaths;

import java.util.Arrays;

public class BellmanFord {

    private int[] bellmanFord(int[][][] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i=0; i<n-1; i++) {
            for (int u=0; u<n; u++) {
                for (int j=0; j<adj[u].length; j++) {
                    int[] vertex = adj[u][j];
                    int v = vertex[0];
                    int weight = vertex[1];
                    dist[v] = Math.min(dist[u] + weight, dist[v]);
                }
            }
        }
        return dist;
    }
}
