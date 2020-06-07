package graphalgorithms.shortestpaths;

import graphalgorithms.Graph;
import graphalgorithms.Node;
import graphalgorithms.Vertex;
import graphalgorithms.VertexKeyComparator;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstras {

    public static void dijkstras(Graph<String> graph, Vertex<String> s) {
        Relaxation.initializeSingleSource(graph, s);
        Set<Vertex<String>> A = new HashSet<>();
        PriorityQueue<Vertex<String>> pq = new PriorityQueue<>(graph.getNumberVertices(), new VertexKeyComparator());
        pq.addAll(graph.getVertices());
        while (!pq.isEmpty()) {
            Vertex<String> u = pq.poll();
            A.add(u);
            for (Node<String> node: graph.getAdjList().get(u)) {
                Vertex<String> v = node.getVertex();
                Relaxation.relax(u, v, node.getWeight());
                // if the heap does not contain v, that means v already has shortest distance from s to v.
                if (pq.contains(v)) { // need to reinsert v as v.getKey() may have changed.  Ideally we'd use decreaseKey with a binary heap.
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
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
