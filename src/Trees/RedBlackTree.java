package Trees;

public class RedBlackTree {
    private static Color red = Color.red;
    private static Color black = Color.black;
    enum Color {
        red,
        black
    }
    TreeNode root;
    TreeNode nil = new TreeNode(-1, black);

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        Color color;
        public TreeNode(int val, Color color) {
            this.val = val;
            left = nil;
            right = nil;
            parent = nil;
            this.color = color;
        }
    }

    private TreeNode minimum (TreeNode x) {
        while (x.left != nil) {
            x = x.left;
        }
        return x;
    }

    private TreeNode maximum(TreeNode x) {
        while (x.right != nil) {
            x = x.right;
        }
        return x;
    }

    private void transplant(TreeNode y, TreeNode x) {
        if (y.parent == nil) {
            root = x;
        }
        else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.parent = y.parent;
    }

    // assumes x.right != nil
    public void leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        }
        if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    // assumes x.left != nil
    public void rightRotate(TreeNode x) {
        TreeNode y = x.left;
        x.left = y.right;
        if (y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        }
        if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        TreeNode y = nil;
        TreeNode x = root;
        while (x != nil) {
            y = x;
            if (key < x.val) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        TreeNode newNode = new TreeNode(key, red);
        newNode.parent = y;
        if (y == nil) {
            root = newNode;
        } else if (key < y.val) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }
        insertFixup(newNode);
    }

    public void insertFixup(TreeNode x) {
        while (x.parent.color.equals(red)) { // x's parent is red
            if (x.parent == x.parent.parent.left) {
                TreeNode y = x.parent.parent.right;
                if (y.color.equals(red)) {  // case 1
                    x.parent.color = black;
                    x.parent.parent.color = red;
                    y.color = black;
                    x = x.parent.parent;
                } else {
                    if (x == x.parent.right) {  // case 2
                        x = x.parent;
                        leftRotate(x);
                    }
                    x.parent.color = black;       // case 3
                    x.parent.parent.color = red;
                    rightRotate(x.parent.parent);
                }
            } else {
                TreeNode y = x.parent.parent.left;   // case 4
                if (y.color.equals(red)) {
                    x.parent.color = black;
                    x.parent.parent.color = red;
                    y.color = black;
                } else {
                    if (x == x.parent.left) {  // case 5
                        x = x.parent;
                        rightRotate(x);
                    }
                    x.parent.color = black;
                    x.parent.parent.color = red;
                    leftRotate(x.parent.parent);
                }
            }
        }
        root.color = black;
    }

    public void delete(TreeNode z) {
        TreeNode y = z;
        TreeNode x = nil;
        Color yOriginalColor = y.color;
        if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
            if (yOriginalColor == black) {
                deleteFixup(x);
            }
        }
    }
    // x is doubly black inside the outer while
    public void deleteFixup(TreeNode x) {
        while (x != root && x.color == Color.black) {
            if (x == x.parent.left) {
                TreeNode w = x.parent.right;
                if (w.color == red) { // case 1
                    w.color = black;
                    x.parent.color = red;
                    leftRotate(x.parent);
                }
                if (w.left.color == black && w.right.color == black) { // case 2
                    w.color = red;
                    x = x.parent;
                } else if (w.right.color == black) { // case 3
                    w.color = red;
                    w.left.color = black;
                    rightRotate(w);
                    w = x.parent.right;
                }
                w.color = x.parent.color;      // case 4
                x.parent.color = black;
                w.right.color = black;
                leftRotate(x.parent);
                x = root;
            } else {
                TreeNode w = x.parent.left;
                if (w.color == red) {  // case 5
                    w.color = black;
                    x.parent.color = red;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == black && w.right.color == black) { // case 6
                    w.color = red;
                    x = x.parent;
                } else if (w.left.color == black) {  // case 7
                    w.color = red;
                    w.right.color = black;
                    leftRotate(w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;  // case 8
                x.parent.color = black;
                w.left.color = black;
                rightRotate(x.parent);
                x = root;
            }
        }
        x.color = Color.black;
    }
}
