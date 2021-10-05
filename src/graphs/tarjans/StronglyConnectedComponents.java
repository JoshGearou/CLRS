package graphs.tarjans;

import java.util.Arrays;
import java.util.Stack;

public class StronglyConnectedComponents {

    public void scc(int[][] adj) {
        int n = adj.length;
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        int[] low = new int[n];
        int[] depth = new int[n];
        int[] count = new int[1];
        Stack<Integer> stack = new Stack<>();
        for (int u = 0; u < n; u++) {
            if (visited[u] == -1) {
                visited[u] = 1;
                scc(u, low, adj, depth, stack, visited, count);
            }
        }
    }

    public void scc(int u, int[] low, int[][] adj, int[] depth, Stack<Integer> stack, int[] visited, int[] count) {

        stack.push(u);
        low[u] = depth[u] = count[0];
        count[0]++;

        for (int v : adj[u]) {
            if (visited[v] == -1) {
                visited[v] = 1;
                scc(v, low, adj, depth, stack, visited, count);
            }

            if (visited[v] == 1) {
                low[u] = Math.min(low[u], low[v]);
            }
        }

        if (low[u] == depth[u]) {
            int v;
            do {
                v = stack.pop();
                visited[v] = 0;
                System.out.print(v + " ");
            } while (u != v);
            System.out.println();
        }
    }
}
