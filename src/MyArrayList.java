import java.util.Iterator;


// Generic MyArrayList class allows to work with array.
public class MyArrayList <T extends Comparable<T>> implements MyList<T>{
    private int size = 0; // Contains current size of an array.
    private int capacity = 5; // Contains current capacity of an array.
    private Object[] arr = new Object[capacity]; // Java Array object.

    // Increases capacity of array in 2 times.
    private void increaseCapacity(){
        capacity *= 2;
        Object[] newArr = new Object[capacity]; // New java array object creating.
        for (int i = 0; i < size; i++) { // Old array elements copying to new array.
            newArr[i] = arr[i];
        }
        arr = newArr; // replacing reference to new array.
    }

    // Checks possibility of  index location in the array.
    // Throws exception `IndexOutOfBoundsException` if index is out of bounds.
    private void validateIndex(int index){
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be equal or less than size");
        }
    }

    // Checks is array empty.
    // Throws exception `IndexOutOfBoundsException` if array is empty
    private void checkIsEmpty(){
        if (size == 0) {
            throw new IndexOutOfBoundsException("Array is empty");
        }
    }

    // Adds passed element into end of array.
    @Override
    public void add(T item) {
        if (size == capacity) { // Checking of array filling.
            increaseCapacity();
        }
        arr[size++] = item;
    }


    // Set item value to index position.
    // If this index cannot be in the array throws exception (see more in validateIndex).
    @Override
    public void set(int index, T item) {
        validateIndex(index); // Index validation.
        arr[index] = item; // Item value setting.
    }

    // Adds element in given position (index).
    @Override
    public void add(int index, T item) {
        validateIndex(index - 1); // Index validation: -1 because possible case index = size
        if (index < 0){
            throw new IndexOutOfBoundsException("Index cannot be less than 0.");
        }
        if (size == capacity){ // Capacity increasing if necessary.
            increaseCapacity();
        }
        for (int i = size; i > index; i--) { // This loop shifts values.
            arr[i] = arr[i - 1];
        }
        arr[index] = item; // Setting of new item to given position (index).
        size++; // Array size increment.
    }

    // Adds element to 0 index.
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Adds element to last index.
    @Override
    public void addLast(T item) {
        add(item);
    }

    // Returns value from array at passed index.
    // Throws exception (see validateIndex method).
    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) arr[index];
    }

    // Returns first value from array.
    // Can throw exception if array is empty (see validateIndex method).
    @Override
    public T getFirst() {
        checkIsEmpty();
        return (T) arr[0];
    }

    // Returns last value from array.
    // Can throw exception if array is empty (see validateIndex method).
    @Override
    public T getLast() {
        checkIsEmpty();
        return (T) arr[size - 1];
    }

    // Remove element on passed index position.
    // Can throw exception if array is empty or index is invalid (see validateIndex method).
    @Override
    public void remove(int index) {
        validateIndex(index); // Checks possibility of index location into array.

        for (int i = index; i < size - 1; i++) { // Elements shifting to left after removing.
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--; // Size incrementation.
    }

    public T removeReturn(int index) {
        validateIndex(index); // Checks possibility of index location into array.
        T element = (T) arr[index];
        for (int i = index; i < size - 1; i++) { // Elements shifting to left after removing.
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--; // Size incrementation.
        return element;
    }

    // Remove first element from array.
    // Can throw exception if array is empty (see validateIndex method).
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Remove last element from array.
    // Can throw exception if array is empty (see checkIsEmpty method).
    @Override
    public void removeLast() {
        checkIsEmpty();
        remove(size - 1);
    }


    // Sorts the array by bubble sorting in ASC order.
    @Override
    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++){
                Comparable<T> current = (Comparable<T>) arr[j];
                T next = (T) arr[j + 1];
                if (current.compareTo(next) > 0) {
                    arr[j] = next;
                    arr[j + 1] = current;
                }
            }
        }
    }

    // Returns index of first found passed element in the array. If no such, returns -1.
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == object){
                return i;
            }
        }
        return -1;
    }

    // Returns index of first found (from end) passed element in the array. If no such, returns -1.
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == object){
                return i;
            }
        }
        return -1;
    }

    // Returns boolean result of element existing in the array.
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    // Returns array object.
    @Override
    public Object[] toArray() {
        Object[] toReturnArray = new Object[size]; // New array object creating.
        for (int i = 0; i < size; i++) {
            toReturnArray[i] = arr[i]; // Copying of elements.
        }
        return toReturnArray;
    }

    // Clears array. Sets size to zero.
    @Override
    public void clear() {
        size = 0;
    }

    // Returns size of array.
    @Override
    public int size() {
        return size;
    }

    // Method for array iterating.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("Index must be equal or less than size");
                }
                return (T) arr[currentIndex++];
            }
        };
    }
}


