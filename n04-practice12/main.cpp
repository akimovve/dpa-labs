#include <iostream>
#include "handler.h"

int main() {
	string key;
	Handler handler("source.txt");
	// Заполнение вектора тестовыми данными
	handler.add_data();

	cout << "Содержимое файла:\n";
	handler.print_file();

	cout << "\nПоиск по ключу. Введите ключ: ";
	cin >> key;
	handler.find_by_key(key);

	cout << "\nУдаление по ключу. Введите ключ: ";
	cin >> key;
	handler.delete_found_item_by_key(key);

	cout << "\nСодержимое файла:\n";
	handler.print_file();
	return 0;
}