// The object MyQueue is queue that is based on MyLinkedList.
public class MyQueue<T extends Comparable<T>> {
    // Implementing queue using MyLinkedList.
    private final MyLinkedList<T> queueContainer = new MyLinkedList<T>();

    // Constructor of MyQueue does not require something, so it is empty.
    public MyQueue() {
    }

    // Returns size of stack.
    public boolean isEmpty() {
        return queueContainer.size() == 0;
    }

    // Peek:
    public T peek() {
        if (isEmpty()) {
            return null; // Returns null when queue is empty
        }
        return queueContainer.getFirst();
    }

    public T element() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty"); // Throws an exception when queue is empty
        }
        return queueContainer.getFirst();
    }

    // Enqueue:
    public boolean offer(T element) {
        try {
            queueContainer.addLast(element); // Try to add element to the end of the queue.
            return true;
        } catch (Exception e) {
            return false; // Returns false if it fails to insert.
        }
    }

    public void add(T element) {
        queueContainer.addLast(element); // Adds element to the end of the queue.
        // If addLast somehow fails, it should throw an exception inherently.
    }

    // Dequeue:
    public T poll() {
        if (isEmpty()) {
            return null; // Returns null when queue is empty.
        }
        T firstElement = queueContainer.getFirst();
        queueContainer.removeFirst();
        return firstElement;
    }

    public T remove() {
        T firstElement = element(); // Will throw exception if queue is empty.
        queueContainer.removeFirst();
        return firstElement;
    }
}
