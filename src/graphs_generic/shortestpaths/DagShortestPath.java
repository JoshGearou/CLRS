package graphs_generic.shortestpaths;

import graphs_generic.Graph;
import graphs_generic.Node;
import graphs_generic.traversals.TopologicalSort;
import graphs_generic.Vertex;

import java.util.Stack;

public class DagShortestPath {

    public static void dagShortestPath(Graph<String> graph, Vertex<String> s) {
        Stack<Vertex<String>> topologicalOrdering = TopologicalSort.topologicalSort(graph);
        Relaxation.initializeSingleSource(graph, s);
        while (!topologicalOrdering.isEmpty()) {
            Vertex<String> u = topologicalOrdering.pop();
            for (Node<String> node: graph.getAdjList().get(u)) {
                Vertex<String> v = node.getVertex();
                Relaxation.relax(u, v, node.getWeight());
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>();
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
            System.out.println(v.getVal() + ": " + v.getKey());
        }
    }
}
