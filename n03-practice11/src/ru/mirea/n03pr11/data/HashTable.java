package ru.mirea.n03pr11.data;

public interface HashTable<K, V> {
    void add(K key, V value);
    void delete(K key);
    HashTableElement<K, V> search(K key);
    void print();
    default int hash(K key, int capacity) {
        return (key.hashCode() * 37) % capacity;
    }
}
