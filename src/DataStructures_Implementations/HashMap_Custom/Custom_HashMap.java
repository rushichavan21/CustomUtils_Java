package DataStructures_Implementations.HashMap_Custom;

public class Custom_HashMap<K,V> implements HashMap_CustomInterface<K,V>{
private final int DEFAULT_CAPACITY=16;
private final int INCREASE_FACTOR=2;
private  Node<K,V>[] table;
private final float LOAD_FACTOR=0.70f;
private int size;
private int capacity;
public Custom_HashMap(){
    table=new Node[DEFAULT_CAPACITY];
    this.capacity=DEFAULT_CAPACITY;
    size=0;
}

private static class Node<K,V>{
   private  K key;
   private V value;
   private Node <K,V> next;
   public Node(){
       this.key=null;
       this.value=null;
       this.next=null;
   }
   public Node(K key,V value){
       this.key=key;
       this.value=value;
       this.next=null;
   }
   public Node(K key , V value , Node <K,V> next){
       this.key=key;
       this.value=value;
       this.next=next;
   }
   public void setValue(V value){
       this.value=value;
   }
   public K getKey(){
       return this.key;
   }
   public V getValue(){
       return this.value;
   }
}

private int hash(K key){
    if(key==null){return 0;}

    return Math.abs(key.hashCode()%this.capacity);

}
private int hash(K key , int newCapacity){
        if(key==null){return 0;}
        return Math.abs(key.hashCode()%newCapacity);

    }
    private void resize() {
        @SuppressWarnings("unchecked")
        Node<K,V>[] newTable = (Node<K,V>[]) new Node[capacity * INCREASE_FACTOR];

        for (int i = 0; i < capacity; i++) {
            Node<K,V> temp = table[i];
            while (temp != null) {
                Node<K,V> nextNode = temp.next;
                int index = hash(temp.key, capacity * INCREASE_FACTOR);
                temp.next = newTable[index];
                newTable[index] = temp;
                temp = nextNode;
            }
        }

        this.capacity *= INCREASE_FACTOR;
        this.table = newTable;
    }


    @Override
    public void put(K key, V value) {
    int index=hash(key);
    if(table[index]==null){
        Node<K,V>  temp= new Node<>(key,value,null);
        table[index]=temp;
        size++;
        return;
    }
        Node<K,V>  temp= table[index];
    while(temp!=null){
        if( (temp.key==null && key==null) || (temp.key!=null && temp.key.equals(key))){
            temp.setValue(value);
            return;
        }

        temp=temp.next;
    }
    Node<K,V> newNode = new Node<>(key, value, table[index]);
    table[index] = newNode;
    size++;

    if(size>capacity*LOAD_FACTOR){
        resize();
    }
    }

    @Override
    public V get(K key) {
    int index=hash(key);
    if(table[index]==null){
        return null;
    }
    Node<K,V> temp= table[index];
    while(temp!=null){
        if( (temp.key==null && key==null) || (temp.key!=null && temp.key.equals(key))){
            return temp.getValue();
        }
        temp=temp.next;
    }
        return null;
    }

    @Override
    public V remove(K key) {
    int index=hash(key);
    if(table[index]==null){
        return null;
    }
    Node<K,V> node= table[index];
    Node<K,V> prevNode=null;
    while(node!=null){
        if((node.key==null && key==null) || (node.key!=null && node.key.equals(key))){
            if(prevNode==null){
                table[index]=node.next;
            }else{
                prevNode.next=node.next;
            }
            size--;
            return node.getValue();
        }
        prevNode=node;
        node=node.next;

    }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K,V> temp = table[index];
        while (temp != null) {
            if ((temp.key == null && key == null) ||
                    (temp.key != null && temp.key.equals(key))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public void clear() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[capacity];
        this.size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean firstEntry = true;
        for (int i = 0; i < capacity; i++) {
            Node<K,V> temp = table[i];
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
