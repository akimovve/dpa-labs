//
// Created by akimovve on 01.11.2020.
//

#include "Edge.h"

Edge::Edge() {}

Edge::Edge(int from, int to, int weight) {
	this->from = from - 1;
	this->to = to - 1;
	this->weight = weight;
}

int Edge::get_from() const {
	return from;
}

int Edge::get_to() const {
	return to;
}

int Edge::get_weight() const {
	return weight;
}
