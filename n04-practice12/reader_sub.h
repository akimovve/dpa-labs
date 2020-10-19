//
// Created by akimovve on 19.10.2020.
//

#ifndef READERSUB_H
#define READERSUB_H

#include <string>

using namespace std;

/**
 * Класс "Читательский абонемент"
 */
class ReaderSub {
public:
	ReaderSub(string num, string name, string surname, string second_name, string address);

	ReaderSub();

	void to_string() const;

	string num;
	string name;
	string surname;
	string second_name;
	string address;
};

#endif //READERSUB_H