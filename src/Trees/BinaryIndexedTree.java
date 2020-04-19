package Trees;

// Also known as a Fenwick Tree
public class BinaryIndexedTree {
    int[] bit;
    public BinaryIndexedTree(int[] nums) {
        createBitIndexTree(nums);
    }

    public void update(int index, int val) {
        index = index + 1;
        while (index < bit.length) {
            bit[index]+= val;
            index+=index & -index; // add last set bit
        }
    }

    public int getSum(int index) {
        index = index+1;
        int sum = 0;
        while (index > 0) {
            sum+=bit[index];
            index-=index & -index; // remove last set bit
        }
        return sum;
    }

    // returns sum between i and j (inclusive on both ends)
    public int getSum(int i, int j) {
        return getSum(j) - getSum(i-1);
    }

    public void createBitIndexTree(int[] nums) {
        bit = new int[nums.length+1];
        for (int i=1; i<bit.length; i++) {
            bit[i] = nums[i-1];
        }
        for (int i=1; i<bit.length; i++) {
            int parent = i + (i & -i);
            if (parent < bit.length) {
                bit[parent]+=bit[i];
            }
        }
    }

    public static void main (String[] args) {
//        int[] nums = {5,2,9,-3,5,20,10,-7,2,3,-4,0,-2,15,5};
//        BinaryIndexedTree tree = new BinaryIndexedTree(nums);
//        System.out.println(tree.getSum(7,8));
    }
}
