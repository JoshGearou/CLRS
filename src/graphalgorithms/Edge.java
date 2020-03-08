package graphalgorithms;

public class Edge<E> {
    Vertex<E> src;
    Vertex<E> dest;
    int weight;

    public Edge(Vertex<E> src, Vertex<E> dest, int weight) {
        this.src = src;
        this.dest = dest;
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
}
