package DataStructures_Implementations.ArrayList_Custom;

import java.util.Arrays;

/**
 * A custom implementation of a dynamic array data structure similar to {@link java.util.ArrayList}.
 * <p>
 * This class provides basic functionality of a resizable array that can grow dynamically
 * as elements are added. It supports operations like adding, removing, accessing, updating,
 * and searching for elements. The internal array resizes automatically when full.
 * </p>
 *
 * <h2>Features:</h2>
 * <ul>
 *   <li>Generic type support</li>
 *   <li>Dynamic resizing (1.5x growth strategy)</li>
 *   <li>Provides utility methods like {@code contains()}, {@code clear()}, and {@code ensureCapacity()}</li>
 * </ul>
 *
 * <p><strong>Time Complexity:</strong></p>
 * <ul>
 *   <li>Insertion (amortized): O(1)</li>
 *   <li>Access: O(1)</li>
 *   <li>Search: O(n)</li>
 *   <li>Deletion: O(1)</li>
 * </ul>
 *
 * @param <T> the type of elements stored in this list
 */
public class CustomArrayList<T> implements ArrayList_CustomInterface<T> {

    /**
     * The backing array that stores elements of the list.
     * Default capacity is {@value DEFAULT_CAPACITY}.
     */
    private Object[] data;

    /**
     * The number of elements currently stored in the list.
     */
    private int size = 0;

    /**
     * The initial default capacity of the list.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty list with an initial capacity of {@value DEFAULT_CAPACITY}.
     */
    public CustomArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list.
     * <p>
     * Resizes the internal array if it has reached capacity.
     * </p>
     *
     * @param value the element to be appended
     * @throws NullPointerException if the value is {@code null}
     */
    @Override
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException("Null values are not allowed");
        }
        if (isFull()) {
            resize();
        }
        data[size++] = value;
    }

    /**
     * Increases the capacity of this list when it becomes full.
     * <p>
     * Growth strategy: current capacity + (current capacity / 2) + 1.
     * </p>
     */
    private void resize() {
        Object[] temp = new Object[data.length + data.length / 2 + 1];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * Checks if the list has reached its maximum allocated capacity.
     *
     * @return {@code true} if the list is full, otherwise {@code false}
     */
    @Override
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the element removed from the end of the list
     * @throws IllegalStateException if the list is empty
     */
    @Override
    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        T removed = (T) data[--size];
        data[size] = null; // prevent memory leaks
        return removed;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return (T) data[index];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the current size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param value the element to be stored at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size})
     */
    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        data[index] = value;
    }

    /**
     * Checks if the specified element exists in the list.
     *
     * @param value the element whose presence is to be tested
     * @return {@code true} if the element exists, otherwise {@code false}
     */
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements from the list.
     * <p>
     * After this call, {@link #size()} will return {@code 0}.
     * </p>
     */
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    /**
     * Ensures that the internal array has at least the specified minimum capacity.
     * <p>
     * If the current capacity is less than {@code minCapacity}, the internal array is resized.
     * </p>
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            Object[] temp = new Object[minCapacity];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    /**
     * Returns a string representation of this list.
     * <p>
     * The string includes the current elements and the size of the list.
     * </p>
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        return "CustomArrayList{" + "data=" + Arrays.toString(data) + ", size=" + size + " }";
    }
}
