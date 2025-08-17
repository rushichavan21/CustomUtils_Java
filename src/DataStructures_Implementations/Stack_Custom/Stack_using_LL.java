package DataStructures_Implementations.Stack_Custom;

public class Stack_using_LL<T> implements Stack_CustomInterface<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        private final T val;
        Node<T> next;

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
    // Returns the Element at the top of the stack
    @Override
    public T top() {
        if (head != null) return head.val;
        return null;
    }
    // Adds an element to the stack
    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }
    // Removes the element from the top of the stack
    @Override
    public T pop() {
        if (head == null) return null;
        T temp = head.val;
        head = head.next;
        size--;
        return temp;
    }
    // Checks if the Stack is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    //Returns the size of the Stack
    @Override
    public int size() {
        return this.size;
    }
    // Clears the Stack
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    // Checks the presence of the given value in the stack
    @Override
    public boolean contains(T value) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.val.equals(value)) return true;
            temp = temp.next;
        }
        return false;
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
