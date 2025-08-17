package DataStructures_Implementations.Stack_Custom;

public interface Stack_CustomInterface<T> {
    T top();
    void push(T value);
    T pop();
    boolean isEmpty();
    int size();
    void clear();
    boolean contains(T value);
}
