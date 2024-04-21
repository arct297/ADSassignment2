// The object MyStack is stack that is based on MyLinkedList.
public class MyStack<T extends Comparable<T>>{
    // Implementing stack using MyLinkedList.
    private final MyLinkedList<T> stackContainer = new MyLinkedList<>();

    // Constructor of MyStack
    public MyStack(){

    }

    // Returns size of stack.
    public int size(){
        return stackContainer.size();
    }

    // Return boolean if stack is empty, otherwise false.
    public boolean empty(){
        return  stackContainer.size() == 0;
    }

    // Returns value of last element. Does not remove last element.
    // Can throw exception, if stack is empty.
    public T peek(){
        if (empty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return stackContainer.getFirst();
    }

    // Adds element to the top of stack.
    public void push(T element){
        stackContainer.addFirst(element);
    }

    // Removes top element of stack and returns it.
    // Can throw exception, if stack is empty.
    public T pop(){
        if (empty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        T topElement = peek();
        stackContainer.removeFirst();
        return topElement;
    }
}
