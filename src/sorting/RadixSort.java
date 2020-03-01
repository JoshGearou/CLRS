package sorting;

public class RadixSort {

    private int getMax(int[] data) {
        int max = 0;
        for (int i=0; i<data.length; i++) {
            max = Math.max(data[i], max);
        }
        return max;
    }
    private void radixSort(int[] data) {
        int max = getMax(data);
        for (int exp=1; max/exp>0; exp*=10) {
            countingSort(data, exp);
        }
    }

    private void countingSort(int[] data, int exp) {
        int[] sorted = new int[data.length];
        int[] counter = new int[10];
        for (int i: data) {
            counter[((i/exp) % 10)]++;
        }

        for (int i=1; i<counter.length; i++) {
            counter[i] = counter[i] + counter[i-1];
        }

        for (int i=data.length-1; i>=0; i--) {
            sorted[counter[(data[i]/exp) % 10]-1] = data[i];
            counter[(data[i]/exp) % 10]--;
        }

        for (int i=0; i<sorted.length; i++) {
            data[i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        int[] data = {702,100,80,387,654,234,128,45,23};
        new RadixSort().radixSort(data);
        for (int i: data) {
            System.out.println(i);
        }
    }
}
