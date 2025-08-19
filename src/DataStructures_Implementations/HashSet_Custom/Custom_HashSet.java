package DataStructures_Implementations.HashSet_Custom;

import java.util.Arrays;

/**
 * A custom implementation of HashSet using separate chaining for collision handling.
 *
 * @param <E> the type of elements maintained by this set
 */
public class Custom_HashSet<E> implements HashSet_CustomInterface<E> {

    /** Default initial capacity of the HashSet */
    private final int DEFAULT_SIZE = 16;

    /** Load factor threshold for resizing */
    private final float LOAD_FACTOR = 0.70f;

    /** Current capacity of the backing array */
    private int capacity;

    /** Number of elements in the set */
    private int size;

    /** Backing array of linked list buckets */
    private Node<E>[] set;

    /**
     * Node class used for chaining in buckets
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * Constructs an empty Custom_HashSet with default capacity.
     */
    @SuppressWarnings("unchecked")
    public Custom_HashSet() {
        this.capacity = DEFAULT_SIZE;
        this.size = 0;
        this.set = (Node<E>[]) new Node[capacity];
    }

    /**
     * Computes hash index for an element using current capacity.
     */
    private int hash(E e) {
        return (e == null) ? 0 : Math.abs(e.hashCode() % capacity);
    }

    /**
     * Computes hash index for an element with a given capacity.
     */
    private int hash(E e, int newCapacity) {
        return (e == null) ? 0 : Math.abs(e.hashCode() % newCapacity);
    }

    /**
     * Resizes the backing array when load factor threshold is crossed.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldSet = set;
        int oldCapacity = capacity;

        // Double the capacity
        capacity *= 2;
        set = (Node<E>[]) new Node[capacity];

        // Rehash all existing elements
        for (int i = 0; i < oldCapacity; i++) {
            Node<E> node = oldSet[i];
            while (node != null) {
                Node<E> next = node.next;
                int newIndex = hash(node.element, capacity);
                node.next = set[newIndex];
                set[newIndex] = node;
                node = next;
            }
        }
    }

    /**
     * Returns the number of elements in the set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes all elements from the set.
     */
    @Override
    public void clear() {
        Arrays.fill(this.set, null);
        this.size = 0;
    }

    /**
     * Adds an element to the set if it is not already present.
     *
     * @param e the element to add
     * @return true if the element was added, false if it already exists
     */
    @Override
    public boolean add(E e) {
        int index = hash(e);
        Node<E> temp = set[index];

        // Check if element already exists
        while (temp != null) {
            if ((temp.element == null && e == null) || (temp.element != null && temp.element.equals(e))) {
                return false; // Duplicate
            }
            temp = temp.next;
        }

        // Insert new node at head of chain
        Node<E> newNode = new Node<>(e);
        newNode.next = set[index];
        set[index] = newNode;
        size++;

        // Resize if load factor exceeded
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }

        return true;
    }

    /**
     * Removes the specified element from the set if present.
     *
     * @param e the element to remove
     * @return true if the element was removed, false otherwise
     */
    @Override
    public boolean remove(E e) {
        int index = hash(e);
        Node<E> temp = set[index];
        Node<E> prev = null;

        while (temp != null) {
            if ((temp.element == null && e == null) || (temp.element != null && temp.element.equals(e))) {
                if (prev == null) {
                    set[index] = temp.next; // Remove head
                } else {
                    prev.next = temp.next; // Bypass node
                }
                size--;
                return true;
            }
            prev = temp;
            temp = temp.next;
        }

        return false;
    }

    /**
     * Checks if the set contains the specified element.
     *
     * @param e the element to check
     * @return true if the element exists, false otherwise
     */
    @Override
    public boolean contains(E e) {
        int index = hash(e);
        Node<E> temp = set[index];

        while (temp != null) {
            if ((temp.element == null && e == null) || (temp.element != null && temp.element.equals(e))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns true if the set is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the set.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;

        for (Node<E> bucket : set) {
            Node<E> temp = bucket;
            while (temp != null) {
                if (!first) sb.append(", ");
                sb.append(temp.element);
                first = false;
                temp = temp.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
