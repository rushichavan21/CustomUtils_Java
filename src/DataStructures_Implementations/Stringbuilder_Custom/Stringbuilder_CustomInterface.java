package DataStructures_Implementations.Stringbuilder_Custom;

public interface Stringbuilder_CustomInterface {
    int length();
    boolean isEmpty();
    void append(String str);
    void append(char ch);
    void insert(int index, String str);
    void insert(int index,char ch);
    void replace(int start, int end, String str);
    void replace(int start, int end, char ch);
    void delete(int start, int end);
    void deleteCharAt(int index);
    void reverse();
    void clear();
}
