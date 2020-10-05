package ru.mirea.n03pr11.data;

public class HashTableElement<K, V> {
    private K key;
    private V value;
    final private boolean deleted;

    public HashTableElement(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public HashTableElement() {
        this.deleted = true;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return !isDeleted()? String.format("[key: %s, value: %s]", key, value) : "deleted";
    }
}
