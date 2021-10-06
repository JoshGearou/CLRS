package graphs.mst;

import java.util.*;

public class Prims {

    public List<int[]> mst(int[][][] adj, int start) {
        int n = adj.length;
        int[] distEst = new int[n];
        int[] parent = new int[n];
        List<int[]> mstEdges = new ArrayList<>();
        Arrays.fill(parent, -1);
        for (int i=0; i<n; i++) {
            distEst[i] = Integer.MAX_VALUE;
        }
        distEst[start] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> distEst[x]));
        for (int i=0; i<n; i++) {
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (parent[u] != -1) {
                mstEdges.add(new int[]{u, parent[u]});
            }
            for (int[] neighbor: adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if (pq.contains(v) && weight < distEst[v]) {
                    parent[v] = u;
                    distEst[v] = weight;
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        return mstEdges;
    }

    public static void main(String[] args) {
        int[][][] adjList = new int[][][]{{{1,4},{2,4},{3,6},{4,6}},{{2,2},{0,4}},{{0,4},{1,2},{3,8}},{{0,6},{2,8},{4,9}},{{0,6},{3,9}}};
        List<int[]> mstEdges = new Prims().mst(adjList, 0);
        for (int[] edge: mstEdges) {
            System.out.println("u: " + edge[0] + " v: " + edge[1]);
        }
    }
}
