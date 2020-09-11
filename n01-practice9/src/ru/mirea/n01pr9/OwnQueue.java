package ru.mirea.n01pr9;

public interface OwnQueue<T> {
	T element();
	boolean offer(T element);
	T peek();
	T poll();
	T remove();
	int getSize();
}
