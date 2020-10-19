//
// Created by akimovve on 19.10.2020.
//

#include "handler.h"

Handler::Handler(const string &file_name) {
	this->file_name = file_name;
}

void Handler::add_data() {
	vector<ReaderSub> data;
	ReaderSub reader01 =
			ReaderSub("57101", "Олег", "Олегов", "Олегович", "Волгоград");
	ReaderSub reader02 =
			ReaderSub("99923", "Пётр", "Петров", "Петрович", "Санкт-Петербург");
	ReaderSub reader03 =
			ReaderSub("25632", "Сергей", "Сергеев", "Сергеевич", "Владивосток");
	ReaderSub reader04 =
			ReaderSub("11715", "Иван", "Иванов", "Иванович", "Тверь");
	ReaderSub reader05 =
			ReaderSub("99921", "Семён", "Семёнов", "Семёнович", "Саратов");
	data.push_back(reader01);
	data.push_back(reader02);
	data.push_back(reader03);
	data.push_back(reader04);
	data.push_back(reader05);
	save_data_to_file(data);
}

void Handler::save_data_to_file(vector<ReaderSub> &data) {
	ofstream fout(file_name);

	for (int i = 0; i < data.size(); i++) {
		fout << data.at(i).num << '#' << data.at(i).surname << '#' << data.at(i).name << '#'
			 << data.at(i).second_name << '#' << data.at(i).address;
		if (i != data.size() - 1) {
			fout << endl;
		}
	}
	fout.close();
}

void Handler::copy_data_from_file_to_vector(vector<ReaderSub> &data) {
	ifstream fin;
	fin.open(file_name);

	if (fin.is_open()) {
		string num;
		ReaderSub readerSub;
		data.clear();

		while (fin.good()) {
			getline(fin, num, '#');

			if (num.empty() || num == "\n") {
				break;
			} else if (!data.empty()) {
				num.erase(0, 1);
			}

			readerSub.num = num;
			getline(fin, readerSub.surname, '#');
			getline(fin, readerSub.name, '#');
			getline(fin, readerSub.second_name, '#');
			fin >> readerSub.address;
			data.push_back(readerSub);
		}
	} else {
		cout << "Файл не может быть открыт " << file_name << endl;
	}
	fin.close();
}

void Handler::print_file() {
	vector<ReaderSub> data;
	copy_data_from_file_to_vector(data);

	if (data.empty()) {
		cout << "Файл пустой\n";
		return;
	}

	for (auto &d : data) {
		d.to_string();
	}
}

int Handler::find_by_key(const string &num) {
	vector<ReaderSub> data;
	copy_data_from_file_to_vector(data);

	for (int i = 0; i < data.size(); i++) {
		if (data[i].num == num) {
			cout << "Найдена запись по ключу " << num << ": ";
			data[i].to_string();
			return i;
		}
	}
	cout << "Запись по ключу " << num << " не найдена\n";
	return -1;
}

bool Handler::delete_found_item_by_key(const string &num) {
	vector<ReaderSub> data;
	copy_data_from_file_to_vector(data);

	int index = find_by_key(num);
	if (index == -1) {
		return false;
	}
	cout << "Удалена запись по ключу " << num << ": ";
	data[index].to_string();

	for (int i = index; i < data.size() - 1; i++) {
		data[i] = data[i + 1];
	}
	data.pop_back();
	save_data_to_file(data);
	return true;
}