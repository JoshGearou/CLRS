package sorting;

public class InsertionSort {

    int [] data = null;
    public InsertionSort(int [] data) {
        this.data = data;
    }
    public void insertionSort(int [] data) {
        for (int j=1; j<data.length; j++) {
            int key = data[j];
            int i = j-1;
            while (i > -1 && data[i] > key) {
                data[i+1] = data[i];
                i=i-1;
            }
            data[i+1] = key;
        }
    }
}
