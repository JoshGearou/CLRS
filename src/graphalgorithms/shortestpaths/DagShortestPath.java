package graphalgorithms.shortestpaths;

import graphalgorithms.Graph;
import graphalgorithms.Node;
import graphalgorithms.TopologicalSort;
import graphalgorithms.Vertex;

import java.util.Stack;

public class DagShortestPath {

    public static void dagShortestPath(Graph<String> g, Vertex<String> s) {
        Stack<Vertex<String>> topologicalOrdering = TopologicalSort.topologicalSort(g);
        Relaxation.initializeSingleSource(g, s);
        while (!topologicalOrdering.isEmpty()) {
            Vertex<String> u = topologicalOrdering.pop();
            for (Node<String> node: u.getAdjList()) {
                Vertex<String> v = node.getVertex();
                Relaxation.relax(u, v, node.getWeight());
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>(6);
        g.addVertex("r");
        g.addVertex("s");
        g.addVertex("t");
        g.addVertex("x");
        g.addVertex("y");
        g.addVertex("z");
        g.addDirectedEdge("r", "s", 5);
        g.addDirectedEdge("r", "t", 3);
        g.addDirectedEdge("s", "t", 2);
        g.addDirectedEdge("s", "x", 6);
        g.addDirectedEdge("t", "x", 7);
        g.addDirectedEdge("t", "y", 4);
        g.addDirectedEdge("t", "z", 2);
        g.addDirectedEdge("x", "y", -1);
        g.addDirectedEdge("x", "z", 1);
        g.addDirectedEdge("y", "z", -2);
        dagShortestPath(g, g.getVertices().get(1));
        for (Vertex<String> v: g.getVertices()) {
            System.out.println(v.getKey());
        }
    }
}
