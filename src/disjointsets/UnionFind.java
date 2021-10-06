package disjointsets;

public class UnionFind {
    int[] rank;
    int[] parent;

    public UnionFind(int size) {
        rank = new int[size];
        parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int union(int firstSetElement, int secondSetElement) {
        return link(findSet(firstSetElement), findSet(secondSetElement));
    }

    public int findSet(int element) {
        if (parent[element] != element) {
            parent[element] = findSet(parent[element]);
        }
        return parent[element];
    }

    private int link(int first, int second) {
        if (rank[first] > rank[second]) {
            parent[second] = first;
            return first;
        } else {
            if (rank[first] == rank[second]) {
                rank[first]++;
            }
            parent[first] = second;
            return second;
        }
    }
}
