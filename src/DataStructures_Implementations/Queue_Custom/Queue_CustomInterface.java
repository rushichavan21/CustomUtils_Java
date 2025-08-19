package DataStructures_Implementations.Queue_Custom;

/**
 * A generic interface that defines the operations of a custom Queue data structure.
 * A Queue is a linear data structure that follows the FIFO (First In, First Out) principle.
 *
 * @param <T> the type of elements held in this queue
 */
public interface Queue_CustomInterface<T> {

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue contains no elements, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Checks if the queue contains a specific element.
     *
     * @param element the element to be checked
     * @return {@code true} if the queue contains the element, {@code false} otherwise
     */
    boolean contains(T element);

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added
     */
    void add(T element);

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element that was removed from the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T remove();

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    T peek();
}
