package graphalgorithms.shortestpaths;

import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;

public class Relaxation {

    public static void initializeSingleSource(Graph<String> g, Vertex<String> s) {
        g.getVertices().forEach(v -> {
            v.setParent(null);
            v.setD(Integer.MAX_VALUE);
        });
        s.setD(0);
    }

    public static void relax(Edge<String> edge) {
        Vertex<String> u = edge.getSrc();
        Vertex<String> v = edge.getDest();
        if (v.getD() > (long) u.getD() + edge.getWeight()) {
            v.setD(u.getD() + edge.getWeight());
            v.setParent(u);
        }
    }

    public static void relax(Vertex<String> u, Vertex<String> v, int weight) {
        if (v.getD() > (long) u.getD() + weight) {
            v.setD(u.getD() + weight);
            v.setParent(u);
        }
    }
}
