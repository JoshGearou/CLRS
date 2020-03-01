public class DailyCoding {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Elem {
        int next;
        String tree;

        public Elem(int next, String tree) {
            this.next = next;
            this.tree = tree;
        }
    }

    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder());
    }

    public String serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null");
        }
        else {
            sb.append("N" + root.val + " (");
            serialize(root.left, sb);
            sb.append(")" + " (");
            serialize(root.right, sb);
            sb.append(")");
        }

        return sb.toString();
    }

    public TreeNode deserialize(String root) {
        return deserialize(root, 0);
    }

    public TreeNode deserialize(String root, int index) {
        if (root.equals("null")) {
            return null;
        }

        index++;
        int val = Character.getNumericValue(root.charAt(index));
        index += 2;
        Elem left = findTree(root, index);
        Elem right = findTree(root, left.next);
        TreeNode newRoot = new TreeNode(val, deserialize(left.tree, 0), deserialize(right.tree, 0));
        return newRoot;
    }

    public Elem findTree(String tree, int index) {
        int stack = 1;
        index++;
        StringBuilder str = new StringBuilder();
        while (stack > 0) {
            if (tree.charAt(index) == '(') {
                str.append(tree.charAt(index));
                stack++;
            } else if (tree.charAt(index) == ')') {
                stack--;
                if (stack == 0) {
                    continue;
                }
                str.append(tree.charAt(index));
            } else {

                str.append(tree.charAt(index));
            }
            index++;
        }
        return new Elem(index + 2, str.toString());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, null));
        String ser = new DailyCoding().serialize(root);
        System.out.println(ser);
        TreeNode des = new DailyCoding().deserialize(ser);
        System.out.println(new DailyCoding().serialize(des));
    }
}
