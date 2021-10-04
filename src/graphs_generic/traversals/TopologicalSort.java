package graphs_generic.traversals;

import graphs_generic.Graph;
import graphs_generic.Node;
import graphs_generic.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

    public static Stack<Vertex<String>> topologicalSort(Graph<String> graph) {
        Stack<Vertex<String>> stack  = new Stack<>();
        Set<Vertex<String>> seen = new HashSet<>();
        for (Vertex<String> v: graph.getVertices()) {
            if (!seen.contains(v)) {
                topologicalSort(graph, v, stack, seen);
            }
        }
        return stack;
    }

    public static void topologicalSort(Graph<String> graph, Vertex<String> u, Stack<Vertex<String>> stack, Set<Vertex<String>> seen) {
        seen.add(u);

        for (Node<String> node : graph.getAdjList().get(u)) {
            Vertex<String> neighbor = node.getVertex();
            if (!seen.contains(neighbor)) {
                topologicalSort(graph, neighbor, stack, seen);
            }
        }
        stack.push(u);
    }
}
