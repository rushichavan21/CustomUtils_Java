package DataStructures_Implementations.Stringbuilder_Custom;

/**
 * Custom implementation of a StringBuilder-like class.
 *
 * This class provides basic mutable string operations such as append,
 * insert, delete, replace, reverse, and clear. Internally, it manages
 * a dynamic character array that grows as needed.
 *
 * Features:
 * - Efficient resizing with a growth factor.
 * - Supports insertion and deletion at arbitrary positions.
 * - Provides a toString() method for easy conversion to immutable String.
 */
public class Custom_Stringbuilder implements Stringbuilder_CustomInterface {
    private char[] value;               // Internal character array buffer
    private int size;                   // Current number of characters stored
    private final int DEFAULT_SIZE = 16; // Initial buffer size
    private final int INCREASE_FACTOR = 2; // Factor for buffer growth

    /**
     * Default constructor initializes with default capacity.
     */
    public Custom_Stringbuilder() {
        this.value = new char[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Returns the length of the current string.
     */
    @Override
    public int length() {
        return this.size;
    }

    /**
     * Checks if the string builder is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Doubles the size of the buffer when full.
     */
    private void resize() {
        char[] temp = new char[this.value.length * INCREASE_FACTOR];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.value[i];
        }
        this.value = temp;
    }

    /**
     * Appends a string to the current string builder.
     */
    @Override
    public void append(String str) {
        for (int i = 0; i < str.length(); i++) {
            this.append(str.charAt(i)); // Reuse append(char)
        }
    }

    /**
     * Appends a single character.
     */
    @Override
    public void append(char ch) {
        if (this.size >= this.value.length) {
            resize();
        }
        this.value[this.size++] = ch;
    }

    /**
     * Inserts a string at the given index.
     */
    @Override
    public void insert(int index, String str) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        while (size + str.length() > value.length) {
            resize();
        }

        // Shift characters to the right
        for (int i = size - 1; i >= index; i--) {
            value[i + str.length()] = value[i];
        }

        // Insert new string
        for (int i = 0; i < str.length(); i++) {
            value[index + i] = str.charAt(i);
        }
        size += str.length();
    }

    /**
     * Inserts a single character at the given index.
     */
    @Override
    public void insert(int index, char ch) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size >= value.length) {
            resize();
        }

        // Shift characters
        for (int i = size - 1; i >= index; i--) {
            value[i + 1] = value[i];
        }
        value[index] = ch;
        size++;
    }

    /**
     * Replaces characters in the range [start, end) with a given string.
     */
    @Override
    public void replace(int start, int end, String str) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException("Invalid range");
        }
        delete(start, end);   // Remove old chars
        insert(start, str);   // Insert new chars
    }

    /**
     * Replaces characters in the range [start, end) with a single character.
     */
    @Override
    public void replace(int start, int end, char ch) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException("Invalid range");
        }
        delete(start, end);   // Remove old chars
        insert(start, ch);    // Insert new char
    }

    /**
     * Deletes characters in the range [start, end).
     */
    @Override
    public void delete(int start, int end) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException("Invalid range");
        }
        int shift = end - start;

        // Shift characters to the left
        for (int i = start; i < size - shift; i++) {
            value[i] = value[i + shift];
        }
        size -= shift;
    }

    /**
     * Deletes the character at a given index.
     */
    @Override
    public void deleteCharAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            value[i] = value[i + 1];
        }
        size--;
    }

    /**
     * Reverses the entire character sequence.
     */
    @Override
    public void reverse() {
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            char temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
    }

    /**
     * Clears the string builder (resets to empty).
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Returns the string representation.
     */
    @Override
    public String toString() {
        return new String(value, 0, size);
    }
}
