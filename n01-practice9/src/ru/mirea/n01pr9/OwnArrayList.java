package ru.mirea.n01pr9;

import java.util.Arrays;
import java.util.Objects;

public class OwnArrayList<T> {
    private Object[] data;
    private int size = 0;
    final private int DEFAULT_SIZE = 16;

    public OwnArrayList() {
        data = new Object[DEFAULT_SIZE];
    }

    public OwnArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalStateException("Размер списка не может быть меньше 0.");
        }
        data = (initialCapacity == 0)? new Object[DEFAULT_SIZE] : new Object[initialCapacity];
    }

    public void add(T element) {
        checkCapacity();
        data[size++] = element;
    }

    public void add(int index, T element) {
        checkCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) data[index];
    }

    public void remove(int index) {
        if (size - index >= 0) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    private void checkCapacity() {
        if (size + 1 > data.length) {
            Object[] temp = new Object[data.length + DEFAULT_SIZE];
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(
                Arrays.stream(data)
                        .filter(Objects::nonNull)
                        .toArray());
    }
}
