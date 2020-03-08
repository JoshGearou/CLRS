package graphalgorithms;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

    public static Stack<Vertex<String>> topologicalSort(Graph<String> g) {
        Stack<Vertex<String>> stack  = new Stack<>();
        Set<Vertex<String>> seen = new HashSet<>();
        for (Vertex<String> v: g.getVertices()) {
            if (!seen.contains(v)) {
                topologicalSort(v, stack, seen);
            }
        }
        return stack;
    }

    public static void topologicalSort(Vertex<String> v, Stack<Vertex<String>> stack, Set<Vertex<String>> seen) {
        seen.add(v);

        for (Node<String> node: v.getAdjList()) {
            Vertex<String> neighbor = node.getVertex();
            if (!seen.contains(neighbor)) {
                topologicalSort(neighbor, stack, seen);
            }
        }
        stack.push(v);
    }
}
