package graphalgorithms.shortestpaths;

import graphalgorithms.Graph;
import graphalgorithms.Vertex;

import java.util.PriorityQueue;

public class Relaxation {

    public static void initializeSingleSource(Graph<String> g, Vertex<String> s) {
        g.getVertices().forEach(v -> {
            v.setParent(null);
            v.setKey(Integer.MAX_VALUE);
        });
        s.setKey(0);
    }

    public static void relax(Vertex<String> u, Vertex<String> v, int weight) {
        if (v.getKey() > (long) u.getKey() + weight) {
            v.setKey(u.getKey() + weight);
            v.setParent(u);
        }
    }

    // relax that also takes a priority queue for dijkstra's algorithm
    public static void relax(Vertex<String> u, Vertex<String> v, int weight, PriorityQueue<Vertex<String>> pq) {
        if (v.getKey() > (long) u.getKey() + weight) {
            v.setKey(u.getKey() + weight);
            v.setParent(u);
            // need to reinsert v as v.getKey() may have changed.  Ideally we'd use decreaseKey with a binary heap.
            pq.remove(v);
            pq.add(v);
        }
    }

    // relax that also takes a priority queue for dijkstra's algorithm
    public static void relax2(Vertex<String> u, Vertex<String> v, int weight, PriorityQueue<Vertex<String>> pq) {
        if (v.getKey() > (long) u.getKey() + weight) {
            v.setKey(u.getKey() + weight);
            v.setParent(u);
            if (!pq.contains(v)) {
                pq.add(v);
            }
        }
    }
}
