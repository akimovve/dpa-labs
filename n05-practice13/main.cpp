#include <iostream>
#include <vector>

#include "Graph.h"

using namespace std;

void reserved_test() {
	Graph *graph = new Graph(6);
	graph->add_edge(1, 2, -3);
	graph->add_edge(1, 1, 8);
	graph->add_edge(2, 3, 1);
	graph->add_edge(3, 2, 1);
	graph->add_edge(3, 4, -2);
	graph->add_edge(3, 1, 2);
	graph->add_edge(4, 6, 10);
	graph->bellman_ford(1);
	graph->print_edges();
}

void custom() {
	int vertices_num, edges, from, to, weight, start;
	cout << "Введите количество вершин графа: ";
	cin >> vertices_num;
	Graph *graph = new Graph(vertices_num);
	cout << "Введите количество параметров для дуг: ";
	cin >> edges;
	if (edges <= 0) {
		cout << "Количество параметров не может быть <= 0.";
		return;
	}
	cout << "От какой вершины считать кратчайшиие расстояния: ";
	cin >> start;
	cout << "Введите параметры для дуг (от до вес):\n";
	for (int i = 0; i < edges; i++) {
		cout << i + 1 << ". ";
		cin >> from >> to >> weight;
		graph->add_edge(from, to, weight);
	}
	graph->bellman_ford(start);
	graph->print_edges();
	cout << "\nВывод в другом виде:\n";
	graph->print();
}

int main() {
	reserved_test();
	//custom();
	return 0;
}

/*
 * 6
 * 7
 * 1
 * 1, 2, -3
 * 1, 1, 8
 * 2, 3, 1
 * 3, 2, 1
 * 3, 4, -2
 * 3, 1, 2
 * 4, 6, 10
 *
 * Ответ:
 * -3; -2; -4; NO; 6
 */