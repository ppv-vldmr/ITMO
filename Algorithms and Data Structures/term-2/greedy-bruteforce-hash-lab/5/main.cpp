#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct sas {
    int up;
    int down;
    int ind;

    sas(int a, int b, int c) {
        this->down = a;
        this->up = b;
        this->ind = c;
    }
};

bool comp(sas a, sas b) {
    return a.down < b.down;
}

bool comp2(sas a, sas b) {
    return a.up > b.up;
}

int main() {
    freopen("apples.in", "r", stdin);
    freopen("apples.out", "w", stdout);
    int n, s;
    cin >> n >> s;
    vector<sas> a1, a2;
    for (int i = 0; i < n; i++) {
        int x1, x2;
        cin >> x1 >> x2;
        if (x1 <= x2) {
            a1.push_back(sas(x1, x2, i));
        } else {
            a2.push_back(sas(x1, x2, i));
        }
    }
    sort(a1.begin(), a1.end(), comp);
    for (int i = 0; i < a1.size(); i++) {
        s += a1[i].up - a1[i].down;
    }
    sort(a2.begin(), a2.end(), comp2);
    for (int i = 0; i < a2.size(); i++) {
        if (s - a2[i].down > 0) {
            s = s - a2[i].down + a2[i].up;
        } else {
            cout << -1 << "\n";
            return 0;
        }
    }
    for (int i = 0; i < a1.size(); i++) {
        cout << a1[i].ind + 1 << " ";
    }
    for (int i = 0; i < a2.size(); i++) {
        cout << a2[i].ind + 1 << " ";
    }
    return 0;
}
