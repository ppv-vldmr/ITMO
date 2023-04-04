#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <cstdint>
#include <vector>
#include <random>

#include <sys/time.h>

#include <cilk/cilk.h>
#include <cilk/reducer_opadd.h>

#define SIZE 100000000
const int P = 4;

std::vector<int> prefix_sum(std::vector<int> &nums, int low, int high) {
    int n = high - low + 1;

    std::vector<int> A(n / 2);
    std::vector<int> res(n);

    res[0] = nums[0];

    if (n > 1) {
        cilk_for(int i = 0; i < n / 2; i++){
            A[i] = nums[2 * i] + nums[2 * i + 1];
        }

        vector<int> temp = prefix_sum(A, low, low + (high - low) / 2);
        cilk_for(int i = 1; i < n; i++){
            if (i % 2 == 1)
                res[i] = temp[i / 2];
            else
                res[i] = temp[(i - 1) / 2] + nums[i];
        }
    }

    return res;
}

int parralel_partition(std::vector<int> &nums, int x) {
    int low = 0;
    int high = nums.size() - 1;

    int n = high - low + 1;

    if (n == 1)
        return low;

    std::vector<int> temp(n);
    std::vector<int> less(n);
    std::vector<int> greater(n);

    cilk_for(int i = 0; i < n; i++){
        temp[i] = nums[low + i];
        if (temp[i] < x)
            less[i] = 1;
        else
            less[i] = 0;
        if (temp[i] > x)
            greater[i] = 1;
        else
            greater[i] = 0;
    }
    less = prefix_sum(less, 0, n - 1);
    greater = prefix_sum(greater, 0, n - 1);

    int k = low + less[n - 1];
    nums[k] = x;

    cilk_for(int i = 0; i < n; i++){
        if (temp[i] < x)
            nums[low + less[i] - 1] = temp[i];
        else if (temp[i] > x)
            nums[k + greater[i]] = temp[i];
    }

    return k;
}

void merge(std::vector<int> &nums, int low, int mid, int high) {
    int i, j, k;
    int n1 = mid - low + 1;
    int n2 = high - mid;

    std::vector<int> left(n1);
    std::vector<int> right(n2);

    for (int i = 0; i < n1; i++)
        left[i] = nums[low + i];
    for (int j = 0; j < n2; j++)
        right[j] = nums[mid + 1 + j];

    i = 0;
    j = 0;
    k = low;

    while (i < n1 && j < n2) {
        if (left[i] <= right[j]) {
            nums[k] = left[i];
            i++;
        } else {
            nums[k] = right[j];
            j++;
        }
        k++;
        while (i < n1) {
            nums[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            nums[k] = right[j];
            j++;
            k++;
        }
    }
}

void merge_sort(std::vector<int> &nums, int low, int high) {
    if (low < high) {
        int mid = low + (high - low) / 2;
        merge_sort(nums, low, mid);
        merge_sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }
}

void sort(std::vector<int> &nums, int low, int high) {
    srand(time(NULL));

    if (low >= high)
        return;

    int n = high - low + 1;

    if (n <= 30) {
        merge_sort(nums, low, high);
        return;
    }
    int indx = 0;
    if (nums.size() % 2 == 0)
        indx = (nums.size() / 2 + nums.size() / 2) / 2;
    else
        indx = nums.size() / 2;

    int x = nums[indx];
    int k = parralel_partition(nums, x);
    cilk_spawn sort(nums, low, k - 1);
    sort(nums, k + 1, high);
}

int main() {
    __cilkrts_set_param("nworkers", "4");

    std::random_device rd;
    std::mt19937 g(rd());
    std::uniform_int_distribution<int> distribution(0, 99);

    std::vector<int> vec(SIZE);
    for (int i = 0; i < SIZE; i++) {
        int input = distribution(g);
        vec[i] = input;
    }

//    for (int i = 0; i < vec.size(); i++) {
//        std::cout << vec[i] << " ";
//    }

    auto start = high_resolution_clock::now();
    cilk_spawn sort(vec, 0, SIZE - 1);
    cilk_sync;
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "Time taken: " << duration.count() << " milliseconds" << endl;

//    for (int i = 0; i < vec.size(); i++) {
//        std::cout << vec[i] << " ";
//    }

    return 0;
}