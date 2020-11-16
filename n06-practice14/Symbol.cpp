//
// Created by akimovve on 16.11.2020.
//

#include "Symbol.h"

Symbol::Symbol(char name, int inputs, float probability) {
	this->name = name;
	this->inputs = inputs;
	this->probability = probability;
	this->code = "";
}

Symbol::Symbol(int inputs) {
	this->inputs = inputs;
}
