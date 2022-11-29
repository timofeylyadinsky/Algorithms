package lt.timofey.task3;


import java.util.*;


public class HashTableChain{
    private static final int START_CAPACITY = 16;
    private Chain[] table;
    private int capacity;

    public double c = 0.6180339887;

    public HashTableChain() {
        capacity = START_CAPACITY;
        table = new Chain[capacity];
        for (int i = 0; i < capacity; ++i) {
            table[i] = null;
        }
    }

    public HashTableChain(int capacity) {
        table = new Chain[capacity];
        this.capacity = capacity;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = null;
        }
    }

    public void add(int key, int value) {
        final int hash = getHashKey(key);
        if (table[hash] == null) {
            table[hash] = new Chain(key, value);
        } else {
            Chain entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                entry = entry.getNext();
            }
            if (entry.getKey() == key) {
                entry.setValue(value);
            } else {
                entry.setNext(new Chain(key, value));
            }
        }
    }

    public void delete(int key) {
        final int hash = getHashKey(key);
        if (table[hash] != null) {
            Chain prevEntry = null;
            Chain entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                prevEntry = entry;
                entry = entry.getNext();
            }
            if (entry.getKey() == key) {
                if (prevEntry == null) {
                    table[hash] = entry.getNext();
                } else {
                    prevEntry.setNext(entry.getNext());
                }
            }
        }
    }

    public Integer search(int key) {
        final int hash = getHashKey(key);
        if (table[hash] == null)
            return null;
        else {
            Chain entry = table[hash];
            while (entry != null && entry.getKey() != key) {
                entry = entry.getNext();
            }
            if (entry == null) {
                return null;
            } else {
                return entry.getValue();
            }
        }
    }
    public boolean isEmpty() {
        for (int i = 0; i < capacity; ++i) {
            final Chain entry = table[i];
            if (entry != null) {
                return false;
            }
        }
        return true;
    }

    public int getHashKey(int key){
        //final double c = 0.6180339887;//Knut Constant
        final int m = 16;
        return (int)(m*((c*key)%1));
    }

    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            description.append("{  ");
            if (table[i] != null) {
                Chain entry = table[i];
                do {
                    description.append(String.format("%d  ", entry.getValue()));
                    entry = entry.getNext();
                } while (entry != null);
            }
            description.append("} ");
        }
        description.append(']');
        return description.toString();
    }

    public void printNumOfCollision(){
        int count = 0;
        for(int i = 0; i < table.length; i++){
            int num = 1;
            if(table[i]!=null) {
                Chain next = table[i];
                while (next.getNext() != null) {
                    num++;
                    next = next.getNext();
                }
                if (num > 1) count++;
            }
        }
        System.out.println("Collision: " +count);
    }
    public void printMaxOfCollision(){
        int count = 0;
        for(int i = 0; i < table.length; i++){
            int num = 1;
            if(table[i]!=null) {
                Chain next = table[i];
                while (next.getNext() != null) {
                    num++;
                    next = next.getNext();
                }
                if (num > count) count = num;
            }
        }
        System.out.println("Max of Collision: " + count);
    }
}



//public class HashTableChain{
//    final private int size = 100;
//    private HashMap<Integer, ArrayList<Integer>> arrayList = new HashMap<Integer, ArrayList<Integer>>(size);
//
//    public HashTableChain(){
//    }
//    public void addValue(int key, int value){
//        int index = getIndex(key);
//        if(arrayList.get(key)!=null) {
//            arrayList.get(key).add(value);
//        }else{
//            arrayList.put(key, (new ArrayList<Integer>(value)));
//        }
//    }
//
//    public ArrayList<Integer> getValue(int key){
//        int index = getIndex(key);
//        return arrayList.get(index);
//    }
//
//    public void deleteKey(int key){
//        int index = getIndex(key);
//        arrayList.remove(key);
//    }
//
//    public int getIndex(int key){
//        final double c = 0.6180339887;//Knut Constant
//        final int m = 13;
//        return m*((int)(c*key)%1)*size;
//    }
//}
