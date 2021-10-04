package graphs_generic.shortestpaths;

import graphs_generic.Graph;
import graphs_generic.Node;
import graphs_generic.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstras {

    public static void dijkstras(Graph<String> graph, Vertex<String> s) {
        Relaxation.initializeSingleSource(graph, s);
        PriorityQueue<Vertex<String>> pq = new PriorityQueue<>(Comparator.comparingInt(Vertex::getKey));
        pq.addAll(graph.getVertices());
        while (!pq.isEmpty()) {
            Vertex<String> u = pq.poll();
            for (Node<String> node: graph.getAdjList().get(u)) {
                Vertex<String> v = node.getVertex();
                // if the heap does not contain v, that means v already has the shortest distance from s to v.
                if (pq.contains(v)) {
                    Relaxation.relax(u, v, node.getWeight());
                    // need to reinsert v as v.getKey() may have changed.  Ideally we'd use
                    // decreaseKey but Java's pq does not have this operation.
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
    }

    /**
     * An alternative version of dijkstras where only the src vertex is put into the pq to begin with.
     * @param graph The directed graph
     * @param s The source vertex
     */
    public static void dijkstras2(Graph<String> graph, Vertex<String> s) {
        Relaxation.initializeSingleSource(graph, s);
        PriorityQueue<Vertex<String>> pq = new PriorityQueue<>(Comparator.comparingInt(Vertex::getKey));
        pq.add(s);
        while (!pq.isEmpty()) {
            Vertex<String> u = pq.poll();
            for (Node<String> node: graph.getAdjList().get(u)) {
                Vertex<String> v = node.getVertex();
                Relaxation.relax2(u, v, node.getWeight(), pq);
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
            System.out.println(v.getVal() + " " + v.getKey());
        }
    }
}
