package DataStructures_Implementations.ArrayList_Custom;

import java.util.Arrays;

public class CustomArrayList<T> implements ArrayList_CustomInterface<T> {
    private Object[] data;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // constructor
    public CustomArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    // Adds the value to the CustomArrayList
    @Override
    public void add(T value) {
        if (isFull()) {
            resize();
        }
        data[size++] = value;
    }

    // Doubles capacity when full
    private void resize() {
        Object[] temp = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        T removed = (T) data[--size];
        data[size] = null; // avoid memory leak
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        return (T) data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        data[index] = value;
    }

    // Returns true if value exists in the list
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    // Removes all elements
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    // Ensures capacity is at least minCapacity
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            Object[] temp = new Object[minCapacity];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }


    @Override
    public String toString() { return "CustomArrayList{" + "data"+ Arrays.toString(data) + ", size=" +size+" } "; }

    // ---------- Testing ----------
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("List: " + list); // [10, 20, 30]

        System.out.println("Contains 20? " + list.contains(20)); // true
        System.out.println("Contains 99? " + list.contains(99)); // false

        list.set(1, 200);
        System.out.println("After set: " + list); // [10, 200, 30]

        list.remove();
        System.out.println("After remove: " + list); // [10, 200]

        list.ensureCapacity(50);
        System.out.println("Capacity increased (not directly visible)");

        list.clear();
        System.out.println("After clear: " + list); // []


    }
}
