package DataStructures_Implementations.HashMap_Custom;

/**
 * A custom interface that defines the contract for a hash-based map (key-value store).
 * <p>
 * Provides basic operations for associating keys with values, retrieving values,
 * checking for key existence, and managing the collection. Implementations are
 * expected to handle hashing and collision resolution.
 * </p>
 *
 * <h2>Expected Features:</h2>
 * <ul>
 *   <li>Unique keys — duplicate keys should overwrite the existing value</li>
 *   <li>Support for null values (depending on implementation)</li>
 *   <li>Efficient average-case operations for insertion, lookup, and deletion</li>
 *   <li>Dynamic resizing when the load factor threshold is reached</li>
 * </ul>
 *
 * <p>This interface is functionally similar to a subset of {@link java.util.Map}.</p>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface HashMap_CustomInterface<K, V> {

    /**
     * Associates the specified value with the specified key in this map.
     * <p>
     * If the map previously contained a mapping for the key, the old value
     * should be replaced by the new value.
     * </p>
     *
     * @param key   the key with which the value is to be associated
     * @param value the value to be associated with the specified key
     * @throws NullPointerException if the key is {@code null} and the implementation
     *                              does not permit {@code null} keys
     */
    void put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or {@code null}
     * if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or {@code null} if
     *         no mapping exists
     */
    V get(K key);

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key the key whose mapping is to be removed
     * @return the previous value associated with the specified key,
     *         or {@code null} if there was no mapping
     */
    V remove(K key);

    /**
     * Returns {@code true} if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence is to be tested
     * @return {@code true} if this map contains a mapping for the specified key,
     *         otherwise {@code false}
     */
    boolean containsKey(K key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of entries in the map
     */
    int size();

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if the map is empty, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Removes all mappings from this map.
     * <p>
     * After this call, {@link #size()} will return {@code 0}.
     * </p>
     */
    void clear();
}
