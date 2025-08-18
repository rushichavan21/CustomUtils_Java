package DataStructures_Implementations.LinkedList_Custom;

public class Custom_DoublyLinkedList<E> implements LinkedList_CustomInterface<E> {
    private Node<E> head;
    private int size;

    public static class Node<E> {
        private final E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public Node(E value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public E getValue() {
            return this.value;
        }
    }

    public Custom_DoublyLinkedList() {
        head = null;
        size = 0;
    }

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

    @Override
    public void removeFirst() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        if (head == null) {
            return;
        }
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

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

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
