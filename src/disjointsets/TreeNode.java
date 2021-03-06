package disjointsets;

public class TreeNode<E> {
    E val;
    int rank; // An upper bound on the height of the node
    TreeNode<E> parent;

    public TreeNode(E val) {
        this.val = val;
        makeSet(this);

    }

    /**
     * Creates a set with one element, x
     *
     * @param x Element to add to empty set
     * @return {x}
     */
    public TreeNode<E> makeSet(TreeNode<E> x) {
        x.parent = x;
        x.rank = 0;
        return x;
    }

    /**
     * Combines two disjoint sets into one (note this method assumes the sets are disjoint)
     *
     * @param x first set
     * @param y second set
     * @return new set
     */
    public static <E> TreeNode<E> union(TreeNode<E> x, TreeNode<E> y) {
        return link(findSet(x), findSet(y));
    }

    /**
     * @param x finds the representative element of the set that x belongs to (if it exists)
     * @return representative element
     */
    public static <E> TreeNode<E> findSet(TreeNode<E> x) {
        if (x.parent != x) {
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }

    /**
     * Helper method for #union to combine two disjoint sets
     *
     * @param x first set
     * @param y second set
     * @return root of new set
     */
    public static <E> TreeNode<E> link(TreeNode<E> x, TreeNode<E> y) {
        if (x.rank > y.rank) {
            y.parent = x;
            return x;
        } else {
            x.parent = y;
            if (x.rank == y.rank) {
                y.rank++;
            }
            return y;
        }
    }
}
