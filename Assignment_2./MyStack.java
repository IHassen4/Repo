import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
    private ArrayList<T> stack;
    private int maxSize;
    
    // Constructor that takes an integer as the size of the stack
    public MyStack(int size) {
        this.maxSize = size;
        this.stack = new ArrayList<>(size);
    }

    // Default constructor that sets the size to a default value (e.g., 10)
    public MyStack() {
        this(10);
    }

    // Determines if the stack is empty
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Determines if the stack is full
    @Override
    public boolean isFull() {
        return stack.size() == maxSize;
    }

    // Deletes and returns the element at the top of the stack
    @Override
    public T pop() throws MyStackUnderflowException {
        if (isEmpty()) {
            throw new MyStackUnderflowException();
        }
        return stack.remove(stack.size() - 1);
    }

    // Returns the element at the top of the stack without removing it
    @Override
    public T top() throws MyStackUnderflowException {
        if (isEmpty()) {
            throw new MyStackUnderflowException();
        }
        return stack.get(stack.size() - 1);
    }

    // Number of elements in the stack
    @Override
    public int size() {
        return stack.size();
    }

    // Adds an element to the top of the stack
    @Override
    public boolean push(T e) throws MyStackOverflowException {
        if (isFull()) {
            throw new MyStackOverflowException();
        }
        stack.add(e);
        return true;
    }

    // Returns the elements of the stack in a string from bottom to top
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : stack) {
            sb.append(element.toString());
        }
        return sb.toString();
    }

    // Returns the string representation of the stack elements separated by a delimiter
    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i).toString());
            if (i < stack.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    // Fills the stack with elements from an ArrayList
    @Override
    public void fill(ArrayList<T> list) throws MyStackOverflowException {
        ArrayList<T> tempList = new ArrayList<>(list); // Make a copy of the list
        for (T element : tempList) {
            if (isFull()) {
                throw new MyStackOverflowException();
            }
            push(element);
        }
    }
}
