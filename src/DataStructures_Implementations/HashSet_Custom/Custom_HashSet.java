package DataStructures_Implementations.HashSet_Custom;

import java.util.Arrays;

public class Custom_HashSet<E> implements HashSet_CustomInterface<E> {
    private final int DEFAULT_SIZE = 16;
    private final float LOAD_FACTOR = 0.70f;
    private int capacity;
    private int size;
    private Node<E>[] set;

    @SuppressWarnings("unchecked")
    public Custom_HashSet() {
        this.capacity = DEFAULT_SIZE;
        this.size = 0;
        this.set = (Node<E>[]) new Node[capacity];
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private int hash(E e) {
        return (e == null) ? 0 : Math.abs(e.hashCode() % capacity);
    }

    private int hash(E e, int newCapacity) {
        return (e == null) ? 0 : Math.abs(e.hashCode() % newCapacity);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldSet = set;
        int oldCapacity = capacity;

        capacity *= 2;
        set = (Node<E>[]) new Node[capacity];

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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        Arrays.fill(this.set, null);
        this.size = 0;
    }

    @Override
    public boolean add(E e) {
        int index = hash(e);
        Node<E> temp = set[index];
        while (temp != null) {
            if ((temp.element == null && e == null) || (temp.element != null && temp.element.equals(e))) {
                return false;
            }
            temp = temp.next;
        }
        Node<E> newNode = new Node<>(e);
        newNode.next = set[index];
        set[index] = newNode;
        size++;
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
        return true;
    }

    @Override
    public boolean remove(E e) {
        int index = hash(e);
        Node<E> temp = set[index];
        Node<E> prev = null;

        while (temp != null) {
            if ((temp.element == null && e == null) || (temp.element != null && temp.element.equals(e))) {
                if (prev == null) {
                    set[index] = temp.next;
                } else {
                    prev.next = temp.next;
                }
                size--;
                return true;
            }
            prev = temp;
            temp = temp.next;
        }
        return false;
    }

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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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
