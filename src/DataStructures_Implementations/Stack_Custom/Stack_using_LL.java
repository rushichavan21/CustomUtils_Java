package DataStructures_Implementations.Stack_Custom;

/**
 * Custom Stack implementation using a singly linked list.
 *
 * <p>This stack supports typical stack operations:
 * push, pop, top, size, clear, contains, and toString.
 *
 * @param <T> the type of elements stored in the stack
 */
public class Stack_using_LL<T> implements Stack_CustomInterface<T> {

    /** Pointer to the top element of the stack */
    private Node<T> head;

    /** Number of elements in the stack */
    private int size;

    /**
     * Inner class representing a node in the linked list.
     */
    private static class Node<T> {
        private final T val;
        private Node<T> next;

        public Node(T val) {
            this.val = val;
            this.next = null;
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }

        T getVal() {
            return this.val;
        }
    }

    /**
     * Retrieves the element at the top of the stack without removing it.
     *
     * @return the top element, or {@code null} if the stack is empty
     */
    @Override
    public T top() {
        return (head != null) ? head.val : null;
    }

    /**
     * Pushes a new element onto the top of the stack.
     *
     * @param value the element to be added
     */
    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the removed element, or {@code null} if the stack is empty
     */
    @Override
    public T pop() {
        if (head == null) return null;
        T temp = head.val;
        head = head.next;
        size--;
        return temp;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack has no elements, otherwise {@code false}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Checks if the stack contains a given value.
     *
     * @param value the element to search for
     * @return {@code true} if the element exists in the stack, otherwise {@code false}
     */
    @Override
    public boolean contains(T value) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.val.equals(value)) return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns a string representation of the stack.
     * The top of the stack is displayed on the left.
     *
     * @return string representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Top -> [");

        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) sb.append(" -> ");
            temp = temp.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
