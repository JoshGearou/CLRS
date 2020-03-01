package maximumsubarray;

public class MaximumSubarray2 {
    public static class Elem {
        public int sum;
        public int leftIndex;
        public int rightIndex;
        public Elem(int sum, int leftIndex, int rightIndex) {
            this.sum = sum;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
    }
    public Elem findMaxCrossingSubarray(int[] data, int low, int mid, int high) {
        Elem crossing = new Elem(Integer.MIN_VALUE, 0,0);
        int sum = 0;
        for (int i=mid; i>=low; i--) {
            sum = sum + data[i];
            if (sum > crossing.sum) {
                crossing.sum = sum;
                crossing.leftIndex = i;
            }
        }
        sum = 0;
        int leftSum = crossing.sum;
        crossing.sum = Integer.MIN_VALUE;
        for (int j=mid+1; j<=high; j++) {
            sum = sum+data[j];
            if (sum > crossing.sum) {
                crossing.sum = sum;
                crossing.rightIndex = j;
            }
        }
        crossing.sum = crossing.sum + leftSum;
        return crossing;
    }

    public Elem findMaximumSubarray(int[] data, int low, int high) {
        if (low>=high) {
            return new Elem(data[low], low, high);
        }
        else {
            int mid = (low+high)/2;
           Elem left = findMaximumSubarray(data, low, mid);
           Elem right = findMaximumSubarray(data, mid+1, high);
           Elem cross = findMaxCrossingSubarray(data, low, mid, high);
            if (left.sum >= right.sum && left.sum >= cross.sum) {
                return left;
            }
            else if (right.sum >= left.sum && right.sum >= cross.sum) {
                return right;
            }
            else {
                return cross;
            }
        }
    }
}
