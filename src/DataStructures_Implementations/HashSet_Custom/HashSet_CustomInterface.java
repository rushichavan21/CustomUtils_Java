package DataStructures_Implementations.HashSet_Custom;

public interface HashSet_CustomInterface <E>{
    int size();
    void clear();
    boolean add(E e);
    boolean remove(E e);
    boolean contains(E e);
    boolean isEmpty();
}
