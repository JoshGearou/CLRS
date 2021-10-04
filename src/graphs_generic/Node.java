package graphs_generic;

public class Node<T> {
    Vertex<T> vertex;
    int weight;

    public Node(Vertex<T> vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public void setVertex(Vertex<T> vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
