package ru.mirea.n03pr11.data;

/**
 * Реализация хеш-таблицы.
 * Разрешение коллизий методом линейного пробирования (с открытой адресацией, увеличением на 1)
 */
public class HashTableLinearProbing<K, V> implements HashTable<K, V> {
    final private static int INIT_CAPACITY = 16;
    private int capacity;
    private int size;
    private HashTableElement<K, V>[] array;

    public HashTableLinearProbing() {
        capacity = INIT_CAPACITY;
        size = 0;
        array = new HashTableElement[capacity];
        for (int i = 0; i < capacity; i++)
            array[i] = null;
    }

    /**
     * Добавление элемента в таблицу
     */
    @Override
    public void add(K key, V value) {
        if (key == null || value == null)
            throw new IllegalArgumentException("Не задан(о) ключ/значение");

        if ((size * 1.0 / capacity) * 100 > 50)
            doubleArraySize();

        int hash = hash(key, capacity);
        while (array[hash] != null) {
            if (array[hash].isDeleted()) {
                array[hash] = new HashTableElement<>(key, value);
                size++;
                return;
            }
            if (array[hash].getKey().equals(key)) {
                array[hash].setValue(value);
                return;
            }
            if (++hash == capacity)
                hash = 0;
        }
        array[hash] = new HashTableElement<>(key, value);
        size++;
    }

    /**
     * Удаление элемента из таблицы
     */
    @Override
    public void delete(K key) {
        int hash = hash(key, capacity);
        while (array[hash] != null) {
            if (array[hash].getKey().equals(key)) {
                array[hash] = new HashTableElement<>();
                --size;
                return;
            }
            if (++hash == capacity)
                hash = 0;
        }
    }

    /**
     * Поиск элемента
     */
    @Override
    public HashTableElement<K, V> search(K key) {
        if (key == null)
            throw new IllegalArgumentException("Не задан ключ для поиска");
        int hash = hash(key, capacity);
        while (array[hash] != null) {
            if (!array[hash].isDeleted() && array[hash].getKey().equals(key)) {
                return array[hash];
            }
            if (++hash == capacity) {
                hash = 0;
            }
        }
        return null;
    }

    /**
     * Вывод хеш-таблицы
     */
    @Override
    public void print() {
        System.out.println("--------------------");
        System.out.println("Хеш-таблица:");
        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");
            if (array[i] == null) {
                System.out.println();
            } else {
                System.out.println(array[i]);
            }
        }
        System.out.println("--------------------");
    }

    private void doubleArraySize() {
        HashTableElement<K, V>[] doubleArray = new HashTableElement[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            if (array[i] != null && !array[i].isDeleted()) {
                int hash = hash(array[i].getKey(), capacity * 2);
                while (doubleArray[hash] != null) {
                    hash++;
                }
                doubleArray[hash] =
                        new HashTableElement(array[i].getKey(), array[i].getValue());
            }
        }
        capacity *= 2;
        array = doubleArray;
    }

    public void hashFuncTest() {
        System.out.println("--------------------");
        System.out.println("Capacity: " + capacity);
        System.out.println("key -> id");
        for (int i = 0; i < capacity; i++) {
            if (array[i] != null && !array[i].isDeleted()) {
                System.out.println(array[i].getKey() + " -> " + hash(array[i].getKey(), capacity));
            }
        }
        System.out.println("--------------------");
    }

    public int getSize() {
        return size;
    }
}
