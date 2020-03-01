package sorting;

import java.util.Random;

public class Quicksort {
    private void quicksort(int[] data, int s, int e) {
        if (s < e) {
            int q = partition(data, s, e);
            System.out.println(q);
            quicksort(data, s, q-1);
            quicksort(data, q+1, e);
        }
    }

    private int partition(int[] data, int s, int e) {
        int i = s-1;
        int x = data[e];
        for (int j=s; j<e; j++) {
            if (data[j] <= x) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i+1, e);
        return i+1;
    }

    private int randomPartition(int[] data, int s, int e) {
        int i = (int) (Math.random() * ((e - s) + 1) + s);
        swap(data, i, e);
        return partition(data, s, e);
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main (String[] args) {
        int[] data = {5,3,2,6,8,10,4};
        new Quicksort().quicksort(data, 0, data.length-1);
//        for (int i: data) {
//            System.out.println(i);
//        }

    }
}
