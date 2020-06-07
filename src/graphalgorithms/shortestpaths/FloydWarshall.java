package graphalgorithms.shortestpaths;

public class FloydWarshall {

    final static int INF = 9999;

    /**
     * Calculates all pairs shortest paths
     * @param graph the graph, where INF is between vertices without edges between them
     * @param v the number of vertices in the graph
     */
    public int[][] floydWarshall(int[][] graph, int v) {
        int[][] dist = new int[v][v];

        for (int i=0; i<v; i++) {
            for (int j=0; j<v; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k=0; k<v; k++) {
            for (int i=0; i<v; i++) {
                for (int j=0; j<v; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
}
