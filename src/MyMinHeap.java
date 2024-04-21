public class MyMinHeap<T extends Comparable<T>> {
    private final MyArrayList<T> heap;

    // Constructs a new, empty object of MyArrayList for min-heap.
    public MyMinHeap() {
        this.heap = new MyArrayList<>();
    }

    // Returns true if the heap is empty, false otherwise.
    public boolean empty() {
        return heap.size() == 0;
    }

    // Returns the number of elements in the heap.
    public int size() {
        return heap.size();
    }

    // Retrieves the minimum element in the heap.
    // Throws an exception if the heap is empty.
    public T getMin() {
        if (empty()) throw new IndexOutOfBoundsException("Heap is empty");
        return heap.get(0);
    }

    // Extracts the minimum element from the heap and removes it.
    // Throws an exception if the heap is empty.
    public T extractMin() {
        if (empty()) throw new IndexOutOfBoundsException("Heap is empty");
        T min = heap.get(0);
        T lastItem = heap.removeReturn(heap.size() - 1);
        if (!empty()) {
            heap.set(0, lastItem);
            heapify(0);
        }
        return min;
    }

    // Inserts a new element into the heap.
    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.size() - 1);
    }

    // Maintains the min-heap property by reorganizing the elements starting from the given index downward.
    private void heapify(int index) {
        int left = leftChildOf(index);
        int right = rightChildOf(index);
        int smallest = index;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Restores the min-heap property by moving the element at the given index upward in the heap.
    private void traverseUp(int index) {
        while (index != 0 && heap.get(parentOf(index)).compareTo(heap.get(index)) > 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    // Helper method that returns the index of the left child of the element at the given index.
    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    // Helper method that returns the index of the right child of the element at the given index.
    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    // Helper method that returns the index of the parent of the element at the given index.
    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    // Helper method that swaps two elements in the heap at the given indices.
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
