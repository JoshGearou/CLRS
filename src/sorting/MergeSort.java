package sorting;

public class MergeSort {

    int [] data = null;
    public MergeSort(int [] data) {
        this.data = data;
    }

    public void mergeSort(int [] data, int s, int e) {
        if (s < e) {
            int m = (s+e)/2;
            mergeSort(data, s, m);
            mergeSort(data, m+1, e);
            merge(data, s,m,e);
        }
    }
    public void merge(int [] data, int s, int m, int e) {
        int n1 = m-s+1;
        int n2 = e-m;
        int [] left = new int[n1+1];
        int [] right = new int[n2+1];
        for (int i=0; i<n1; i++) {
            left[i] = data[s+i];
        }
        for (int j=0; j<n2; j++) {
            right[j] = data[m+1+j];
        }
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;

        int a = 0;
        int b = 0;
        for (int i=s; i<=e; i++) {
            if (left[a] <= right[b]) {
                data[i] = left[a];
                a++;
            }
            else {
                data[i] = right[b];
                b++;
            }
        }
    }
}
