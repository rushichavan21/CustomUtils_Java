package DataStructures_Implementations.ArrayList_Custom;

/**
 * A custom interface that defines the contract for a dynamic array implementation.
 * <p>
 * Provides essential operations for a list-like data structure, including
 * adding, removing, accessing, updating, checking, and managing capacity.
 * </p>
 *
 * <h2>Expected Implementations:</h2>
 * Classes implementing this interface should provide:
 * <ul>
 *   <li>Dynamic resizing when the array is full</li>
 *   <li>Index validation for access and modification methods</li>
 *   <li>Support for clearing and ensuring minimum capacity</li>
 * </ul>
 *
 * <p>This interface is functionally similar to a subset of {@link java.util.List}.</p>
 *
 * @param <T> the type of elements stored in the implementing list
 */
public interface ArrayList_CustomInterface<T> {

    /**
     * Appends the specified element to the end of the list.
     *
     * @param value the element to be added
     * @throws NullPointerException if the specified value is {@code null}
     */
    void add(T value);

    /**
     * Checks if the list has reached its maximum current capacity.
     *
     * @return {@code true} if the list is full, otherwise {@code false}
     */
    boolean isFull();

    /**
     * Removes and returns the last element in the list.
     *
     * @return the element removed from the list
     * @throws IllegalStateException if the list is empty
     */
    T remove();

    /**
     * Retrieves the element at the specified index.
     *
     * @param index the index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    T get(int index);

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the current size of the list
     */
    int size();

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param value the new element to be stored
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    void set(int index, T value);

    /**
     * Checks if the specified element exists in the list.
     *
     * @param value the element to search for
     * @return {@code true} if the list contains the specified element, otherwise {@code false}
     */
    boolean contains(T value);

    /**
     * Removes all elements from the list.
     * <p>
     * After calling this method, {@link #size()} will return {@code 0}.
     * </p>
     */
    void clear();

    /**
     * Ensures that the list has at least the specified minimum capacity.
     * <p>
     * If the current capacity is less than {@code minCapacity}, the internal
     * array should be resized.
     * </p>
     *
     * @param minCapacity the desired minimum capacity
     */
    void ensureCapacity(int minCapacity);
}
