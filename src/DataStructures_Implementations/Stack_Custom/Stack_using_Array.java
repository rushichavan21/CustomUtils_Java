package DataStructures_Implementations.Stack_Custom;

import java.util.Arrays;

public class Stack_using_Array<T> implements Stack_CustomInterface<T> {
    private Object[] st;
    private int size = 0;

    public Stack_using_Array() {
        int DEFAULT_SIZE = 10;
        this.st = new Object[DEFAULT_SIZE];
    }

    @Override
    public T top() {
        if (size == 0) return null;
        return (T)st[size - 1];
    }

    @Override
    public T peek() {
        return top();
    }

    @Override
    public void push(T value) {
        if (size == st.length) {
            resize();
        }
        st[size++] = value;
    }

    @Override
    public T pop() {
        if (size == 0) return null;
        T temp = (T) st[--size];
        st[size] = null; // prevent memory leak
        return temp;
    }

    private void resize() {
        Object[] temp = new Object[st.length * 2];
        for (int i = 0; i < st.length; i++) {
            temp[i] = st[i];
        }
        st = temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        Arrays.fill(st, 0, size, null);
        size = 0;
    }

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

    public static void main(String[] args) {
        Stack_using_Array<Integer> stack = new Stack_using_Array<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }
}
