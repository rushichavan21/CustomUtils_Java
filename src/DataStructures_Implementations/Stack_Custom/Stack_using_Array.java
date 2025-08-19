package DataStructures_Implementations.Stack_Custom;

import java.util.Arrays;

/**
 * Custom Stack implementation using an array as the underlying data structure.
 * <p>
 * This class follows the LIFO (Last-In, First-Out) principle and supports
 * dynamic resizing when the array capacity is exceeded.
 *
 * @param <T> the type of elements stored in the stack
 */
public class Stack_using_Array<T> implements Stack_CustomInterface<T> {

    /** Internal array to store stack elements */
    private Object[] st;

    /** Current number of elements in the stack */
    private int size = 0;

    /** Default initial capacity of the stack */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Constructor initializes the stack with a default size of 10.
     */
    public Stack_using_Array() {
        this.st = new Object[DEFAULT_SIZE];
    }

    /**
     * Retrieves the element at the top of the stack without removing it.
     *
     * @return the top element, or {@code null} if the stack is empty
     */
    @Override
    public T top() {
        if (size == 0) return null;
        return (T) st[size - 1];
    }

    /**
     * Pushes an element onto the stack. If the stack is full,
     * the internal array is resized (doubled in capacity).
     *
     * @param value the element to push
     */
    @Override
    public void push(T value) {
        if (size == st.length) {
            resize();
        }
        st[size++] = value;
    }

    /**
     * Pops (removes) the top element from the stack.
     *
     * @return the popped element, or {@code null} if the stack is empty
     */
    @Override
    public T pop() {
        if (size == 0) return null;
        T temp = (T) st[--size];
        st[size] = null; // Prevent memory leak
        return temp;
    }

    /**
     * Resizes the stack by doubling its capacity when full.
     * This ensures amortized O(1) time complexity for push operations.
     */
    private void resize() {
        Object[] temp = new Object[st.length * 2];
        for (int i = 0; i < st.length; i++) {
            temp[i] = st[i];
        }
        st = temp;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack has no elements, otherwise {@code false}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the number of elements currently in the stack.
     *
     * @return the current size of the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clears all elements from the stack.
     * Memory is set to {@code null} for garbage collection.
     */
    @Override
    public void clear() {
        Arrays.fill(st, 0, size, null);
        size = 0;
    }

    /**
     * Checks whether the stack contains a specific value.
     *
     * @param value the value to check
     * @return {@code true} if the value exists in the stack, otherwise {@code false}
     */
    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (st[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provides a string representation of the stack.
     * Shows elements in array format and the current size.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(st, size)) + " size=" + size;
    }
}
