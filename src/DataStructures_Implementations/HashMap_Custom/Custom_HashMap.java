package DataStructures_Implementations.HashMap_Custom;

/**
 * A custom implementation of a hash-based map (similar to {@link java.util.HashMap}).
 * <p>
 * This class uses an array of buckets, where each bucket is a singly linked list
 * (separate chaining) to handle hash collisions. The internal array dynamically
 * resizes when the load factor threshold is exceeded.
 * </p>
 *
 * <h2>Features:</h2>
 * <ul>
 *   <li>Key-value mapping with unique keys</li>
 *   <li>Collision handling using separate chaining</li>
 *   <li>Automatic resizing when {@code size > capacity * LOAD_FACTOR}</li>
 *   <li>Supports {@code null} keys (stored at index 0)</li>
 * </ul>
 *
 * <p><strong>Time Complexity (Average Case):</strong></p>
 * <ul>
 *   <li>Insertion: O(1)</li>
 *   <li>Lookup: O(1)</li>
 *   <li>Deletion: O(1)</li>
 * </ul>
 *
 * <p><strong>Time Complexity (Worst Case):</strong> O(n) — when all keys hash to the same bucket.</p>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class Custom_HashMap<K, V> implements HashMap_CustomInterface<K, V> {

    /** Default initial capacity of the hash table. */
    private static final int DEFAULT_CAPACITY = 16;

    /** Growth factor used when resizing the hash table. */
    private static final int INCREASE_FACTOR = 2;

    /** Load factor threshold beyond which resizing occurs. */
    private static final float LOAD_FACTOR = 0.70f;

    /** Array of buckets storing linked lists of nodes. */
    private Node<K, V>[] table;

    /** Current number of key-value mappings in the map. */
    private int size;

    /** Current capacity of the hash table. */
    private int capacity;

    /**
     * Constructs an empty map with the default capacity ({@value DEFAULT_CAPACITY}).
     */
    @SuppressWarnings("unchecked")
    public Custom_HashMap() {
        table = new Node[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    /**
     * Node class representing an entry in the hash table.
     */
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        void setValue(V value) {
            this.value = value;
        }

        K getKey() {
            return this.key;
        }

        V getValue() {
            return this.value;
        }
    }

    /**
     * Computes the hash index for a given key.
     *
     * @param key the key
     * @return the bucket index
     */
    private int hash(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % this.capacity);
    }

    /**
     * Computes the hash index for a given key in a new capacity.
     *
     * @param key         the key
     * @param newCapacity the capacity of the new table
     * @return the bucket index
     */
    private int hash(K key, int newCapacity) {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % newCapacity);
    }

    /**
     * Resizes the hash table when load factor threshold is exceeded.
     * <p>
     * All entries are rehashed into the new table of size {@code capacity * INCREASE_FACTOR}.
     * </p>
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[capacity * INCREASE_FACTOR];

        for (int i = 0; i < capacity; i++) {
            Node<K, V> temp = table[i];
            while (temp != null) {
                Node<K, V> nextNode = temp.next;
                int index = hash(temp.key, capacity * INCREASE_FACTOR);
                temp.next = newTable[index];
                newTable[index] = temp;
                temp = nextNode;
            }
        }

        this.capacity *= INCREASE_FACTOR;
        this.table = newTable;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * <p>
     * If the key already exists, its value is updated. If the load factor
     * threshold is exceeded, the table is resized.
     * </p>
     *
     * @param key   the key with which the value is to be associated
     * @param value the value to be associated
     */
    @Override
    public void put(K key, V value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            size++;
        } else {
            Node<K, V> temp = table[index];
            while (temp != null) {
                if ((temp.key == null && key == null) || (temp.key != null && temp.key.equals(key))) {
                    temp.setValue(value);
                    return;
                }
                temp = temp.next;
            }
            table[index] = new Node<>(key, value, table[index]);
            size++;
        }

        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * Returns the value mapped to the specified key, or {@code null} if no mapping exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or {@code null} if none exists
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> temp = table[index];
        while (temp != null) {
            if ((temp.key == null && key == null) || (temp.key != null && temp.key.equals(key))) {
                return temp.getValue();
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * Removes the mapping for the specified key if present.
     *
     * @param key the key to remove
     * @return the previous value associated with the key, or {@code null} if none
     */
    @Override
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prevNode = null;

        while (node != null) {
            if ((node.key == null && key == null) || (node.key != null && node.key.equals(key))) {
                if (prevNode == null) {
                    table[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                return node.getValue();
            }
            prevNode = node;
            node = node.next;
        }
        return null;
    }

    /**
     * Checks if this map contains a mapping for the specified key.
     *
     * @param key the key to check
     * @return {@code true} if the key exists, otherwise {@code false}
     */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> temp = table[index];
        while (temp != null) {
            if ((temp.key == null && key == null) || (temp.key != null && temp.key.equals(key))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the current size of the map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if empty, otherwise {@code false}
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all key-value mappings from this map.
     * <p>
     * After calling this, {@link #size()} will return {@code 0}.
     * </p>
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[capacity];
        this.size = 0;
    }

    /**
     * Returns a string representation of this map in the form:
     * <pre>
     * {key1=value1, key2=value2, ...}
     * </pre>
     *
     * @return a string representing the map entries
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean firstEntry = true;

        for (int i = 0; i < capacity; i++) {
            Node<K, V> temp = table[i];
            while (temp != null) {
                if (!firstEntry) {
                    sb.append(", ");
                }
                sb.append(temp.getKey()).append("=").append(temp.getValue());
                firstEntry = false;
                temp = temp.next;
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
