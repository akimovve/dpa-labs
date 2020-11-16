#include <iostream>
#include <string>
#include <map>
#include <list>

#include "Symbol.h"
#include "Node.h"

using namespace std;

char esc_char = 27;

void print_table(const list<Node *> &list, const string &title) {
	cout << "\n" << esc_char << "[1m" << title << esc_char << "[0m";
	cout << "\nАлфавит | Кол.вх. | Вероятн.\n";
	for (auto item : list) {
		Symbol *s = item->symbol;
		printf("   %c    |    %d    |   %.6g\n", s->name, s->inputs, s->probability);
	}
}

/**
 * Построение таблицы частот
 * @param input - исходная строк
 * @return список узлов (дерево частот)
 */
list<Node *> frequency_table(const string &input) {
	map<char, int> frequency;
	for (char c : input) {
		frequency[c]++;
	}
	list<Node *> nodes;
	for (auto &fr : frequency) {
		auto *symbol = new Symbol(fr.first, fr.second, (float) fr.second / input.size());
		nodes.push_back(new Node(symbol));
	}
	print_table(nodes, "Таблица частот");
	return nodes;
}

/**
 * Сортировка таблицы частот и построение дерева
 * @param nodes - узлы
 * @return дерево кодирования Хаффмана
 */
list<Node *> build_tree(list<Node *> nodes) {
	nodes.sort(Comparator());
	print_table(nodes, "Таблица отсортированных частот");

	while (nodes.size() != 1) {
		Node *smr = nodes.back();
		nodes.pop_back();
		Node *sml = nodes.back();
		nodes.pop_back();

		nodes.push_back(new Node(sml, smr));
		nodes.sort(Comparator());
	}
	return nodes;
}

map<char, string> symbols_code;

/**
 * Составление кодов для символов
 * @param root - текущая вершина
 * @param code - код символа
 */
void build_code_table(Node *root, const string &code) {
	Node *l = root->left;
	Node *r = root->right;

	if (l != nullptr)
		build_code_table(l, code + "0");

	if (r != nullptr)
		build_code_table(r, code + "1");

	if (l == nullptr && r == nullptr) {
		root->symbol->code = code;
		symbols_code[root->symbol->name] = root->symbol->code;
	}
}

/**
 * Вывод результата сжатия данных и подсчёт количества символов в коде
 */
int get_result(const string &input) {
	cout << "\n" << esc_char << "[1m" << "Связь символов и кодов\n" << esc_char << "[0m";
	for (const auto &s : symbols_code) {
		cout << s.first << ": " << s.second << "\n";
	}

	cout << "\n" << esc_char << "[1m" << "Закодированная строка по алгоритму Хаффмана\n" << esc_char << "[0m";
	string counter;
	for (auto ch : input) {
		cout << symbols_code[ch];
		counter += symbols_code[ch];
	}
	return counter.size();
}

int to_2(int in_10) {
	int bin = 0, c = 1;
	while (in_10) {
		bin += c * (in_10 % 2);
		c *= 10;
		in_10 /= 2;
	}
	return bin;
}

/**
 * Перевод исходной строки в ASCII
 * @param input
 * @return длина зашифрованного слова
 */
int ascii_code(const string &input) {
	cout << "\n" << esc_char << "[1m" << "Закодированная строка по ASCII\n" << esc_char << "[0m";
	string counter;
	int c;
	for (auto ch : input) {
		cout << to_2((int) ch);
		counter += to_string(to_2((int) ch));
	}
	return counter.length();
}

// pypkin vasiliw kirillovih
// akimov valery evgenievich
// bcbcbcbcbabcbbcbbccccabacbcaba@bcccccccc

int main() {
	string input;
	cout << "Введите строку для кодирования алгоритмом Хаффмана: ";
	getline(cin, input);

	list<Node *> nodes = frequency_table(input); // Таблица частот

	nodes = build_tree(nodes); // Дерево Хаффмана

	build_code_table(nodes.front(), ""); // Таблица кодов символов

	int huffman = get_result(input); // Вывод результата кодировки и подсчёт длины зашифр. слова

	int ascii = ascii_code(input); // Перевод в ASCII двоичный код и подсчёт его длины

	cout << "\n\nДлина кода по алгоритму Хаффмана: " << huffman << "\nДлина кода по ASCII: " << ascii;
	cout << "\nОтношение результата кодирования алгоритма Хаффмана к ASCII: " << (float) huffman / (float) ascii
		 << "\n";
	return 0;
}
