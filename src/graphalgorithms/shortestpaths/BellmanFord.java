package graphalgorithms.shortestpaths;

import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;

public class BellmanFord {

    public static boolean bellmanFord(Graph<String> graph, Vertex<String> s) {
        Relaxation.initializeSingleSource(graph, s);
        for (int i=0; i<graph.getNumberVertices(); i++) {
            for (Edge<String> edge: graph.getEdges()) {
                Relaxation.relax(edge.getSrc(), edge.getDest(), edge.getWeight());
            }
        }

        for (Edge<String> edge: graph.getEdges()) {
            Vertex<String> u = edge.getSrc();
            Vertex<String> v = edge.getDest();
            if (v.getKey() > u.getKey() + edge.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>();
        g.addDirectedEdge("s", "t", 6);
        g.addDirectedEdge("s", "y", 7);
        g.addDirectedEdge("t", "x", 5);
        g.addDirectedEdge("t", "y", 8);
        g.addDirectedEdge("t", "z", -4);
        g.addDirectedEdge("x", "t", -2);
        g.addDirectedEdge("y", "x", -3);
        g.addDirectedEdge("y", "z", 9);
        g.addDirectedEdge("z", "x", 7);
        boolean bell = bellmanFord(g, g.getVertices().get(0));
        System.out.println(bell);
        for (Vertex<String> v: g.getVertices()) {
            System.out.println(v.getVal() + ": " + v.getKey());
        }

    }
}
