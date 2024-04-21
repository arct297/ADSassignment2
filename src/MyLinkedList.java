import java.util.Iterator;

// This class implements linked list object.
public class MyLinkedList <T extends Comparable<T>> implements MyList<T>{
    MyNode<T> head; // Reference to head element.
    MyNode<T> tail; // Reference to last element.
    private int size = 0; // Contains size of list.

    public MyLinkedList(){
        // Nothing because head, tail are null by default.
    }

    // This class is pattern for Node object, that contains data and reference to next element.
    private static class MyNode<E> {
        E data; // Contains value of element.
        MyNode<E> next; // References to next element.
        // Constructor of my node:
        public MyNode(E data) {
            this.data = data; // Sets value of element to data.
            this.next = null;
        }

    }
    // add method adds element to the end.
    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<T>(item);
        if (head == null) { // Checking is list empty.
            head = tail = newNode; // If so, adding first element, that is head and tail.
        } else {
            tail.next = newNode; // Current tail now references to new node.
            tail = newNode; // Tail changing.
        }
        size++;
    }

    // Method "set" sets value to passed index.
    // Can throw exception IndexOutOfBoundsException if list is empty.
    @Override
    public void set(int index, T item) {
        if (index >= size) { // Checking is list empty.
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        int i = 0;
        MyNode<T> current = head;
        while (i < index){ // Finding node of given index.
            current = current.next;
            i++;
        }
        current.data = item; // New value setting.
    }

    // Adds index to passed index.
    // Can throw exception IndexOutOfBoundsException if index is invalid.
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        MyNode<T> newNode = new MyNode<>(item); // New node creation.
        if (index == 0) { // In case add first shifting head.
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            MyNode<T> current = head;
            for (int i = 0; i < index - 1; i++) { // Finding necessary element on the index.
                current = current.next;
            }
            newNode.next = current.next; // Shifting.
            current.next = newNode;
            if (newNode.next == null) tail = newNode; // Tail case checking.
        }
        size++;
    }

    // Adds node to 0 position, using add(index, item) method.
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Adds node to last position, using add(index, item) method.
    @Override
    public void addLast(T item) {
        add(item);
    }

    // Return value of passed index in linked list.
    @Override
    public T get(int index) {
        if (size == 0){
            throw new IndexOutOfBoundsException("List is empty"); // Impossible to get value of element in empty list.
        }
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds"); // Index does not exist.
        }

        if (index == 0){
            return head.data; // for 0 position returns value of head.data.
        }
        if (index == size - 1){
            return tail.data; // for last index returns value of tail.data
        }
        MyNode<T> current = head; // Start traversal from the head.
        for (int i = 0; i < index; i++) { // Traverse to the node at the specified index.
            current = current.next;
        }
        return current.data; // Data retrieving.
    }

    // Returns value of first element in list.
    // Throws exception, if list is empty.
    @Override
    public T getFirst() {
        if (size == 0){ // Checks is list empty
            throw new IndexOutOfBoundsException("List is empty");
        }
        return head.data; // head.data is value of first element.
    }

    // Returns value of last element in list.
    // Throws exception, if list is empty.
    @Override
    public T getLast() {
        if (size == 0){ // Checks is list empty
            throw new IndexOutOfBoundsException("List is empty");
        }
        return tail.data; // tail.data is value of last element.
    }

    // Removes element from the list by passed index.
    @Override
    public void remove(int index) {
        if (size == 0){ // Checks is list empty.
            throw new IndexOutOfBoundsException("List is empty");
        }
        if (index >= size || index < 0) { // Checks validity of index.
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0){ // For zero index updating shifting head to left.
            head = head.next;
            if (size == 1) { // tail setting to null in case if size is 1 (removing will lead to empty linked list).
                tail = null;
            }
        }
        MyNode<T> current = head; // Finding of node on index position.
        for (int i = 0; i < index - 1; i++){
            current = current.next;
        }
        current.next = current.next.next; // Replacing of next of current (garbaging of node on passed index).
        if (current.next == null) tail = current;
        size--;
    }

    // Removing of first node, using remove(index).
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removing of last node, using remove(index).
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    // Method sorts linked list
    @Override
    public void sort() {

    }


    // Returns first position of object in list, if such, otherwise -1.
    @Override
    public int indexOf(Object object) {
        MyNode<T> current = head;
        for (int i = 0; i < size; i++){ // List iteration...
            if (current.data.equals(object)) { // If data is equal to object, returns position.
                return i;
            }
            current = current.next;
        }
        return -1; // If whole list was iterated, but object was not found, then -1.
    }

    // Returns last position of object in list, if such, otherwise -1.
    @Override
    public int lastIndexOf(Object object) {
        int lastFoundIndex = -1; // Base case - object is not found.
        MyNode<T> current = head;
        for (int i = 0; i < size; i++){ // List iterating...
            if (current.data.equals(object)) {
                lastFoundIndex = i; // If item is found, updates last defined index.
            }
            current = current.next;
        }
        return lastFoundIndex;
    }

    // Using indexOf returns true if object is in list, otherwise false.
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    // Returns data of each node in array.
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size]; // Array object creating.
        if (size == 0){
            return arr; // Returns empty array if linked list is empty.
        }
        MyNode<T> current = head;
        for (int i = 0; i < size && current != null; i++){ // Copying data values of each node to array.
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    // Clears linked list.
    @Override
    public void clear() {
        head = tail = null; // Garbaging of all nodes.
        size = 0; // Now size is zero.
    }

    // Returns size of linked list.
    @Override
    public int size() {
        return size;
    }

    // Method that implements possibility of linked list iterating.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("Index must be equal or less than size");
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

}
