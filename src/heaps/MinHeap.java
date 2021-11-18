package heaps;

import java.util.ArrayList;

public class MinHeap {
    ArrayList<Integer> heap;
    int size;
    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(Integer.MIN_VALUE);
        size = 0;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int newSize) {
        size = newSize;
    }

    private void setHeap(ArrayList<Integer> data) {
        heap.addAll(data);
    }

    private void minHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;

        if (left <=size && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }

        if (right <= size && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private void buildHeap() {
        setSize(heap.size()-1);
        for (int i=heap.size()/2; i>=1; i--) {
            minHeapify(i);
        }
    }

    // sorts array in descending order
    private void heapSort() {
        buildHeap();
        for (int i=size; i>=2; i--) {
            swap(1, i);
            setSize(getSize()-1);
            minHeapify(1);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int left(int i) {
        return (2 * i);
    }

    private int right(int i) {
        return (2 * i) + 1;
    }

    private int parent(int i) {
        return i/2;
    }

    // Priority Queue operations

    private int getMin() {
        if (getSize() < 2) {
            return 1;
        }
        return heap.get(1);
    }
    private int extractMin() {
        if (getSize() < 2) {
            return -1;
        }
        int min = heap.get(1);
        swap(1, getSize());
        setSize(getSize()-1);
        minHeapify(1);
        return min;
    }

    private void decreaseKey(int i, int key) {
        if (key > heap.get(i)) {
            System.out.println("new key should be less than or equal to existing key");
            return;
        }
        heap.set(i, key);
        while (i > 1 && heap.get(parent(i)) > heap.get(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void insertKey(int key) {
        heap.add(Integer.MAX_VALUE);
        setSize(getSize()+1);
        decreaseKey(getSize(), key);
    }

    private void removeKey(int index) {
        if (index <= 0 || index > getSize()) {
            System.out.println("not a valid index");
            return;
        }
        swap(index, getSize());
        setSize(getSize() - 1);
        minHeapify(index);
    }
}
