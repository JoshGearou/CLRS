package maximumsubarray;

public class MaximumSubarray {
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

    public Elem maximumSubarray(int [] data) {
        Elem max = new Elem(Integer.MIN_VALUE, 0, data.length);
        int res = 0;
        for (int i=0; i<data.length; i++) {
            for (int j=i; j<data.length; j++) {
                res+=data[j];
                if (res > max.sum) {
                    max.sum = res;
                    max.leftIndex = i;
                    max.rightIndex = j;
                }
            }
            res = 0;
        }
        return max;
    }
}
