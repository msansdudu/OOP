package nsu.chebotareva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    private HashTable<String, Number> hashTable;

    @BeforeEach
    public void setup() {
        hashTable = new HashTable<>();
    }

    @Test
    public void mainTest(){
        Main.main(new String[]{""});
        assertTrue(true);
    }

    @Test
    public void testCreateHashTable() {
        assertNotNull(hashTable);
    }

    @Test
    public void testPutAndGetAndUpdate() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
        hashTable.put("two", 2.0);
        assertEquals(2.0, hashTable.get("two"));
        hashTable.update("one", 1.0);
        assertEquals(1.0, hashTable.get("one"));
    }

    @Test
    public void testRemove() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));

        hashTable.remove("one");
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testContainsKeyAndSize() {
        assertEquals(0, hashTable.size());
        hashTable.put("one", 1);
        assertTrue(hashTable.containsKey("one"));
        assertEquals(1, hashTable.size());
        hashTable.put("two", 2);
        assertEquals(2, hashTable.size());
        hashTable.remove("one");
        assertEquals(1, hashTable.size());
        assertFalse(hashTable.containsKey("one"));
    }

    @Test
    public void testIterator() {
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
    public void testConcurrentModificationException() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        Iterator<Map.Entry<String, Number>> iterator = hashTable.iterator();
        hashTable.put("three", 3);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testEquals() {
        HashTable<String, Number> table = new HashTable<>();
        hashTable.put("one", 1);
        table.put("one", 1);
        assertTrue(table.equals(hashTable));
        table.put("two", 2);
        assertFalse(table.equals(hashTable));
    }

    @Test
    public void testToString() {
        hashTable.put("one", 1);
        hashTable.put("two", 2.0);
        String str = hashTable.toString();
        assertTrue(str.contains("one: 1"));
        assertTrue(str.contains("two: 2.0"));
    }
}