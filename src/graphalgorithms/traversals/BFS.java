package graphalgorithms.traversals;

import graphalgorithms.Graph;
import graphalgorithms.Node;
import graphalgorithms.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void bfs(Graph<String> graph) {
        HashSet<Vertex<String>> visited = new HashSet<>();
        Queue<Vertex<String>> queue = new LinkedList<>();
        queue.add(graph.getVertices().get(0));
        visited.add(graph.getVertices().get(0));
        while (!queue.isEmpty()) {
            Vertex<String> vertex = queue.poll();
            System.out.println(vertex.getVal());
            for (Node<String> node : graph.getAdjList().get(vertex)) {
                if (!visited.contains(node.getVertex())) {
                    visited.add(node.getVertex());
                    queue.add(node.getVertex());
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
        new BFS().bfs(graph);
    }
}
