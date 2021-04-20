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

    private int union(int firstSetElement, int secondSetElement) {
        return link(findSet(firstSetElement), findSet(secondSetElement));
    }

    private int findSet(int element) {
        if (parent[element] != element) {
            parent[element] = findSet(parent[element]);
        }
        return parent[element];
    }

    private int link(int firstSetElement, int secondSetElement) {
        if (rank[firstSetElement] > rank[secondSetElement]) {
            parent[secondSetElement] = firstSetElement;
        } else {
            if (rank[firstSetElement] == rank[secondSetElement]) {
                rank[firstSetElement]++;
            }
            parent[secondSetElement] = firstSetElement;
        }
        return firstSetElement;
    }
}
