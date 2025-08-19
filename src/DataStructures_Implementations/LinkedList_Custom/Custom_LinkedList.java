package DataStructures_Implementations.LinkedList_Custom;

/**
 * A custom implementation of a singly linked list.
 * This list supports basic operations such as adding elements
 * at the start or end, removing elements, searching, and clearing.
 *
 * @param <T> the type of elements stored in the list
 */
public class Custom_LinkedList<T> implements LinkedList_CustomInterface<T> {

    /** Head node of the linked list */
    private Node head;

    /** Number of elements in the list */
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public Custom_LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inner Node class representing a single element in the list.
     */
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Adds a new element at the start of the linked list.
     *
     * @param data element to be added
     */
    @Override
    public void addAtStart(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Adds a new element at the end of the linked list.
     *
     * @param data element to be added
     */
    @Override
    public void addAtEnd(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; // first element
        } else {
            Node temp = head;
            while (temp.next != null) { // traverse till last node
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    /**
     * Removes the first element of the list.
     */
    @Override
    public void removeFirst() {
        if (head == null) {
            System.out.println("Empty List, nothing to delete");
            return;
        }
        head = head.next;
        size--;
    }

    /**
     * Removes the last element of the list.
     */
    @Override
    public void removeLast() {
        if (head == null) {
            System.out.println("Empty List, nothing to delete");
            return;
        }
        if (head.next == null) {
            head = null; // only one element
        } else {
            Node temp = head;
            while (temp.next.next != null) { // stop at 2nd last node
                temp = temp.next;
            }
            temp.next = null;
        }
        size--;
    }

    /**
     * Retrieves the element at a given index (0-based).
     *
     * @param index the position of the element
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        Node temp = head;
        for (int i = 0; i < index; i++) { // move to index
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Searches for an element in the list.
     *
     * @param data element to search for
     * @return true if element exists, false otherwise
     */
    @Override
    public boolean search(T data) {
        Node temp = head;
        while (temp != null) {
            if ((temp.data == null && data == null) ||
                    (temp.data != null && temp.data.equals(data))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Clears the linked list (removes all elements).
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Prints all elements in the list.
     */
    @Override
    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return list size
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
