package graphalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph<E> {
    HashMap<Vertex<E>, List<Node<E>>> adjList;
    HashMap<E, Vertex<E>> valToVertex;
    List<Edge<E>> edges;
    List<Vertex<E>> vertices;

    public Graph() {
        adjList = new HashMap<>();
        valToVertex = new HashMap<>();
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public void addDirectedEdge(E src, E dest, int weight) {
        Vertex<E> u = getVertex(src);
        Vertex<E> v = getVertex(dest);
        Edge<E> e = new Edge<>(u, v, weight);
        if (!edges.contains(e)) {
            addEdge(e);
        }
    }

    public void addDirectedEdge(Vertex<E> src, Vertex<E> dest, int weight) {
        Edge<E> e = new Edge<>(src, dest, weight);
        if (!edges.contains(e)) {
            addEdge(e);
        }
    }

    public Vertex<E> getVertex(E src) {
        if (valToVertex.containsKey(src)) {
            return valToVertex.get(src);
        } else {
            valToVertex.put(src, new Vertex<>(src));
            return valToVertex.get(src);
        }
    }

    public void addEdge(Edge<E> edge) {
        edges.add(edge);
        Vertex<E> u = edge.getSrc();
        Vertex<E> v = edge.getDest();
        if (!adjList.containsKey(u)) {
            vertices.add(u);
            List<Node<E>> neighbors = new ArrayList<>();
            neighbors.add(new Node<>(v, edge.getWeight()));
            adjList.put(u, neighbors);
        } else {
            adjList.get(u).add(new Node<>(v, edge.getWeight()));
        }

        if (!adjList.containsKey(v)) {
            vertices.add(v);
            adjList.put(v, new ArrayList<>());
        }
    }

    public void addUndirectedEdge(E src, E dest, int weight) {
        Vertex<E> u = getVertex(src);
        Vertex<E> v = getVertex(dest);
        Edge<E> e1 = new Edge<>(u, v, weight);
        Edge<E> e2 = new Edge<>(v, u, weight);
        if (!edges.contains(e1)) {
            addEdge(e1);
        }

        if (!edges.contains(e2)) {
            addEdge(e2);
        }
    }

    public List<Vertex<E>> getVertices() {
        return vertices;
    }

    public List<Edge<E>> getEdges() {
        return edges;
    }

    public List<Edge<E>> getUndirectedEdges() {
        List<Edge<E>> edges = new ArrayList<>();
        for (Vertex<E> u : adjList.keySet()) {
            for (Node<E> n : adjList.get(u)) {
                Vertex<E> v = n.getVertex();
                if (edges.contains(new Edge<>(v.getVal(), u.getVal(), n.getWeight()))) {
                    continue;
                }
                edges.add(new Edge<>(u.getVal(), v.getVal(), n.getWeight()));
            }
        }
        return edges;
    }

    public HashMap<Vertex<E>, List<Node<E>>> getAdjList() {
        return adjList;
    }

    public int getNumberVertices() {
        return adjList.size();
    }
}

