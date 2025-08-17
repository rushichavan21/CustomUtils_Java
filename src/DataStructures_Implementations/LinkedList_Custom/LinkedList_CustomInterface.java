package DataStructures_Implementations.LinkedList_Custom;

public interface LinkedList_CustomInterface<T> {
    void addAtStart(T data);
    void addAtEnd(T data);
    void removeFirst();
    void removeLast();
    T get(int index);
    boolean search(T data);
    void clear();
    void printList();
    int getSize();
    boolean isEmpty();
}
