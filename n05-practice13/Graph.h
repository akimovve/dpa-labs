//
// Created by akimovve on 01.11.2020.
//

#ifndef GRAPH_H
#define GRAPH_H

#include <vector>
#include <iostream>
#include <iomanip>
#include "Edge.h"

using namespace std;
/**
 * Класс - граф
 */
class Graph {
public:
	Graph(int vertices_num);

	bool add_edge(int from, int to, int weight);

	void bellman_ford(int start);

	bool compare(bool step);

	void print();

	void print_edges();

	int INF = 1e9;
private:
	int vertices_num;
	int start_ver;
	vector<Edge> edges;
	vector<int> distance;
};


#endif // GRAPH_H
