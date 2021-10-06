package graphs.shortestpaths;

public class FloydWarshall {
    // adj[i][j] stores the weight from vertex i to j.
    private int[][] floydWarshall(int[][] adj, int n) {
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
        return adj;
    }
}
