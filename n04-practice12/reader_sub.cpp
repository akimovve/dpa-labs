//
// Created by akimovve on 19.10.2020.
//

#include <iostream>
#include "reader_sub.h"

using namespace std;

/**
 * Реализация класса "Читательский абонемент"
 */
ReaderSub::ReaderSub(string num, string name, string surname, string second_name, string address) {
	this->num = num;
	this->name = name;
	this->surname = surname;
	this->second_name = second_name;
	this->address = address;
}

ReaderSub::ReaderSub() {}

void ReaderSub::to_string() const {
	cout << "Номер читательского: " << num << "; \tФамилия: " << surname << "; Имя: " << name
		 << "; Отчество: " << second_name << "; Адрес: " << address << endl;
}