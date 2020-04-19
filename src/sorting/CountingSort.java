package sorting;

import java.util.Arrays;

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
        // Stores the count of each number.
        for (int i: data) {
            temp[i]++;
        }

        // Makes it so temp[i] contains the number of elements less than or equals to temp[i].
        for (int i=1; i<temp.length; i++) {
            temp[i] = temp[i] + temp[i-1];
        }
        // Place each element into its correct position in the list.
        for (int i=data.length-1; i>=0; i--) {
            sorted[temp[data[i]]-1] = data[i];
            temp[data[i]]--;
        }

        System.arraycopy(sorted, 0, data, 0, data.length);
    }

    public static void main(String[] args) {
        int[] data = {2,3,1,2,4,3,6};
        new CountingSort().countingSort(data, 6);
        for (int i: data) {
            System.out.println(i);
        }
    }
}
