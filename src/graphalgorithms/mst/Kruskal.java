package graphalgorithms.mst;

import disjointsets.TreeNode;
import graphalgorithms.Edge;
import graphalgorithms.Graph;
import graphalgorithms.Vertex;


import java.util.*;

import static disjointsets.TreeNode.findSet;
import static disjointsets.TreeNode.union;


public class Kruskal {
    HashMap<Vertex<String>, TreeNode<Vertex<String>>> map = new HashMap<>();

    public Set<Edge<String>> findMST(Graph<String> graph) {
        Set<Edge<String>> A = new HashSet<>();
        for (Vertex<String> v: graph.getVertices()) {
            TreeNode<Vertex<String>> set = new TreeNode<>(v);
            map.put(v, set);
        }

        Collections.sort(graph.getEdges(), Comparator.comparingInt(Edge::getWeight));
        graph.getEdges().forEach(e -> {
            TreeNode<Vertex<String>> srcTree = map.get(e.getSrc());
            TreeNode<Vertex<String>> destTree = map.get(e.getDest());
            if (findSet(srcTree) != findSet(destTree)) {
                A.add(e);
                union(srcTree, destTree);
            }
        });
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

        Set<Edge<String>> mstEdges = new Kruskal().findMST(g);
        for (Edge<String> e: mstEdges) {
            System.out.println(e.getWeight());
        }
    }
}
