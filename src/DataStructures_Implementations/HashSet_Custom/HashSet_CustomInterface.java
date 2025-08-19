package DataStructures_Implementations.HashSet_Custom;

/**
 * A custom HashSet interface that defines the basic operations
 * for a Set data structure.
 *
 * @param <E> the type of elements maintained by this set
 */
public interface HashSet_CustomInterface<E> {

    /**
     * Returns the number of elements in this set.
     *
     * @return the size of the set
     */
    int size();

    /**
     * Removes all elements from the set.
     */
    void clear();

    /**
     * Adds the specified element to the set if it is not already present.
     *
     * @param e element to be added
     * @return true if the element was added, false if it was already present
     */
    boolean add(E e);

    /**
     * Removes the specified element from the set if it is present.
     *
     * @param e element to be removed
     * @return true if the element was removed, false if it was not found
     */
    boolean remove(E e);

    /**
     * Returns true if this set contains the specified element.
     *
     * @param e element whose presence is to be tested
     * @return true if the element is present, false otherwise
     */
    boolean contains(E e);

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if the set is empty, false otherwise
     */
    boolean isEmpty();
}
