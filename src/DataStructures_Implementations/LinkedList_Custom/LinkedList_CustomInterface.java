package DataStructures_Implementations.LinkedList_Custom;

/**
 * A custom generic LinkedList interface that defines the
 * fundamental operations supported by a linked list data structure.
 *
 * @param <T> the type of elements stored in the linked list
 */
public interface LinkedList_CustomInterface<T> {

    /**
     * Adds a new element at the start of the linked list.
     *
     * @param data the element to be added
     */
    void addAtStart(T data);

    /**
     * Adds a new element at the end of the linked list.
     *
     * @param data the element to be added
     */
    void addAtEnd(T data);

    /**
     * Removes the first element of the linked list.
     * If the list is empty, no action is performed.
     */
    void removeFirst();

    /**
     * Removes the last element of the linked list.
     * If the list is empty, no action is performed.
     */
    void removeLast();

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the position of the element (0-based index)
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    T get(int index);

    /**
     * Searches for an element in the linked list.
     *
     * @param data the element to search for
     * @return true if the element exists, false otherwise
     */
    boolean search(T data);

    /**
     * Removes all elements from the linked list.
     * After calling this method, the list will be empty.
     */
    void clear();

    /**
     * Prints all elements of the linked list in sequence.
     * Usually implemented for debugging purposes.
     */
    void printList();

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the current size of the list
     */
    int getSize();

    /**
     * Checks whether the linked list is empty.
     *
     * @return true if the list contains no elements, false otherwise
     */
    boolean isEmpty();
}
