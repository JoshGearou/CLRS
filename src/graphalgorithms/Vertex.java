package graphalgorithms;

import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
    private T val;
    private Set<Edge<T>> edges;

    public Vertex(T val) {
        this.val = val;
        edges = new HashSet<>();
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public T getVal() {
        return val;
    }
}
