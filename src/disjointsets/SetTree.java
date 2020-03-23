package disjointsets;

public class SetTree<E> {
    TreeNode<E> root;

    public SetTree() {

    }

    public SetTree(E val) {
        TreeNode<E> temp = new TreeNode<>(val);
        makeSet(temp);
    }

    public TreeNode<E> getRoot() {
        return root; // The representative element of each disjoint set.
    }

    public static class TreeNode<E> {
        E val;
        int rank; // An upper bound on the height of the node
        TreeNode<E> parent;

        public TreeNode(E val) {
            this.val = val;
            rank = 0;
        }
    }

    /**
     * Creates a set with one element, x
     * @param x Element to add to empty set
     * @return {x}
     */
    public SetTree<E> makeSet(TreeNode<E> x) {
        x.parent = x;
        x.rank = 0;
        this.root = x;
        return this;
    }

    /**
     * Combines two disjoint sets into one (note this method assumes the sets are disjoint)
     * @param x first set
     * @param y second set
     * @return new set
     */
    public SetTree<E> union(TreeNode<E> x, TreeNode<E> y) {
        this.root = link(findSet(x), findSet(y));
        return this;
    }

    /**
     *
     * @param x finds the representative element of the set that x belongs to (if it exists)
     * @return representative element
     */
    public TreeNode<E> findSet(TreeNode<E> x) {
        if (x.parent != x) {
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }

    /**
     * Helper method for #union to combine two disjoint sets
     * @param x first set
     * @param y second set
     * @return root of new set
     */
    public TreeNode<E> link(TreeNode<E> x, TreeNode<E> y) {
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

    public static void main(String[] args) {
        SetTree<Integer> set1 = new SetTree<>(1);
        SetTree<Integer> set2 = new SetTree<>(2);
        new SetTree<Integer>().union(set1.getRoot(), set2.getRoot());
        System.out.println(new SetTree<Integer>().findSet(set1.getRoot()) == new SetTree<Integer>().findSet(set2.getRoot()));
    }
}
