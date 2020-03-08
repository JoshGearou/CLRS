package graphalgorithms.mst;

import graphalgorithms.*;
import javafx.util.Pair;

import java.util.*;

public class Prim {

    public List<Pair<Vertex<String>, Vertex<String>>> findMST(Graph<String> graph, Vertex<String> r) {
        List<Pair<Vertex<String>, Vertex<String>>> A = new ArrayList<>();
        graph.getVertices().forEach(e -> {
            e.setParent(null);
            e.setKey(Integer.MAX_VALUE);
        });
        r.setKey(0);
        PriorityQueue<Vertex<String>> pq = new PriorityQueue<>(graph.getVertices().size(), new VertexKeyComparator());
        pq.addAll(graph.getVertices());
        while (!pq.isEmpty()) {
            Vertex<String> u = pq.poll();
            A.add(new Pair<>(u, u.getParent()));
            for (Node<String> node: u.getAdjList()) {
                Vertex<String> v = node.getVertex();
                if (pq.contains(v) && node.getWeight() < v.getKey()) {
                    v.setParent(u);
                    v.setKey(node.getWeight());
                    if (pq.contains(v)) {
                        pq.remove(v);
                        pq.add(v);
                    }
                }
            }

        }
        A.remove(0);
        return A;
    }

    public static void main(String[] args) {
        Graph<String> g = new Graph<>(9);
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        g.addVertex("g");
        g.addVertex("h");
        g.addVertex("i");
        g.addUndirectedEdge("a", "b", 4);
        g.addUndirectedEdge("a", "h", 8);
        g.addUndirectedEdge("b", "c", 8);
        g.addUndirectedEdge("b", "h", 11);
        g.addUndirectedEdge("c", "d", 7);
        g.addUndirectedEdge("c", "i", 2);
        g.addUndirectedEdge("c", "f", 4);
        g.addUndirectedEdge("d", "e", 9);
        g.addUndirectedEdge("d", "f", 14);
        g.addUndirectedEdge("e", "f", 10);
        g.addUndirectedEdge("f", "g", 2);
        g.addUndirectedEdge("g", "i", 6);
        g.addUndirectedEdge("g", "h", 1);
        g.addUndirectedEdge("h", "i", 7);

        List<Pair<Vertex<String>, Vertex<String>>> mstEdges = new Prim().findMST(g, g.getVertices().get(0));
        for (Pair<Vertex<String>, Vertex<String>> e : mstEdges) {
            System.out.println(e.getKey().getVal() + " " + e.getValue().getVal());
        }
    }
}
