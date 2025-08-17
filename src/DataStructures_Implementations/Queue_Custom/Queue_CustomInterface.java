package DataStructures_Implementations.Queue_Custom;

public interface Queue_CustomInterface<T> {
    boolean isEmpty();
    boolean contains(T element);
    void add(T element);
    T remove();
    T peek();
}
