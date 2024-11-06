package nsu.chebotareva;
import java.util.*;

public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private List<Entry<K, V>>[] table;
    private int size = 0;
    private int modCount = 0;

    public HashTable() {
        table = new List[INITIAL_CAPACITY];
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % table.length);
    }

    public void put(K key, V value) {
        ensureCapacity();
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.getKey(), key)) {
                entry.setValue(value);
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
        modCount++;
    }

    public V get(K key) throws NoSuchElementException{
        int index = hash(key);
        if (table[index] == null) throw new NoSuchElementException();

        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        throw new NoSuchElementException();
    }

    public boolean containsKey(K key) {
        try {
            get(key);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public V remove(K key) throws NoSuchElementException{
        int index = hash(key);
        if (table[index] == null) throw new NoSuchElementException();

        Iterator<Entry<K, V>> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (Objects.equals(entry.getKey(), key)) {
                V value = entry.getValue();
                iterator.remove();
                size--;
                modCount++;
                return value;
            }
        }
        throw new NoSuchElementException();
    }

    public void update(K key, V newValue) throws NoSuchElementException{
        int index = hash(key);
        if (table[index] == null) throw new NoSuchElementException();

        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.getKey(), key)) {
                entry.setValue(newValue);
                modCount++;
                return;
            }
        }
        throw new NoSuchElementException();
    }

    private void ensureCapacity() {
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
    }

    private void resize() {
        List<Entry<K, V>>[] oldTable = table;
        table = new List[oldTable.length * 2];
        size = 0;

        for (List<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            private Iterator<Entry<K, V>> bucketIterator = null;

            @Override
            public boolean hasNext() {
                if (bucketIterator != null && bucketIterator.hasNext()) {
                    return true;
                }

                while (index < table.length) {
                    List<Entry<K, V>> bucket = table[index++];
                    if (bucket != null && !bucket.isEmpty()) {
                        bucketIterator = bucket.iterator();
                        return bucketIterator.hasNext();
                    }
                }
                return false;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bucketIterator.next();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        HashTable<K, V> hashTable = (HashTable<K, V>) o;
        if (size != hashTable.size) return false;
        for (Map.Entry<?, ?> entry : hashTable) {
            if (!hashTable.containsKey((K) entry.getKey())) {
                return false;
            }
            if (this.get((K) entry.getKey()) != hashTable.get((K) entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(table);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\t");
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    sb.append(entry).append(", \n\t");
                }
            }
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("\n}");
        return sb.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}