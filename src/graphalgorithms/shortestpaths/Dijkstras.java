package graphalgorithms.shortestpaths;

import graphalgorithms.Graph;
import graphalgorithms.Node;
import graphalgorithms.Vertex;
import graphalgorithms.VertexKeyComparator;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstras {

    public static void dijkstras(Graph<String> g, Vertex<String> s) {
        Relaxation.initializeSingleSource(g, s);
        Set<Vertex<String>> A = new HashSet<>();
        PriorityQueue<Vertex<String>> pq = new PriorityQueue<>(g.getVertices().size(), new VertexKeyComparator());
        pq.addAll(g.getVertices());
        while (!pq.isEmpty()) {
            Vertex<String> u = pq.poll();
            A.add(u);
            for (Node<String> node: u.getAdjList()) {
                Vertex<String> v = node.getVertex();
                Relaxation.relax(u, v, node.getWeight());
                if (pq.contains(v)) { // need to reinsert v as v.getD() may have changed.  Ideally we'd use decreaseKey with a binary heap.
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(5);
        graph.addVertex("s");
        graph.addVertex("t");
        graph.addVertex("x");
        graph.addVertex("y");
        graph.addVertex("z");
        graph.addDirectedEdge("s", "t", 10);
        graph.addDirectedEdge("s", "y", 5);
        graph.addDirectedEdge("t", "x", 1);
        graph.addDirectedEdge("t", "y", 2);
        graph.addDirectedEdge("x", "z", 4);
        graph.addDirectedEdge("y", "t", 3);
        graph.addDirectedEdge("y", "x", 9);
        graph.addDirectedEdge("y", "z", 2);
        graph.addDirectedEdge("z", "s", 7);
        graph.addDirectedEdge("z", "x", 6);
        dijkstras(graph, graph.getVertices().get(0));
        for (Vertex<String> v: graph.getVertices()) {
            System.out.println(v.getKey());
        }
    }
}
