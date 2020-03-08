package graphalgorithms.mst;

import disjointsets.SetTree;
import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;


public class Kruskal {
    HashMap<Vertex<String>, SetTree<Vertex<String>>> map = new HashMap<>();

    public Set<Edge<String>> findMST(Graph<String> graph) {
        for (Vertex<String> v: graph.getVertices()) {
            SetTree<Vertex<String>> set = new SetTree<>(v);
            map.put(v, set);
        }

        Collections.sort(graph.getEdges(), Comparator.comparingInt(Edge::getWeight));
        graph.getEdges().forEach(e -> {
            SetTree.TreeNode<Vertex<String>> srcTree = map.get(e.getSrc()).getRoot();
            SetTree.TreeNode<Vertex<String>> destTree = map.get(e.getDest()).getRoot();
            if (new SetTree<Vertex<String>>().findSet(srcTree) != new SetTree<Vertex<String>>().findSet(destTree)) {
                graph.getA().add(e);
                new SetTree<Vertex<String>>().union(srcTree, destTree);
            }
        });
        return graph.getA();
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
        g.addEdge("a", "b", 4);
        g.addEdge("a", "h", 8);
        g.addEdge("b", "c", 8);
        g.addEdge("b", "h", 11);
        g.addEdge("c", "d", 7);
        g.addEdge("c", "i", 2);
        g.addEdge("c", "f", 4);
        g.addEdge("d", "e", 9);
        g.addEdge("d", "f", 14);
        g.addEdge("e", "f", 10);
        g.addEdge("f", "g", 2);
        g.addEdge("g", "i", 6);
        g.addEdge("g", "h", 1);
        g.addEdge("h", "i", 7);

        Set<Edge<String>> mstEdges = new Kruskal().findMST(g);
        for (Edge<String> e: mstEdges) {
            System.out.println(e.getWeight());
        }
    }
}
