package DataStructures_Implementations.Stack_Custom;

import java.util.Arrays;

public class Stack_using_Array<T> implements Stack_CustomInterface<T> {
    private Object[] st;
    private int size = 0;

    public Stack_using_Array() {
        int DEFAULT_SIZE = 10;
        this.st = new Object[DEFAULT_SIZE];
    }
    // Returns the Element at the top of the stack
    @Override
    public T top() {
        if (size == 0) return null;
        return (T)st[size - 1];
    }
   // Adds an element to the stack
    @Override
    public void push(T value) {
        if (size == st.length) {
            resize();
        }
        st[size++] = value;
    }
    // Removes the element from the top of the stack
    @Override
    public T pop() {
        if (size == 0) return null;
        T temp = (T) st[--size];
        st[size] = null; // prevent memory leak
        return temp;
    }
    // Here if the index overflows then the size doubles
    private void resize() {
        Object[] temp = new Object[st.length * 2];
        for (int i = 0; i < st.length; i++) {
            temp[i] = st[i];
        }
        st = temp;
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
        Arrays.fill(st, 0, size, null);
        size = 0;
    }
    // Checks the presence of the given value in the stack
    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (st[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(st, size)) + " size=" + size;
    }
}
