//
// Created by akimovve on 16.11.2020.
//

#ifndef SYMBOL_H
#define SYMBOL_H

#include <string>

using namespace std;

/**
 * Класс-символ (!работает только с латиницей)
 */
class Symbol {
public:
	char name;
	int inputs;
	float probability;
	string code;

	Symbol(char name, int inputs, float probability);

	Symbol(int inputs);
};


#endif // SYMBOL_H
