package graphalgorithms.shortestpaths;

import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;

public class Relaxation {

    public static void initializeSingleSource(Graph<String> g, Vertex<String> s) {
        g.getVertices().forEach(v -> {
            v.setParent(null);
            v.setKey(Integer.MAX_VALUE);
        });
        s.setKey(0);
    }

    public static void relax(Edge<String> edge) {
        Vertex<String> u = edge.getSrc();
        Vertex<String> v = edge.getDest();
        if (v.getKey() > (long) u.getKey() + edge.getWeight()) {
            v.setKey(u.getKey() + edge.getWeight());
            v.setParent(u);
        }
    }

    public static void relax(Vertex<String> u, Vertex<String> v, int weight) {
        if (v.getKey() > (long) u.getKey() + weight) {
            v.setKey(u.getKey() + weight);
            v.setParent(u);
        }
    }
}
