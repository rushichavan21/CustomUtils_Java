package DataStructures_Implementations.ArrayList_Custom;

public interface ArrayList_CustomInterface<T>  {
    void add(T value);
    boolean isFull();
    T remove();
    T get(int index);
    int size();
    void set(int index, T value);
    boolean contains(T value);
    void clear();
    void ensureCapacity(int minCapacity);
}
