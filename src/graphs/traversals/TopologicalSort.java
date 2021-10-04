package graphs.traversals;

import java.util.HashSet;
import java.util.Stack;

public class TopologicalSort {
    private void topologicalSort(int[][] adj) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<adj.length; i++) {
            if (!visited.contains(i)) {
                visited.add(i);
                topologicalSort(i, adj, visited, stack);
            }
        }
    }

    private void topologicalSort(int u, int[][] adj, HashSet<Integer> visited, Stack<Integer> stack) {
        System.out.println(u);
        for (int v: adj[u]) {
            if (!visited.contains(v)) {
                visited.add(v);
                topologicalSort(v, adj, visited, stack);
            }
        }
        stack.push(u);
    }
}
