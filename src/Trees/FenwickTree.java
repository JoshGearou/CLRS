package Trees;

// Also known as a Fenwick Tree
public class FenwickTree {
    int[] ft;
    int[] nums;
    public FenwickTree(int[] nums) {
        this.nums = nums;
        createFenwickTree(nums);
    }

    public void update(int index, int val) {
        nums[index]+=val;
        index = index + 1;
        while (index < ft.length) {
            ft[index]+= val;
            index = index + (index & -index); // add last set bit
        }
    }

    public int rangeSumQuery(int index) {
        index = index+1;
        int sum = 0;
        while (index > 0) {
            sum+= ft[index];
            index = index - (index & -index); // remove last set bit
        }
        return sum;
    }

    // returns sum between i and j (inclusive on both ends)
    public int rangeSumQuery(int i, int j) {
        return rangeSumQuery(j) - rangeSumQuery(i-1);
    }

    public void createFenwickTree(int[] nums) {
        ft = new int[nums.length+1];
        for (int i = 1; i< ft.length; i++) {
            ft[i] = nums[i-1];
        }
        for (int i = 1; i< ft.length; i++) {
            int parent = i + (i & -i);
            if (parent < ft.length) {
                ft[parent] += ft[i];
            }
        }
    }

    public static void main (String[] args) {
//        int[] nums = {5,2,9,-3,5,20,10,-7,2,3,-4,0,-2,15,5};
//        FenwickTree tree = new FenwickTree(nums);
//        System.out.println(tree.rangeSumQuery(4,6));
    }
}
