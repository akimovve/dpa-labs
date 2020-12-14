package ru.mirea.n08pr16;

/**
 * Класс - узел для хранения информации о дереве решений
 */
public class Node {
	private double weight;
	private int cost;
	private int root;
	private int level;

	/**
	 * Инициализация узла
	 * @property weight Вес
	 * @property cost Стоимость узлов от корня до данного узла
	 * @property root Верхняя граница макс. стоимости в поддереве данного узла
	 * @property level Глубина узла в дереве решений
	 */
	public Node() {
		this.weight = 0.0;
		this.cost = 0;
		this.root = 0;
		this.level = -1;
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

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Node {" +
				"weight=" + weight +
				", cost=" + cost +
				", root=" + root +
				", level=" + level +
				'}';
	}
}
