package graphs.mst;

import disjointsets.UnionFind;

import java.sql.Array;
import java.util.*;

public class Kruskals {

    public List<int[]> mst(int[][] edges) {
        HashSet<Integer> vertices = new HashSet<>();
        for (int[] edge: edges) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }
        List<int[]> mstEdges = new ArrayList<>();
        int numVertices = vertices.size();
        UnionFind uf = new UnionFind(numVertices);
        Arrays.sort(edges, Comparator.comparingInt(x -> x[2]));

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            if (uf.findSet(u) != uf.findSet(v)) {
                uf.union(u, v);
                mstEdges.add(edge);
            }
        }
        return mstEdges;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1,4},{1,2,2},{0,2,4},{0,3,6},{2,3,8},{0,4,6},{3,4,9}};
        Kruskals kruskals = new Kruskals();
        List<int[]> res = kruskals.mst(edges);
        for (int[] edge: res) {
            System.out.println("u " + edge[0] + " v: " + edge[1]);
        }
    }
}
