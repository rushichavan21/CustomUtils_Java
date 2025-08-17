package DataStructures_Implementations.LinkedList_Custom;

import java.util.LinkedList;

public class Custom_LinkedList<T> implements LinkedList_CustomInterface<T> {
    private Node head;
    private int size;

    // Constructor
    public Custom_LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Node inner class
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Add element at the start
    @Override
    public void addAtStart(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add element at the end
    @Override
    public void addAtEnd(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    // Remove first element
    @Override
    public void removeFirst() {
        if (head == null) {
            System.out.println("Empty List, nothing to delete");
            return;
        }
        head = head.next;
        size--;
    }

    // Remove last element
    @Override
    public void removeLast() {
        if (head == null) {
            System.out.println("Empty List, nothing to delete");
            return;
        }
        if (head.next == null) { // only 1 node
            head = null;
        } else {
            Node temp = head;
            while (temp.next.next != null) { // stop at second-last node
                temp = temp.next;
            }
            temp.next = null;
        }
        size--;
    }

    // Get element at given index (0-based)
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    // Search for an element
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

    // Clear the list
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    // Print all elements
    @Override
    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Get size of list
    @Override
    public int getSize() {
        return size;
    }

    // Check if list is empty
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
