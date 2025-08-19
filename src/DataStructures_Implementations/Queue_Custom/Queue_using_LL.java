package DataStructures_Implementations.Queue_Custom;

/**
 * A custom implementation of a Queue using a Linked List.
 * This queue follows the FIFO (First-In-First-Out) principle.
 *
 * @param <T> the type of elements stored in this queue
 */
public class Queue_using_LL<T> implements Queue_CustomInterface<T> {

    /** The head (front) of the queue */
    private Node<T> head;

    /** The tail (rear) of the queue */
    private Node<T> tail;

    /** Current size of the queue */
    private int size;

    /**
     * A static nested Node class to represent
     * each element of the linked list queue.
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

        public T getVal() {
            return this.val;
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks whether the queue contains a given element.
     *
     * @param element the element to check for
     * @return true if element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> temp = this.head;
        while (temp != null) {
            if (temp.getVal().equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Adds an element at the end (rear) of the queue.
     *
     * @param element the element to add
     */
    @Override
    public void add(T element) {
        Node<T> temp = new Node<>(element, null);
        if (this.size == 0) {
            this.head = temp;
            this.tail = temp;
        } else {
            tail.next = temp;
            tail = tail.next;
        }
        size++;
    }

    /**
     * Removes and returns the element from the front of the queue.
     *
     * @return the removed element, or null if queue is empty
     */
    @Override
    public T remove() {
        if (this.size == 0) {
            return null; // Could throw NoSuchElementException, but kept consistent with your version
        }
        Node<T> temp = head;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null; // Clean up tail reference if queue is now empty
        }
        return temp.val;
    }

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the front element, or null if queue is empty
     */
    @Override
    public T peek() {
        if (this.size == 0) {
            return null;
        }
        return head.val;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return elements in FIFO order as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) {
                sb.append(", ");
            }
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
