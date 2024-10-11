import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {
    private ArrayList<T> queue;
    private int maxSize;

    // Constructor that takes an integer as the size of the queue
    public MyQueue(int size) {
        this.maxSize = size;
        this.queue = new ArrayList<>(size);
    }

    // Default constructor that sets the size to a default value (e.g., 10)
    public MyQueue() {
        this(10);
    }

    // Determines if the queue is empty
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Determines if the queue is full
    @Override
    public boolean isFull() {
        return queue.size() == maxSize;
    }

    // Deletes and returns the element at the front of the queue
    @Override
    public T dequeue() throws MyQueueUnderflowException {
        if (isEmpty()) {
            throw new MyQueueUnderflowException();
        }
        return queue.remove(0);
    }

    // Returns the number of elements in the queue
    @Override
    public int size() {
        return queue.size();
    }

    // Adds an element to the end of the queue
    @Override
    public boolean enqueue(T e) throws MyQueueOverflowException {
        if (isFull()) {
            throw new MyQueueOverflowException();
        }
        queue.add(e);
        return true;
    }

    // Returns the elements of the queue in a string from front to back
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : queue) {
            sb.append(element.toString());
        }
        return sb.toString();
    }

    // Returns the string representation of the queue elements separated by a delimiter
    @Override
    public String toString(String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            sb.append(queue.get(i).toString());
            if (i < queue.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    // Fills the queue with elements from an ArrayList
    @Override
    public void fill(ArrayList<T> list) throws MyQueueOverflowException {
        ArrayList<T> tempList = new ArrayList<>(list); // Make a copy of the list
        for (T element : tempList) {
            if (isFull()) {
                throw new MyQueueOverflowException();
            }
            enqueue(element);
        }
    }
}
