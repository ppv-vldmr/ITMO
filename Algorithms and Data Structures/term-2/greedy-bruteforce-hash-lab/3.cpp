#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<long long> prices(7);

long long my_pow(int n, int x) {
    long long res = 1;
    for (int i = 0; i < x; i++) {
        res *= n;
    }
    return res;
}

long long first_number(long long n) {
    int k = 0;
    while (n / 10 > 0) {
        n /= 10;
        k++;
    }
    return n * my_pow(10, k);
}

long long find_solution(long long n) {
    if (n <= 0)
        return 0;
    long long sum_cur = 100000000000LL;
    for (int i = 0; i < 7; i++) {
        if (prices[i] * ((n + my_pow(10, i) - 1) / my_pow(10, i)) < sum_cur) {
            sum_cur = prices[i] * ((n + my_pow(10, i) - 1) / my_pow(10, i));
        }
    }
    long long in1 = 100000000000LL;
    long long t = first_number(n);
    for (int i = 0; my_pow(10, i) <= t && i < 7; i++) {
        if (in1 > t / my_pow(10, i) * prices[i]) {
            in1= t / my_pow(10, i) * prices[i];
        }
    }
    return min(sum_cur, in1 + find_solution(n - t));
}

int main() {
    freopen("printing.in", "r", stdin);
    freopen ("printing.out", "w", stdout);
    long long n;
    cin >> n;
    for (long long & i : prices) {
        cin >> i;
    }
    cout << find_solution(n);
    return 0;
}