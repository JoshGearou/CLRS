package graphs.traversals;

import java.util.HashSet;

public class DFS {
    private void dfs(int[][] adj) {
        HashSet<Integer> visited = new HashSet<>();
        for (int i=0; i<adj.length; i++) {
            if (!visited.contains(i)) {
                visited.add(i);
                dfs(i, adj, visited);
            }
        }
    }

    private void dfs(int u, int[][] adj, HashSet<Integer> visited) {
        System.out.println(u);
        for (int v: adj[u]) {
            if (!visited.contains(v)) {
                visited.add(v);
                dfs(v, adj, visited);
            }
        }
    }
}
