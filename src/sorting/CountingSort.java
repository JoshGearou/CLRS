package sorting;

// Use counting sort if k=O(n), which means the sort is also O(n).
public class CountingSort {
    private void countingSort1(int[] data, int k) {
        int[] counter = new int[k+1];
        for (int i: data) {
            counter[i]++;
        }

        int index = 0;
        for (int i=0; i<counter.length; i++) {
            while (counter[i] > 0) {
                data[index++] = i;
                counter[i]--;
            }
        }
    }

    private void countingSort2(int[] data, int k) {
        countingSort(data, k);
    }
    // CLRS way
    private void countingSort(int[] data, int k) {
        int[] temp = new int[k+1];
        int[] sorted = new int[data.length];
        for (int i: data) {
            temp[i]++;
        }
        // now temp[i] contains the number of elements equal to i.
        for (int i=1; i<temp.length; i++) {
            temp[i] = temp[i] + temp[i-1];
        }
        // now temp[i] contains the number of elements less than or equals to i.
        for (int i=data.length-1; i>=0; i--) {
            sorted[temp[data[i]]-1] = data[i];
            temp[data[i]]--;
        }
        for (int i=0; i<sorted.length; i++) {
            data[i] = sorted[i];
        }
    }
}
