//
// Created by akimovve on 19.10.2020.
//

#ifndef HANDLER_H
#define HANDLER_H

#include <iostream>
#include <fstream>
#include <vector>
#include "reader_sub.h"

using namespace std;

class Handler {
public:
	explicit Handler(const string &file_name);

	void add_data();

	void print_file();

	int find_by_key(const string &num);

	bool delete_found_item_by_key(const string &num);

private:
	string file_name;

	void save_data_to_file(vector<ReaderSub> &data);

	void copy_data_from_file_to_vector(vector<ReaderSub> &data);
};

#endif //HANDLER_H