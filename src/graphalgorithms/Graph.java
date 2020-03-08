package graphalgorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<E> {
    int numVertices;
    List<Vertex<E>> vertices;
    List<Edge<E>> edges;
    Set<Edge<E>> A;


    public Graph(int numVertices) {
        this.numVertices = numVertices;
        vertices = new ArrayList<>(numVertices);
        edges = new ArrayList<>();
        A = new HashSet<>();
    }

    public Set<Edge<E>> getA() {
        return A;
    }

    public void addEdge(E src, E dest, int weight) {
        addEdge(addVertex(src), addVertex(dest), weight);
    }

    public void addEdge(Vertex<E> src, Vertex<E> dest, int weight) {
        Edge<E> e = new Edge<>(src, dest, weight);
        if (newEdge(e)) {
            edges.add(e);
            src.getEdges().add(e);
            dest.getEdges().add(e);
        }
    }

    public boolean newEdge(Edge<E> e) {
        for (Edge<E> edge : edges) {
            if ((edge.src == e.src && edge.dest == e.dest) || (edge.dest == e.src && edge.src == e.dest)) {
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

