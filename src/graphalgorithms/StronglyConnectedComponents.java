package graphalgorithms;

import disjointsets.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import static disjointsets.TreeNode.findSet;
import static disjointsets.TreeNode.union;

public class StronglyConnectedComponents {

    public HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> findSCCUndirectedGraph(Graph<Integer> graph) {
        HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> map = new HashMap<>();
        for (Vertex<Integer> v : graph.getVertices()) {
            TreeNode<Vertex<Integer>> val = new TreeNode<>(v);
            map.put(v, val);
        }

        for (Edge<Integer> e : graph.getEdges()) {
            if (!findSet(map.get(e.getSrc())).equals(findSet(map.get(e.getDest())))) {
                union(map.get(e.getSrc()), map.get(e.getDest()));
            }
        }
        return map;
    }

    public HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> findSCCDirectedGraph(Graph<Integer> graph) {
        HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> map = new HashMap<>();
        HashMap<Vertex<Integer>, Vertex<Integer>> toReversedMap = new HashMap<>();

        Stack<Vertex<Integer>> stack = new Stack<>();
        addToStack(graph, stack);
        Graph<Integer> reversed = reverseGraph(graph, toReversedMap);
        for (Vertex<Integer> u : reversed.getVertices()) {
            TreeNode<Vertex<Integer>> val = new TreeNode<>(u);
            map.put(u, val);
        }
        return findSCCDirectedGraph(reversed, map, stack, toReversedMap);

    }

    public HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> findSCCDirectedGraph(Graph<Integer> graph, HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> roots, Stack<Vertex<Integer>> stack, HashMap<Vertex<Integer>, Vertex<Integer>> toReversedMap) {
        HashSet<Vertex<Integer>> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            Vertex<Integer> u = stack.pop();
            if (!visited.contains(toReversedMap.get(u))) {
                dfs(toReversedMap.get(u), roots, visited, graph);
                System.out.println();
            }
        }

        return roots;
    }

    public void dfs(Vertex<Integer> u, HashMap<Vertex<Integer>, TreeNode<Vertex<Integer>>> map, HashSet<Vertex<Integer>> visited, Graph<Integer> graph) {
        visited.add(u);
        System.out.println(u.getVal());
        for (Node<Integer> neighbor : graph.getAdjList().get(u)) {
            Vertex<Integer> v = neighbor.getVertex();
            if (!visited.contains(v)) {
                union(map.get(u), map.get(v));
                dfs(v, map, visited, graph);
            }
        }
    }

    public void addToStack(Graph<Integer> graph, Stack<Vertex<Integer>> stack) {
        HashSet<Vertex<Integer>> visited = new HashSet<>();
        for (Vertex<Integer> u : graph.getAdjList().keySet()) {
            if (!visited.contains(u)) {
                addToStack(u, stack, visited, graph);
            }
        }
    }

    public void addToStack(Vertex<Integer> u, Stack<Vertex<Integer>> stack, HashSet<Vertex<Integer>> visited, Graph<Integer> graph) {
        visited.add(u);
        for (Node<Integer> neighbor : graph.getAdjList().get(u)) {
            Vertex<Integer> v = neighbor.getVertex();
            if (!visited.contains(v)) {
                addToStack(v, stack, visited, graph);
            }
        }

        stack.push(u);
    }

    public Graph<Integer> reverseGraph(Graph<Integer> graph, HashMap<Vertex<Integer>, Vertex<Integer>> map) {
        Graph<Integer> reversed = new Graph<>();
        for (Vertex<Integer> u : graph.getAdjList().keySet()) {
            for (Node<Integer> neighbor : graph.getAdjList().get(u)) {
                Vertex<Integer> v = neighbor.getVertex();
                Vertex<Integer> newU = new Vertex<>(u.getVal());
                Vertex<Integer> newV = new Vertex<>(v.getVal());
                reversed.addDirectedEdge(newV, newU, neighbor.weight);
                map.put(u, new Vertex<>(newU.getVal()));
                map.put(v, new Vertex<>(newV.getVal()));
            }
        }
        return reversed;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addDirectedEdge(1, 0, 0);
        graph.addDirectedEdge(2, 1, 0);
        graph.addDirectedEdge(0, 2, 0);
        graph.addDirectedEdge(0, 3, 0);
        graph.addDirectedEdge(3, 4, 0);
        new StronglyConnectedComponents().findSCCDirectedGraph(graph);
    }
}
