package ru.mirea.n08pr16;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		// Исходные данные о вещах
		Thing[] things = {
				new Thing(7.7, 99),
				new Thing(1.73, 10),
				new Thing(8.0, 50),
				new Thing(15.1, 101),
				new Thing(4.02, 21),
				new Thing(0.6, 1),
				new Thing(10.9, 74),
				new Thing(3.3, 6)
		};
		// Максимальный вес рюкзака
		int maxWeight = 15;
		System.out.print("Макс. стоимость: " + calculate(things, maxWeight));
	}

	/**
	 * Подсчёт стоимости
	 * @param things Список из вещей
	 * @param maxWeight Макс. вес рюкзака
	 * @return Макс. стоимость
	 */
	private static int calculate(Thing[] things, int maxWeight) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node());

		int maxProfit = 0;
		Node node1;
		Node node2 = new Node();
		Arrays.sort(things); // Сортировка вещей по убыванию (cost/weight)
		while (!queue.isEmpty()) {
			node1 = queue.poll(); // Получение и удаление первого элемента из очереди

			if (node1.getLevel() == -1) {
				node2.setLevel(0);
			}
			if (node1.getLevel() == things.length - 1) {
				continue;
			}

			node2.setLevel(node1.getLevel() + 1);
			node2.setWeight(node1.getWeight() + things[node2.getLevel()].getWeight());
			node2.setCost(node1.getCost() + things[node2.getLevel()].getCost());

			if (node2.getCost() > maxProfit && maxWeight >= node2.getWeight()) {
				maxProfit = node2.getCost();
			}

			node2.setRoot(root(node2, maxWeight, things));
			if (node2.getRoot() > maxProfit) {
				queue.add(node2);
			}

			Node node3 = new Node();
			node3.setLevel(node2.getLevel());
			node3.setWeight(node1.getWeight());
			node3.setCost(node1.getCost());
			node3.setRoot(root(node3, maxWeight, things));

			if (node3.getRoot() > maxProfit) {
				queue.add(node3);
			}
		}
		return maxProfit;
	}

	/**
	 * Подсчёт границы стоимости в поддереве
	 * @param node Узел
	 * @param weight Вес
	 * @param things Список из вещей
	 * @return Граница стоимости
	 */
	private static Integer root(Node node, int weight, Thing[] things) {
		if (node.getWeight() >= weight) {
			return 0;
		}
		int lvl = node.getLevel() + 1;
		int totalWeight = (int) node.getWeight();
		int res = node.getCost();
		while ((lvl < things.length) && (totalWeight + things[lvl].getWeight() <= weight)) {
			totalWeight += things[lvl].getWeight();
			res += things[lvl].getCost();
			lvl++;
		}
		if (lvl < things.length) {
			res += (weight - totalWeight) * things[lvl].getCost() / things[lvl].getWeight();
		}
		return res;
	}
}
