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
        return root;
    }

    public static class TreeNode<E> {
        E val;
        int rank;
        TreeNode<E> parent;

        public TreeNode(E val) {
            this.val = val;
            rank = 0;
        }
    }

    public SetTree<E> makeSet(TreeNode<E> x) {
        x.parent = x;
        x.rank = 0;
        this.root = x;
        return this;
    }

    // assumes x and y are in different sets
    public SetTree<E> union(TreeNode<E> x, TreeNode<E> y) {
        this.root = link(findSet(x), findSet(y));
        return this;
    }

    public TreeNode<E> findSet(TreeNode<E> x) {
        if (x.parent != x) {
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }

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
        System.out.println(new SetTree<Integer>().findSet(set1.getRoot()) == new SetTree<Integer>().findSet(set1.getRoot()));
    }
}
