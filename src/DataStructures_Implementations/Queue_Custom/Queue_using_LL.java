package DataStructures_Implementations.Queue_Custom;

public class Queue_using_LL<T> implements Queue_CustomInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private final T val;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }

        public T getVal() {
            return this.val;
        }
    }
    // Checks if the Queue is empty
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
    // Checks for the presence of a given element in the Queue
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
    // Adds the provided element to the Queue
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
    // Removes the Front element from the queue
    @Override
    public T remove() {
        if (this.size == 0) return null;
        Node<T> temp = head;
        head = head.next;
        size--;
        return temp.val;
    }
    // Returns the front value
    @Override
    public T peek() {
        if (this.size == 0) return null;
        return head.val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) sb.append(", ");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
