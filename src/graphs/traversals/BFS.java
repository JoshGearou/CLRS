package graphs.traversals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void bfs(int[][] adj, int start) {

        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v: adj[u]) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
    }
}
