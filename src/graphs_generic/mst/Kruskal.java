package graphs_generic.mst;

import disjointsets.TreeNode;
import graphs_generic.Edge;
import graphs_generic.Graph;
import graphs_generic.Vertex;


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

        List<Edge<String>> edges = graph.getUndirectedEdges();
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));
        edges.forEach(e -> {
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
        Graph<String> g = new Graph<>();
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
