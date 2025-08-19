package DataStructures_Implementations.Stack_Custom;

/**
 * Custom Stack Interface that defines the contract for stack operations.
 *
 * @param <T> the type of elements stored in the stack
 */
public interface Stack_CustomInterface<T> {

    /**
     * Retrieves the element at the top of the stack without removing it.
     *
     * @return the top element of the stack, or {@code null} if the stack is empty
     */
    T top();

    /**
     * Pushes (inserts) an element onto the top of the stack.
     *
     * @param value the element to be added
     */
    void push(T value);

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the removed top element, or {@code null} if the stack is empty
     */
    T pop();

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack has no elements, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the size of the stack
     */
    int size();

    /**
     * Removes all elements from the stack, making it empty.
     */
    void clear();

    /**
     * Checks if the stack contains a given element.
     *
     * @param value the element to search for
     * @return {@code true} if the element exists in the stack, otherwise {@code false}
     */
    boolean contains(T value);
}
