package heaps;

import java.util.ArrayList;

public class MaxHeap {
    ArrayList<Integer> heap;
    int size;
    public MaxHeap() {
        heap = new ArrayList<>();
        heap.add(Integer.MAX_VALUE);
        size = 0;
    }

    private void heapSort() {
        buildHeap();
        for(int i=getSize(); i>=2; i--) {
            swap(1, i);
            setSize(getSize()-1);
            maxHeapify(1);
        }
    }

    private void setHeap(ArrayList<Integer> data) {
        heap.addAll(data);
    }

    /* Builds a heap from an array */
    public void buildHeap() {
        setSize(heap.size()-1);
        for (int i=heap.size()/2; i>=1; i--) {
            maxHeapify(i);
        }
    }

    public void maxHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int largest = i;
        if (left <= getSize() && heap.get(left) > heap.get(largest)) {
            largest = left;
        }

        if (right <= getSize() && heap.get(right) > heap.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }

    }

    private int left(int i) {
        return i * 2;
    }

    private int right(int i) {
        return (i * 2) + 1;
    }

    private int parent(int i) {
        return i/2;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int newSize) {
        size = newSize;
    }

    public void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    /* Priority Queue operations */

    private int getMax() {
        return getSize() > 1 ? heap.get(1): -1;
    }

    private int extractMax() {
        if (getSize() <= 1) {
            return -1;
        }
        int max = heap.get(1);
        swap(1, getSize());
        setSize(getSize()-1);
        maxHeapify(1);
        return max;
    }

    private void increaseKey(int i, int key) {
        if (key < heap.get(i)) {
            System.out.println("new key should be greater than or equal to existing key");
        }
        heap.set(i, key);

        while (i > 1 && heap.get(parent(i)) < heap.get(i)) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void insertKey(int key) {
        setSize(getSize()+1);
        heap.set(getSize(), Integer.MIN_VALUE);
        increaseKey(getSize(), key);
    }

    public static void main(String[] args) {

    }
}
