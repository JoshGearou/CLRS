package graphalgorithms;

import java.util.ArrayList;
import java.util.List;

public class Graph<E> {
    List<Vertex<E>> vertices;
    List<Edge<E>> edges;


    public Graph(int numVertices) {
        vertices = new ArrayList<>(numVertices);
        edges = new ArrayList<>();
    }

    public void addDirectedEdge(E src, E dest, int weight) {
        addDirectedEdge(addVertex(src), addVertex(dest), weight);
    }

    public void addUndirectedEdge(E src, E dest, int weight) {
        addUndirectedEdge(addVertex(src), addVertex(dest), weight);
    }

    public void addDirectedEdge(Vertex<E> src, Vertex<E> dest, int weight) {
        Edge<E> e = new Edge<>(src, dest, weight);
        if (newEdge(e)) {
            edges.add(e);
            src.getAdjList().add(new Node<E>(dest, weight));
        }
    }

    public void addUndirectedEdge(Vertex<E> src, Vertex<E> dest, int weight) {
        Edge<E> e1 = new Edge<>(src, dest, weight);
        Edge<E> e2 = new Edge<>(dest, src, weight);
        if (newEdge(e1)) {
            edges.add(e1);
            src.getAdjList().add(new Node<E>(dest, weight));
        }

        if (newEdge(e2)) {
            edges.add(e2);
            dest.getAdjList().add(new Node<E>(src, weight));
        }
    }

    public boolean newEdge(Edge<E> e) {
        for (Edge<E> edge : edges) {
            if ((edge.src == e.src && edge.dest == e.dest)) {
                return false;
            }
        }
        return true;
    }

    public Vertex<E> addVertex(E val) {
        Vertex<E> v;
        for (Vertex<E> vert : vertices) {
            if (vert.getVal().equals(val)) {
                v = vert;
                return v;
            }
        }
        Vertex<E> vertex = new Vertex<>(val);
        vertices.add(vertex);
        return vertex;
    }

    public List<Vertex<E>> getVertices() {
        return vertices;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }
}

