//
// Created by akimovve on 01.11.2020.
//

#include "Graph.h"

/**
 * Инициализация графа
 * @param vertices_num - количество вершин графа
 */
Graph::Graph(int vertices_num) {
	if (vertices_num <= 0) {
		cout << "Количество вершин не может быть <= 0.\n";
		return;
	}
	this->vertices_num = vertices_num;
	this->edges = vector<Edge>();
	this->distance = vector<int>(vertices_num, INF);
}

/**
 * Добавление связей вершин графа
 * @param from - индекс вершины, из которой исходит дуга (ребро)
 * @param to - индекс вершины, к которой приходит дуга
 * @param weight - вес дуги
 */
bool Graph::add_edge(int from, int to, int weight) {
	if (from - 1 < 0 || to - 1 < 0 || from - 1 >= vertices_num || to - 1 >= vertices_num) {
		cout << "Индекс вершины не принадлежит [0; " << vertices_num - 1 << "].\n";
		return false;
	}
	if (weight == 0) {
		cout << "Вес не может быть равен 0.\n";
		return false;
	}
	Edge *edge = new Edge(from, to, weight);
	edges.push_back(*edge);
	return true;
}

/**
 * Функция подсчитывания кратчайшего пути по алгоритму Беллмана-Форда
 * @param start - индекс вершины, расстояния от которой будут считаться
 */
void Graph::bellman_ford(int start) {
	// TODO: Проверки на индекс
	start_ver = --start;
	/*
	 * Шаг 1: Инициализация вектора расстояний от [start]
	 * до каждой из вершины. Все значения, кроме [start],
	 * присвоены INF. А [start] равен 0
	 */
	distance[start_ver] = 0;

	/*
	 * Шаг 2: Выполнение операции сравнения текущей дистанции
	 * с дистанцией по другим рёбрам (n - 1) раз
	 */
	for (int j = 0; j < vertices_num - 1; j++) {
		compare(true);
	}

	/*
	 * Шаг 3: Проверка на цикл отрицательного веса.
	 * Вышеупомянутый шаг гарантирует нахождение кратчайших расстояний,
	 * если граф не содержит цикла отрицательного веса.
	 * Если в 3-ем шаге получится найти ещё более короткий путь, то
	 * это цикл отрицательного веса
	 */
	if (!compare(false)) {
		return;
	}
}

/**
 * Цикл выборки кратчайшего пути
 * @param step - флаг для определениия шага
 * (используется для вызова проверки на цикл отрицательного веса)
 * @return false, если есть цикл отриц. веса
 */
bool Graph::compare(bool step) {
	int from, to, weight;
	for (auto &edge: edges) {
		from = edge.get_from();
		to = edge.get_to();
		weight = edge.get_weight();
		if (distance[to] > distance[from] + weight) {
			if (step) {
				distance[to] = distance[from] + weight;
			} else {
				cout << "Граф содержит цикл отрицательного веса.\n";
				return false;
			}
		}
	}
	return true;
}

/**
 * Вывод вектора кратчаших расстояний от графа с индексом start
 */
void Graph::print() {
	for (int j = 0; j < vertices_num; j++) {
		if (distance[j] == INF) {
			cout << "Связь отсутствует.\n";
		} else {
			cout << distance[j] << endl;
		}
	}
}

/**
 * Вывод вектора кратчаших расстояний в визуально более понятном виде
 */
void Graph::print_edges() {
	int j;
	cout << "Исходные данные:\n";
	for (auto &edge : edges) {
		cout << "Ребро: " << edge.get_from() + 1 << " -> " << edge.get_to() + 1 << " = " << edge.get_weight() << "\n";
	}
	cout << "\nКратчайшие пути до каждой вершины из " << start_ver + 1 << ":\n";
	for (j = 0; j < vertices_num; j++) {
		cout << setw(3) << "[" << j + 1 << "]";
	}
	cout << "\n|";
	for (j = 0; j < vertices_num; j++) {
		if (distance[j] == INF) {
			cout << setw(3) << "NO" << setw(2) << "|";
		} else {
			cout << setw(3) << distance[j] << setw(2) << "|";
		}
	}
	cout << "\n";
}
