#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <cmath>
#include <ctime>

using namespace std;

int n, m;

vector<vector<int>> row;
vector<int> masks;
set<int> el;

void mine() {
    int d = rand() % 100;
//    cout << d << endl;
    for (int i = 0; i < d; i++) {
        int x = 10;
    }
}

bool check(int mask, int bit) {
    return (mask & (1 << bit)) > 0;
}

int getMask(vector<int> v) {
    int res = 0;
    mine();
    for (int rowSize : v) {
        res += (1 << rowSize);
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("check.in", "r", stdin);
    freopen("check.out", "w", stdout);
    cin.tie(nullptr);
    cin >> n >> m;
    row.resize(m);
    masks.resize(1 << n, false);
    srand(time(nullptr));
    bool first = false;
    for(int i = 0; i < m; i++) {
        int k, pos = 0;
        cin >> k;
        row[i].resize(k);
        for (int j = 0; j < k; j++) {
            int ind;
            cin >> ind;
            ind--;
            pos += 1 << ind;
            row[i][j] = ind;
            el.insert(ind);
        }
        masks[pos] = true;
        if (row[i].empty()) {
            mine();
            first = true;
        }
    }
    if (!first) {
        cout << "NO";
        return 0;
    }
    for(int i = 0; i < m; i++) {
        int rowSize = row[i].size();
        for (int mask = 1; mask < (1 << rowSize) - 1; mask++) {
            int pos = 0;
            for(int j = 0; j < rowSize; j++) {
                if (check(mask, j)) {
                    mine();
                    pos += (1 << row[i][j]);
                }
            }
            if (!masks[pos]) {
                mine();
                cout << "NO";
                return 0;
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            if (row[i].size() > row[j].size()) {
                mine();
                int m1 = getMask(row[i]);
                int m2 = getMask(row[j]);
                for (int z = 0; z < n; ++z) {
                    if (check(m2, z) && check(m1, z)) {
                        mine();
                        m1 ^= (1 << z);
                    }
                }
                set<int> els;
                for (int z = 0; z < n; z++) {
                    if (check(m1, z)) {
                        mine();
                        els.insert(z);
                    }
                }
                bool ok3 = false;
                for (int ind : els) {
                    if (masks[(m2 + (1 << ind))]) {
                        mine();
                        ok3 = true;
                        break;
                    }
                }
                if (!ok3) {
                    mine();
                    cout << "NO";
                    return 0;
                }
            }
        }
    }
    cout << "YES";
    return 0;
}