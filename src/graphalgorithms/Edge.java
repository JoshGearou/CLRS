package graphalgorithms;

import java.util.Objects;

public class Edge<E> {
    Vertex<E> src;
    Vertex<E> dest;
    int weight;

    public Edge(Vertex<E> src, Vertex<E> dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Edge(E srcVal, E destVal, int weight) {
        src = new Vertex<>(srcVal);
        dest = new Vertex<>(destVal);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex<E> getSrc() {
        return src;
    }

    public Vertex<E> getDest() {
        return dest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest, weight);
    }

    @Override
    public boolean equals(Object obj) {
        Edge<E> edge = (Edge<E>) obj;
        return this.src.equals(edge.src) && this.dest.equals(edge.dest) && this.weight == edge.weight;
    }
}
