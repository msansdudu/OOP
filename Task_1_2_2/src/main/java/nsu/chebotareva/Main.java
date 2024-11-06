package nsu.chebotareva;

import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        for (Map.Entry<?, ?> entry : hashTable) {
            System.out.println(entry);
        }
    }
}