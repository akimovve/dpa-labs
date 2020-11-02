//
// Created by akimovve on 01.11.2020.
//

#ifndef EDGE_H
#define EDGE_H

/**
 * Класс - дуга (ребро) графа
 */
class Edge {
public:
	Edge();

	Edge(int from, int to, int weight);

	int get_from() const;

	int get_to() const;

	int get_weight() const;

private:
	int from;
	int to;
	int weight;
};


#endif // EDGE_H
