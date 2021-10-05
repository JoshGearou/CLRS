package graphs.tarjans;

import java.util.Arrays;
import java.util.HashSet;

public class ArticulationPoint {

    public boolean[] articulationPoints(int[][] adj) {
        int[] count = new int[1];
        HashSet<Integer> visited = new HashSet<>();
        int n = adj.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] depth = new int[n];
        int[] low = new int[n];
        boolean[] res = new boolean[n];
        for (int u=0; u<n; u++) {
            if (!visited.contains(u)) {
                visited.add(u);
                articulationPoints(u, adj, parent, low, depth, count, visited, res);
            }
        }

        return res;
    }

    public void articulationPoints(int u,
                                   int[][] adj,
                                   int[] parent, int[] low, int[] depth, int[] count, HashSet<Integer> visited, boolean[] res) {
        int children = 0;
        depth[u] = count[0];
        low[u] = count[0];
        count[0]++;
        for (int v: adj[u]) {
            if (!visited.contains(v)) {
                children++;
                visited.add(v);
                parent[v] = u;
                articulationPoints(v, adj, parent, low, depth, count, visited, res);

                low[u] = Math.min(low[u], low[v]);

                // case 2: v is an articulation edge if it does not have a back edge to one of u's ancestors,
                // which would mean removing u would make v unreachable for some parts of the graph.
                if (parent[u] != -1 && low[v] >= depth[u]) {
                    res[u] = true;
                }
            }
            if (parent[u] != v) {
                low[u] = Math.min(low[u], depth[v]);
            }
        }

        // case 1: root is an articulation point if it has two or more children.
        if (parent[u] == -1 && children > 1) {
            res[u] = true;
        }
    }
}
