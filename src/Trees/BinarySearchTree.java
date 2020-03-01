package Trees;

public class BinarySearchTree {
    TreeNode root;
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    private TreeNode minimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode maximum(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private void inorderTraversal(TreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println(node.val);
            inorderTraversal(node.right);
        }
    }

    private TreeNode search(TreeNode node, int key) {
        if (node == null || node.val == key) {
            return node;
        }
        if (key < node.val) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    private TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        TreeNode p = node.parent;
        while (p != null && p.right == node) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    private TreeNode predecessor(TreeNode node) {
        if (node.left != null) {
            return maximum(node.left);
        }
        TreeNode p = node.parent;
        while (p != null && p.left == node) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    private void insert(int key) {
        TreeNode y = null;
        TreeNode x = root;
        while (x != null) {
            y = x;
            if (x.val < key) {
                x = x.right;
            } else {
                x = x.left;
            }
        }
        TreeNode newNode = new TreeNode(key);
        newNode.parent = y;
        if (y == null) { // the tree is empty
            root = newNode;
        } else if (y.val < key) {
            y.right = newNode;
        } else {
            y.left = newNode;
        }
    }

    private void delete(TreeNode x) {
        if (x.left == null) {
            transplant(x, x.right);
        }
        else if (x.right == null) {
            transplant(x, x.left);
        }
        else {
            TreeNode succ = minimum(x.right);
            if (succ.parent != x) { // x's successor is somewhere in its right subtree (but is not x's right child)
                transplant(succ, succ.right);
                succ.right = x.right;
                succ.right.parent = succ;
            }
            transplant(x, succ);
            succ.left = x.left;
            succ.left.parent = succ;
        }
    }

    /**
     * replaces x with y
     * @param x the subtree to be replaced
     * @param y the subtree that replaces the other (x)
     */
    private void transplant(TreeNode x, TreeNode y) {
        if (x.parent == null) {
            root = y;
        }
        else if (x.parent.left == x) {
            x.parent.left = y;
        }
        else if (x.parent.right == x) {
            x.parent.right = y;
        }
        if (y != null) {
            y.parent = x.parent;
        }
    }
}
