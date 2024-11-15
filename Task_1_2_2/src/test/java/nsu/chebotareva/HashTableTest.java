package nsu.chebotareva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    private HashTable<String, Number> hashTable;

    @BeforeEach
    void setup() {
        hashTable = new HashTable<>();
    }

    @Test
    void mainTest(){
        Main.main(new String[]{""});
        assertTrue(true);
    }

    @Test
    void testCreateHashTable() {
        assertNotNull(hashTable);
    }

    @Test
    void testPutAndGetAndUpdate() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
        hashTable.put("two", 2.0);
        assertEquals(2.0, hashTable.get("two"));
        hashTable.update("one", 1.0);
        assertEquals(1.0, hashTable.get("one"));
    }

    @Test
    void testRemove() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
        hashTable.remove("one");
        assertTrue(hashTable.isEmpty());
        boolean res = false;
        try {
            hashTable.remove("one");
        } catch (NoSuchElementException e) {
            res = true;
        }
        assertTrue(res);
    }

    @Test
    void testContainsKeyAndSize() {
        assertEquals(0, hashTable.size());
        hashTable.put("one", 1);
        assertTrue(hashTable.containsKey("one"));
        assertEquals(1, hashTable.size());
        hashTable.put("two", 2);
        assertEquals(2, hashTable.size());
        assertTrue(hashTable.containsKey("two"));
        hashTable.remove("one");
        assertEquals(1, hashTable.size());
        assertFalse(hashTable.containsKey("one"));
    }

    @Test
    void testIterator() {
        hashTable.put("one", 1);
        hashTable.put("two", 2.0);
        Iterator<Map.Entry<String, Number>> iterator = hashTable.iterator();
        assertNotNull(iterator);
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void testConcurrentModificationException() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        Iterator<Map.Entry<String, Number>> iterator = hashTable.iterator();
        hashTable.put("three", 3);
        boolean res = false;
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            res = true;
        }
        assertTrue(res);
    }

    @Test
    void testEquals() {
        HashTable<String, Number> table = new HashTable<>();
        hashTable.put("one", 1);
        table.put("one", 1);
        assertTrue(table.equals(hashTable));
        table.put("two", 2);
        assertFalse(table.equals(hashTable));
    }

    @Test
    void testEntryEquals() {
        Entry<String, Number> e1 = new Entry<>("one", 1);
        Entry<String, Number> e2 = new Entry<>("one", 1);
        assertTrue(e1.equals(e2));
        assertTrue(e1.equals(e1));
        e2.setValue(1.0);
        assertFalse(e1.equals(e2));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        assertFalse(e1.equals(list));
    }

    @Test
    void testToString() {
        hashTable.put("one", 1);
        hashTable.put("two", 2.0);
        String str = hashTable.toString();
        assertTrue(str.contains("one: 1"));
        assertTrue(str.contains("two: 2.0"));
    }
}