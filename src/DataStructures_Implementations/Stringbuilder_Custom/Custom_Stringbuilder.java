package DataStructures_Implementations.Stringbuilder_Custom;

public class Custom_Stringbuilder implements Stringbuilder_CustomInterface {
    private char[] value;
    private int size;
    private final int DEFAULT_SIZE = 16;
    private final int INCREASE_FACTOR = 2;

    public Custom_Stringbuilder() {
        this.value = new char[DEFAULT_SIZE];
        this.size = 0;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    void resize() {
        char[] temp = new char[this.value.length * INCREASE_FACTOR];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.value[i];
        }
        this.value = temp;
    }

    @Override
    public void append(String str) {
        for (int i = 0; i < str.length(); i++) {
            this.append(str.charAt(i));
        }
    }

    @Override
    public void append(char ch) {
        if (this.size >= this.value.length) {
            resize();
        }
        this.value[this.size++] = ch;
    }

    @Override
    public void insert(int index, String str) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        while (size + str.length() > value.length) {
            resize();
        }
        // shift right
        for (int i = size - 1; i >= index; i--) {
            value[i + str.length()] = value[i];
        }
        // insert
        for (int i = 0; i < str.length(); i++) {
            value[index + i] = str.charAt(i);
        }
        size += str.length();
    }

    @Override
    public void insert(int index, char ch) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= value.length) {
            resize();
        }
        // shift right
        for (int i = size - 1; i >= index; i--) {
            value[i + 1] = value[i];
        }
        value[index] = ch;
        size++;
    }

    @Override
    public void replace(int start, int end, String str) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException();
        }
        // delete old substring
        delete(start, end);
        // insert new string at start
        insert(start, str);
    }

    @Override
    public void replace(int start, int end, char ch) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException();
        }
        delete(start, end);
        insert(start, ch);
    }

    @Override
    public void delete(int start, int end) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException();
        }
        int shift = end - start;
        for (int i = start; i < size - shift; i++) {
            value[i] = value[i + shift];
        }
        size -= shift;
    }

    @Override
    public void deleteCharAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            value[i] = value[i + 1];
        }
        size--;
    }

    @Override
    public void reverse() {
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            char temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
    }

    @Override
    public void clear() {
        size = 0; // just reset
    }

    @Override
    public String toString() {
        return new String(value, 0, size);
    }
}
