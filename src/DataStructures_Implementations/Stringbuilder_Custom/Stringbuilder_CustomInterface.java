package DataStructures_Implementations.Stringbuilder_Custom;

/**
 * A custom interface that mimics some of the functionality of Java's {@link StringBuilder}.
 * Provides methods to manipulate mutable sequences of characters.
 */
public interface Stringbuilder_CustomInterface {

    /**
     * Returns the number of characters currently stored in this StringBuilder.
     *
     * @return the length of the string
     */
    int length();

    /**
     * Checks if the StringBuilder is empty.
     *
     * @return true if length is 0, false otherwise
     */
    boolean isEmpty();

    /**
     * Appends a string at the end of the current content.
     *
     * @param str the string to append
     */
    void append(String str);

    /**
     * Appends a single character at the end of the current content.
     *
     * @param ch the character to append
     */
    void append(char ch);

    /**
     * Inserts a string at the given index.
     *
     * @param index position where the string should be inserted
     * @param str   the string to insert
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    void insert(int index, String str);

    /**
     * Inserts a character at the given index.
     *
     * @param index position where the character should be inserted
     * @param ch    the character to insert
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    void insert(int index, char ch);

    /**
     * Replaces the characters in a substring with a given string.
     *
     * @param start starting index (inclusive)
     * @param end   ending index (exclusive)
     * @param str   replacement string
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    void replace(int start, int end, String str);

    /**
     * Replaces the characters in a substring with a single character.
     *
     * @param start starting index (inclusive)
     * @param end   ending index (exclusive)
     * @param ch    replacement character
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    void replace(int start, int end, char ch);

    /**
     * Deletes a substring between start (inclusive) and end (exclusive).
     *
     * @param start starting index
     * @param end   ending index
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    void delete(int start, int end);

    /**
     * Deletes the character at the given index.
     *
     * @param index the position of the character to delete
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    void deleteCharAt(int index);

    /**
     * Reverses the order of characters in the current string.
     */
    void reverse();

    /**
     * Clears the StringBuilder (removes all characters).
     */
    void clear();
}
