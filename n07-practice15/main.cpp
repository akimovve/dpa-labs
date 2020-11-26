#include <iostream>
#include <string>

using namespace std;

void print_brackets(int f, int s, int (&array)[6][6]);

int *count_scalar_order(const int *test_array, int len) {

	int matrix_max[len][len];
	int matrix_min[len][len];
	int s_max[len][len];
	int s_min[len][len];

	for (int i = 0; i < len; i++) {
		for (int j = 0; j < len; j++) {
			matrix_max[i][j] = 0;
			matrix_min[i][j] = 0;
			s_max[i][j] = 0;
			s_min[i][j] = 0;
		}
	}

	for (int i = 1; i < len; i++) {
		for (int j = 0; j < len - i; j++) {

			int k = i + j;
			matrix_max[j][k] = INT_MIN;
			matrix_min[j][k] = INT_MAX;

			for (int e = j; e < k; e++) {
				int expr = matrix_min[j][e] +
						   matrix_min[e + 1][k] +
						   test_array[k + 1] * test_array[e + 1] * test_array[j];

				if (expr >= matrix_max[j][k]) {
					matrix_max[j][k] = expr;
					matrix_max[k][j] = e;
					s_max[j][k] = e;
				}

				if (expr < matrix_min[j][k]) {
					matrix_min[j][k] = expr;
					matrix_min[k][j] = e;
					s_min[j][k] = e;
				}
			}
		}
	}

	cout << "\nДля макс.: ";
	print_brackets(0, len - 1, reinterpret_cast<int (&)[6][6]>(s_max));
	cout << "\nДля мин.: ";
	print_brackets(0, len - 1, reinterpret_cast<int (&)[6][6]>(s_min));
	cout << "\n\n";

	return new int[]{matrix_max[0][len - 1], matrix_min[0][len - 1]};
}

void print_brackets(int f, int s, int (&array)[6][6]) {
	if (f == s) {
		cout << 'A' << f;
		return;
	}
	cout << '(';
	print_brackets(f, array[f][s], array);
	cout << " × ";
	print_brackets(array[f][s] + 1, s, array);
	cout << ')';
}

// Задача 2.
int main() {
	int test_array[] = {5, 10, 3, 12, 5, 50, 6};
	int len = sizeof(test_array) / sizeof(test_array[0]) - 1;

	int *res = count_scalar_order(
			test_array,
			len
	);

	printf("Макс. число скалярных произведений: %d\n", res[0]);
	printf("Мин. число скалярных произведений: %d\n", res[1]);
	return 0;
}
