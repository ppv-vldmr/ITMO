#include <iostream>
#include <unordered_map>
#include <cmath>
#include <iomanip>
#include <cstdlib>

using namespace std;

long foo() {
    int x = rand() % 100;
    long y = 0;
    for (int i = 0; i < x; i++) {
        y++;
    }
//    cout << "y: " << y << endl;
    return y;
}

int main()
{
    srand((unsigned)time(NULL));
    foo();
    size_t k1, k2, n;
    cin >> k1 >> k2 >> n;

    unordered_map<int, long double> p_x;
    unordered_map<int, unordered_map<int, long double>> p_xy;

    for (size_t i = 0; i < n; i++) {
        foo();
        int x, y;
        cin >> x >> y;
        p_x[x]++;
        p_xy[x][y]++;
    }

    for (auto &it : p_x) {
        foo();
        it.second /= n;
    }

    for (auto &iter : p_xy) {
        foo();
        for (auto &jt : iter.second)
        {
            jt.second /= n;
        }
    }

    long double result = 0;
    for (auto &item : p_xy) {
        foo();
        for (auto jt : item.second) {
            int x = item.first;
            long double v = jt.second;
            result -= v * (log(v) - log(p_x[x]));
        }
    }
    cout << setprecision(16) << fixed;
    cout << result << endl;
    return 0;
}
