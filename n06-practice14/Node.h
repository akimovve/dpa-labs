//
// Created by akimovve on 16.11.2020.
//

#ifndef NODE_H
#define NODE_H

#include <iostream>
#include "Symbol.h"

/**
 * Класс-узел для связей
 */
class Node {
public:
	Symbol *symbol;
	Node *left;
	Node *right;

	Node(Node *left, Node *right);

	Node(Symbol *symbol);
};

struct Comparator {
	bool operator()(const Node *o1, const Node *o2);
};

#endif // NODE_H
