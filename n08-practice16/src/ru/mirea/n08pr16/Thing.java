package ru.mirea.n08pr16;

/**
 * Класс - вещь
 */
public class Thing implements Comparable<Thing> {
	private double weight;
	private int cost;

	/**
	 * Инициализация вещи
	 * @param weight Вес
	 * @param cost Цена
	 */
	public Thing(double weight, int cost) {
		this.weight = weight;
		this.cost = cost;
	}

	/**
	 * Сравнение вещей
	 * @param o Для сравнения
	 * @return Результат сравнения
	 */
	@Override
	public int compareTo(Thing o) {
		return (int) (o.cost / o.weight - this.cost / this.weight);
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Вещь: " +
				"вес = " + weight +
				", цена = " + cost;
	}
}
