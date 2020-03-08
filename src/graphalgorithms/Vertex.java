package graphalgorithms;

import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
    private T val;
    private int d; // for shortest-paths algorithms
    private int key; // for prim's algorithm
    private Vertex<T> parent; // for prim's algorithm
    private Set<Node<T>> adjList;

    public Vertex(T val) {
        this.val = val;
        adjList = new HashSet<>();
    }

    public Set<Node<T>> getAdjList() {
        return adjList;
    }

    public T getVal() {
        return val;
    }

    public int getKey() {
        return key;
    }

    public Vertex<T> getParent() {
        return parent;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}

