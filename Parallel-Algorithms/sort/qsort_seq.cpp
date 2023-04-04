#include <iostream>
#include <utility>
#include <vector>
#include <chrono>
#include <random>

#define ARRAY_SIZE 100000000

std::vector<int> array;

using namespace std::chrono;

void print_array() {
    for (const auto &el : array) {
        std::cout << el << ' ';
    }
    std::cout << std::endl;
}

size_t partition_array(size_t start, size_t end) {
    int pivot = array[start];
    size_t i = start;
    size_t j;

    for (j = start + 1; j < end; j++) {
        if (array[j] <= pivot) {
            i = i + 1;
            std::swap(array[i], array[j]);
        }
    }

    std::swap(array[i], array[start]);
    return i;
}

void qsort_array(size_t start, size_t end) {
    size_t pivot;
    if (start < end) {
        pivot = partition_array(start, end);

        qsort_array(start, pivot);
        qsort_array(pivot + 1, end);
    }
}

int main() {
    std::random_device rd;
    std::mt19937 g(rd());
    std::uniform_int_distribution<int> distribution(0, 99);

    array.reserve(ARRAY_SIZE);
    for (int i = 0; i < ARRAY_SIZE; i++) {
        int element = distribution(g);
        array.push_back(element);
    }

//    print_array();

    auto start = high_resolution_clock::now();
    qsort_array(0, ARRAY_SIZE);
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "Time taken: " << duration.count() << " milliseconds" << endl;

//    print_array();

    return 0;
}
