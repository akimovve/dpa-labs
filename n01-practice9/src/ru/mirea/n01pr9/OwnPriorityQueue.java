package ru.mirea.n01pr9;

import java.util.NoSuchElementException;

public class OwnPriorityQueue<T extends Comparable<T>> implements OwnQueue<T> {
	final private OwnArrayList<T> queue;

	public OwnPriorityQueue() {
		this.queue = new OwnArrayList<>();
	}

	@Override
	public T element() {
		return queue.get(0);
	}

	@Override
	public boolean offer(T element) {
		if (element == null) {
			throw new NullPointerException();
		}

		if (queue.isEmpty()) {
			queue.add(element);
			return true;
		}

		for (int i = 0; i < queue.getSize(); i++) {
			if (element.compareTo(queue.get(i)) > 0) {
				queue.add(i, element);
				return true;
			}
		}
		queue.add(element);
		return true;
	}

	@Override
	public T peek() {
		if (queue.isEmpty()) {
			return null;
		}
		return queue.get(queue.getSize() - 1);
	}

	@Override
	public T poll() {
		if (queue.isEmpty()) {
			return null;
		}
		T element = queue.get(0);
		queue.remove(0);
		return element;
	}

	@Override
	public T remove() {
		if (queue.isEmpty()) {
			throw new NoSuchElementException();
		}
		T element = queue.get(0);
		queue.remove(0);
		return element;
	}

	@Override
	public int getSize() {
		return queue.getSize();
	}

	public Object[] toArray() {
		return queue.toArray();
	}
}
