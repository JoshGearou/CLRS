package graphs.maximumflow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondKarp {

    private int maxFlow(int[][] adj, int s, int t, int n) {
        int maxFlow = 0;
        int flow;
        int[] dist = new int[n];
        int[] parent = new int[n];
        do {
            dist[s] = 0;
            Queue<Integer> queue = new LinkedList<>();
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            queue.add(s);
            while (!queue.isEmpty()) {
                int u = queue.poll();
                if (u == t) {
                    break;
                }
                for (int v=0; v<n; v++) {
                    if (adj[u][v] > 0 && dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        queue.add(v);
                        parent[v] = u;
                    }
                }
            }
            flow = augment(s, t, Integer.MAX_VALUE, parent, adj);
            maxFlow+=flow;
        } while (flow != 0);
        return maxFlow;
    }

    private int augment(int s, int v, int minEdge, int[] parent, int[][] adj) {
        if (v == s) {
            return minEdge;
        }

        if (parent[v] != -1) {
            int flow = augment(s, parent[v], Math.min(minEdge, adj[parent[v]][v]), parent, adj);
            adj[parent[v]][v]-=flow;
            adj[v][parent[v]]+=flow;
            return flow;
        }
        return 0;
    }
}
