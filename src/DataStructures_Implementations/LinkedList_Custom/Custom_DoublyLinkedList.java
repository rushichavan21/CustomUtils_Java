package DataStructures_Implementations.LinkedList_Custom;

/**
 * Custom implementation of a Doubly Linked List data structure.
 * <p>
 * This class allows insertion, deletion, searching, and traversal
 * operations with nodes connected in both forward and backward directions.
 * </p>
 *
 * @param <E> the type of elements stored in the list
 */
public class Custom_DoublyLinkedList<E> implements LinkedList_CustomInterface<E> {
    private Node<E> head;  // Reference to the first node of the list
    private int size;      // Tracks the number of elements in the list

    /**
     * Inner static class representing a node in the doubly linked list.
     *
     * @param <E> the type of element stored in the node
     */
    public static class Node<E> {
        private final E value;
        private Node<E> next;
        private Node<E> prev;

        /**
         * Constructs a new Node with value, next, and previous references.
         */
        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        /**
         * Constructs a new Node with only a value (next and prev set to null).
         */
        public Node(E value) {
            this(value, null, null);
        }

        /** @return the value stored in the node */
        public E getValue() {
            return this.value;
        }
    }

    /** Constructs an empty Doubly Linked List. */
    public Custom_DoublyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds a new node containing {@code data} at the start of the list.
     *
     * @param data element to add
     */
    @Override
    public void addAtStart(E data) {
        Node<E> temp = new Node<>(data);
        if (head != null) {
            temp.next = head;
            head.prev = temp;
        }
        head = temp;
        size++;
    }

    /**
     * Adds a new node containing {@code data} at the end of the list.
     *
     * @param data element to add
     */
    @Override
    public void addAtEnd(E data) {
        Node<E> temp = new Node<>(data);
        if (head == null) {
            head = temp;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = temp;
            temp.prev = current;
        }
        size++;
    }

    /**
     * Removes the first element of the list.
     * <p>If the list is empty, does nothing.</p>
     */
    @Override
    public void removeFirst() {
        if (head == null) return;

        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    /**
     * Removes the last element of the list.
     * <p>If the list is empty, does nothing.</p>
     */
    @Override
    public void removeLast() {
        if (head == null) return;

        if (head.next == null) {
            head = null;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.prev.next = null;
        }
        size--;
    }

    /**
     * Retrieves the element at the given index.
     *
     * @param index position of the element to retrieve (0-based)
     * @return element at the given index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<E> temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.getValue();
    }

    /**
     * Searches for the given data in the list.
     *
     * @param data element to search for
     * @return true if element exists, false otherwise
     */
    @Override
    public boolean search(E data) {
        Node<E> temp = head;
        while (temp != null) {
            if (temp.getValue().equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /** Removes all elements from the list. */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /** Prints the elements of the list in order. */
    @Override
    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * @return the number of elements in the list
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * @return true if the list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns a string representation of the list.
     * Example: [1 <-> 2 <-> 3]
     *
     * @return string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> temp = head;
        while (temp != null) {
            sb.append(temp.getValue());
            if (temp.next != null) {
                sb.append(" <-> ");  // show both links
            }
            temp = temp.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
