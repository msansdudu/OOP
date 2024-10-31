package nsu.chebotareva;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.update("one", 1.0);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);
        hashTable.put("five", 5);
        hashTable.put("six", 6);
        hashTable.put("seven", 7);
        hashTable.put("eight", 8);
        hashTable.put("nine", 9);
        hashTable.put("ten", 10);
        hashTable.put("eleven", 11);
        hashTable.put("twelve", 12);
        hashTable.put("one", 1);
        hashTable.update("one", 1.0);
        hashTable.put("thirteen", 13);
        hashTable.put("fourteen", 14);
        hashTable.put("fifteen", 15);
        hashTable.put("sixteen", 16);
        hashTable.put("seventeen", 17);
        hashTable.put("eighteen", 18);
        hashTable.put("nineteen", 19);
        hashTable.put("twenty", 20);
        hashTable.put("twenty one", 21);
        hashTable.put("twenty two", 22);
        hashTable.put("twenty three", 23);
        System.out.println(hashTable);
    }
}