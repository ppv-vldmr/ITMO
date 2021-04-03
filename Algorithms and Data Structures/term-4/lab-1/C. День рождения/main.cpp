#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

typedef long long ll;
typedef long double ld;
typedef unsigned long long ull;

#define x first
#define y second
#define pb push_back

vector<vector<int>> g, rg, org;
vector<int> matching;
vector<bool> used, visited;
size_t n, m;
vector<vector<bool>> board;

void dfs1(int v) {
    if (visited[v]) {
        return;
    }
    visited[v] = true;
    for (const auto &to : org[v]) {
        dfs1(to);
    }
}

bool dfs(int v) {
    if (used[v]) {
        return false;
    }
    used[v] = true;
    for (const auto to : rg[v]) {
        if (matching[to] == -1) {
            matching[to] = v;
            return true;
        } else {
            bool res = dfs(matching[to]);
            if (res) {
                matching[to] = v;
                return true;
            }
        }
    }
    return false;
}

int main() {
    int q;
    cin >> q;
    for (size_t x = 0; x < q; x++) {
        cin >> n >> m;
        vector<vector<int>> num;
        g.clear();
        g.resize(n);
        rg.clear();
        rg.resize(n);
        matching.clear();
        matching.resize(m, -1);
        vector<vector<bool>> rev;
        rev.resize(n, vector<bool>(m, true));
        int to;
        for (size_t i = 0; i < n; i++) {
            cin >> to;
            while (to != 0) {
                g[i].pb(to - 1);
                rev[i][to - 1] = false;
                cin >> to;
            }
        }
        for (size_t i = 0; i < n; i++) {
            for (size_t j = 0; j < m; j++) {
                if (rev[i][j]) {
                    rg[i].pb(j);
                }
            }
        }
        for (size_t i = 0; i < n; i++) {
            used.clear();
            used.resize(n, false);
            dfs(i);
        }
        vector<pair<int, int>> p;
        vector<vector<bool>> matchingMatrix(n, vector<bool>(m, false));
        set<int> matched;
        for (size_t i = 0; i < m; i++) {
            if (matching[i] != -1) {
                p.pb({matching[i], i});
                matchingMatrix[matching[i]][i] = true;
                matched.insert(matching[i]);
            }
        }
        org.clear();
        org.resize(n + m);
        for (size_t i = 0; i < n; i++) {
            for (size_t j = 0; j < rg[i].size(); j++) {
                if (matchingMatrix[i][rg[i][j]]) {
                    org[rg[i][j] + n].pb(i);
                } else {
                    org[i].pb(rg[i][j] + n);
                }
            }
        }
        visited.clear();
        visited.resize(n + m);
        for (size_t i = 0; i < n; i++) {
            if (!matched.count(i)) {
                dfs1(i);
            }
        }
        set<int> cover;
        for (size_t i = 0; i < n + m; i++) {
            if (i < n) {
                if (!visited[i]) {
                    cover.insert(i);
                }
            } else {
                if (visited[i]) {
                    cover.insert(i);
                }
            }
        }
        set<int> boys, girls;
        for (size_t i = 0; i < n + m; i++) {
            if (!cover.count(i)) {
                if (i < n) {
                    boys.insert(i + 1);
                } else {
                    girls.insert(i - n + 1);
                }
            }
        }
        cout << girls.size() + boys.size() << endl;
        cout << boys.size() << " " << girls.size() << endl;
        for (const auto &it : boys) {
            cout << it << " ";
        }
        cout << endl;
        for (const auto &it : girls) {
            cout << it << " ";
        }
        cout << endl;
    }
    return 0;
}