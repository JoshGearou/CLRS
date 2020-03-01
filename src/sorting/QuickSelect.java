package sorting;

public class QuickSelect {

    private int quickSelect(int[] data, int s, int e, int k) {
        if (s < e) {
            int q = randomParition(data, s, e);
            if (q == k) {
                return data[q];
            }
            else if (k < q) {
                return quickSelect(data, s, q-1, k);
            } else {
                return quickSelect(data, q+1, e, k);
            }
        }
        return -1;
    }

    private int randomParition(int[] data, int s, int e) {
        int num = ((int) (Math.random() * ((e - s) + 1) + s));
        swap(data, num, e);
        return partition(data, s, e);
    }

    private int partition(int[] data, int s, int e) {
        int x = data[e];
        int i = s-1;
        for (int j=s; j<e; j++) {
            if (data[j] <= x) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data,i+1, e);
        return i+1;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = {4,8,6,2,3,7};
        System.out.println(new QuickSelect().quickSelect(data, 0, data.length-1, 5));
    }
}
