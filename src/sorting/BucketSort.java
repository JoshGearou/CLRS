package sorting;

import java.util.ArrayList;
import java.util.LinkedList;

public class BucketSort {

    private void insertionSort(LinkedList<Double> lst) {
        for (int i=1; i<lst.size(); i++) {
            double key = lst.get(i);
            int j = i-1;
            while (j > -1 && lst.get(j) > key) {
                lst.set(j+1, lst.get(j));
                j--;
            }
            lst.set(j+1, key);
        }
    }
    // numbers between [0.0, 1.0)
    private void bucketSort(double[] data) {
        ArrayList<LinkedList<Double>> temp = new ArrayList<>(data.length);
        int n = 10;
        for (int i=0; i<n; i++) {
            LinkedList<Double> lst = new LinkedList<>();
            temp.add(lst);
        }

        for (int i=0; i<data.length; i++) {
            LinkedList<Double> lst = temp.get((int) (n * data[i]));
            lst.add(data[i]);
        }

        for (int i=0; i<n; i++) {
            insertionSort(temp.get(i));
        }
        int index = 0;
        for (int i=0; i<temp.size(); i++) {
            for (int j=0; j<temp.get(i).size(); j++) {
                data[index++] = temp.get(i).get(j);
            }
        }
    }

    public static void main(String[] args) {
        double[] data = {.78,.17, .39, .26, .72, .94, .21, .12, .23, .68, .22, .56, .11};
        new BucketSort().bucketSort(data);
        for (double i: data) {
            System.out.println(i);
        }
    }
}
