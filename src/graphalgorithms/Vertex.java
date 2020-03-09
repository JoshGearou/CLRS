package graphalgorithms;

import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
    private T val;
    // For prim's algorithm: the minimum weight of any edge connecting a vertex v
    // to another vertex in the tree
    //
    // For shortest path algorithms: An upper bound on the weight of a shortest path from source s to v
    private int key;
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
    
}

