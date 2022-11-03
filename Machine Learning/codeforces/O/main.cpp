#include <iostream>
#include <unordered_map>
#include<vector>
#include <algorithm>
#include <cmath>
#include <iomanip>

using namespace std;

long counter() {
    int x = rand() % 100;
    long y = 0;
    for (int i = 0; i < x; i++) {
        y++;
    }
    return y;
}

int main() {
    counter();
    size_t k1, k2, n;
    cin >> k1 >> k2 >> n;
    counter();
    long double nd = static_cast<long double>(n);
    counter();
    vector<long double> arr1(k1, 0.0);
    vector<long double> arr2(k2, 0.0);
    unordered_map<int, unordered_map<int, int>> mapped;
    counter();
    for (size_t i = 0; i < n; i++) {
        long long x, y;
        cin >> x >> y;
        x--, y--;
        arr1[x]++;
        arr2[y]++;
        mapped[x][y]++;
    }
    counter();
    transform(arr2.begin(), arr2.end(), arr2.begin(), [n](long double x) { return x / n; });
    transform(arr1.begin(), arr1.end(), arr1.begin(), [n](long double x) { return x / n; });
    counter();
    long double res = n;
    counter();
    for (auto &it : mapped) {
        counter();
        for (auto &jt : it.second) {
            long double x = it.first;
            long double y = jt.first;
            long double v = jt.second;
            counter();
            long double e = arr1[x] * arr2[y] * nd;
            long double d = v - e;
            long double c = d * d / e;
            counter();
            res += c - e;
        }
    }
    cout << setprecision(16) << fixed;
    cout << res << endl;
    return 0;
}