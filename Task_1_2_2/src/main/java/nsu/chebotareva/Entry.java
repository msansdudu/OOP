package nsu.chebotareva;

import java.util.Map;
import java.util.Objects;

public class Entry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V prev = this.value;
        this.value = value;
        return prev;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) obj;
        return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
}