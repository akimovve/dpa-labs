//
// Created by akimovve on 16.11.2020.
//

#include "Node.h"

/**
 * Объединение двух узлов (и сложение колиичества входов)
 * @param left
 * @param right
 */
Node::Node(Node *left, Node *right) {
	this->left = left;
	this->right = right;
	this->symbol = new Symbol(left->symbol->inputs + right->symbol->inputs);
}

Node::Node(Symbol *symbol) {
	this->symbol = symbol;
}

bool Comparator::operator()(const Node *o1, const Node *o2) {
	return o2->symbol->inputs < o1->symbol->inputs;
}
