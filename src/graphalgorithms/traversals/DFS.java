package graphalgorithms.traversals;

import graphalgorithms.Graph;
import graphalgorithms.Node;
import graphalgorithms.Vertex;

import java.util.HashSet;
import java.util.Stack;

public class DFS {

    public void dfs(Graph<String> graph) {
        HashSet<Vertex<String>> visited = new HashSet<>();
        for (Vertex<String> v : graph.getVertices()) {
            if (!visited.contains(v)) {
                dfs(graph, v, visited);
            }
        }
    }

    public void dfs(Graph<String> graph, Vertex<String> v, HashSet<Vertex<String>> visited) {
        visited.add(v);
        System.out.println(v.getVal());
        for (Node<String> node : graph.getAdjList().get(v)) {
            if (!visited.contains(node.getVertex())) {
                dfs(graph, node.getVertex(), visited);
            }
        }
    }

    public void dfsWithStack(Graph<String> graph) {
        HashSet<Vertex<String>> visited = new HashSet<>();
        Stack<Vertex<String>> stack = new Stack<>();
        stack.push(graph.getVertices().get(0));
        visited.add(graph.getVertices().get(0));
        while (!stack.isEmpty()) {
            Vertex<String> vertex = stack.pop();
            System.out.println(vertex.getVal());
            for (Node<String> node : graph.getAdjList().get(vertex)) {
                if (!visited.contains(node.getVertex())) {
                    visited.add(node.getVertex());
                    stack.push(node.getVertex());
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addDirectedEdge("s", "t", 10);
        graph.addDirectedEdge("s", "y", 5);
        graph.addDirectedEdge("t", "x", 1);
        graph.addDirectedEdge("t", "y", 2);
        graph.addDirectedEdge("x", "z", 4);
        graph.addDirectedEdge("y", "t", 3);
        graph.addDirectedEdge("y", "x", 9);
        graph.addDirectedEdge("y", "z", 2);
        graph.addDirectedEdge("z", "s", 7);
        graph.addDirectedEdge("z", "x", 6);
        new DFS().dfsWithStack(graph);
    }
}
