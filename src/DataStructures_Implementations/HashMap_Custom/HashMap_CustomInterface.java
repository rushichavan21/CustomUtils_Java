package DataStructures_Implementations.HashMap_Custom;

public interface HashMap_CustomInterface<K, V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    int size();
    boolean isEmpty();
    void clear();
}
