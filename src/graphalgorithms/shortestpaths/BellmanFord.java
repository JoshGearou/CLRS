package graphalgorithms.shortestpaths;

import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;

public class BellmanFord {

    public static boolean bellmanFord(Graph<String> g, Vertex<String> s) {
        Relaxation.initializeSingleSource(g, s);
        for (int i=0; i<g.getVertices().size(); i++) {
            for (Edge<String> edge: g.getEdges()) {
                Relaxation.relax(edge);
            }
        }

        for (Edge<String> edge: g.getEdges()) {
            Vertex<String> u = edge.getSrc();
            Vertex<String> v = edge.getDest();
            if (v.getD() > u.getD() + edge.getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>(5);
        g.addVertex("s");
        g.addVertex("t");
        g.addVertex("x");
        g.addVertex("y");
        g.addVertex("z");
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
            System.out.println(v.getVal() + ": " + v.getD());
        }

    }
}
