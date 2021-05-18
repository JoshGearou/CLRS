package Trees;

public class SegmentTree {
    int[] st;
    int[] data;
    int n;

    private SegmentTree(int[] data) {
        n = data.length;
        this.data = data;
        st = new int[n * 4];
    }

    private int left(int index) {
        return 2 * index;
    }

    private int right(int index) {
        return (2 * index) + 1;
    }

    private void buildRMSTree(int root, int start, int end) {
        if (start == end) {
            st[root] = start;
        } else {
            int mid = (end - start) / 2 + start;
            buildRMSTree(left(root), start, mid);
            buildRMSTree(right(root), mid + 1, end);
            int minIndexLeft = st[left(root)];
            int minIndexRight = st[right(root)];
            st[root] = data[minIndexLeft] <= data[minIndexRight] ? minIndexLeft : minIndexRight;
        }
    }

    private void updateRMSTree(int index, int val) {
        updateRMSTree(1, 0, n - 1, index, val);
    }

    private void updateRMSTree(int root, int start, int end, int index, int val) {
        if (start == end) {
            data[index] = val;
        } else {
            int mid = (end - start) / 2 + start;
            if (index >= start && index <= mid) {
                updateRMSTree(left(root), start, mid, index, val);
            } else {
                updateRMSTree(right(root), mid + 1, end, index, val);
            }
            int minLeftIndex = st[left(root)];
            int minRightIndex = st[right(root)];
            st[root] = data[minLeftIndex] < data[minRightIndex] ? minLeftIndex : minRightIndex;
        }
    }

    private int rangeMinimumQuery(int i, int j) {
        return rangeMinimumQuery(1, 0, n - 1, i, j);
    }

    private int rangeMinimumQuery(int root, int start, int end, int i, int j) {
        if (i > end || j < start) {
            return -1;
        }
        if (i <= start && j >= end) {
            return st[root];
        }

        int mid = (end - start) / 2 + start;
        int minIndexLeft = rangeMinimumQuery(left(root), start, mid, i, j);
        int minIndexRight = rangeMinimumQuery(right(root), mid+1, end, i, j);

        if (minIndexLeft == -1) {
            return minIndexRight;
        }
        if (minIndexRight == -1) {
            return minIndexLeft;
        }
        return (data[minIndexLeft] <= data[minIndexRight]) ? minIndexLeft : minIndexRight;
    }

    private void buildRSQTree(int root, int start, int end) {
        if (start == end) {
            st[root] = data[start];
        } else {
            int mid = (end - start) / 2 + start;
            buildRSQTree(left(root), start, mid);
            buildRSQTree(right(root), mid + 1, end);
            int leftSum = st[left(root)];
            int rightSum = st[right(root)];
            st[root] = leftSum + rightSum;
        }
    }

    private void updateRSQTree(int index, int val) {
        updateRSQTree(1, 0, n - 1, index, val);
    }

    private void updateRSQTree(int root, int start, int end, int index, int val) {
        if (start == end) {
            data[index] = val;
            st[root] = val;
        } else {
            int mid = (end - start) / 2 + start;
            if (index >= start && index <= mid) {
                updateRSQTree(left(root), start, mid, index, val);
            } else {
                updateRSQTree(right(root), mid + 1, end, index, val);
            }
            int leftSum = st[left(root)];
            int rightSum = st[right(root)];
            st[root] = leftSum + rightSum;
        }
    }

    private int rangeSumQuery(int i, int j) {
        return rangeSumQuery(1, 0, n - 1, i, j);
    }

    private int rangeSumQuery(int root, int start, int end, int i, int j) {
        if (i > end || j < start) {
            return 0;
        }
        if (i <= start && j >= end) {
            return st[root];
        }

        int mid = (end - start) / 2 + start;
        int leftSum = rangeSumQuery(left(root), start, mid, i, j);
        int rightSum = rangeSumQuery(right(root), mid + 1, end, i, j);

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] data = {18, 17, 13, 19, 15, 11, 20};
        SegmentTree tree = new SegmentTree(data);
        tree.buildRSQTree(1, 0, data.length - 1);
        tree.updateRSQTree(5, 99);
        System.out.println(tree.rangeSumQuery(4, 5));
    }
}
