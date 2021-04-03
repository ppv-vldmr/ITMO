#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>
#include <cmath>

using namespace std;

typedef long long ll;
typedef unsigned char byte;
typedef unsigned int ui;



ui n, m;
vector<bool> used;
vector<ui> math, rmath;
vector<vector<ui>> graph;


bool dfs_kun(ui v) {
    if (used[v]) return false;
    used[v] = true;
    for (ui i = 0; i < graph[v].size(); ++i) {
        ui to = graph[v][i];
        if (math[to] == -1 || dfs_kun(math[to])) {
            math[to] = v;
            return true;
        }
    }
    return false;
}


int main() {
    std::ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    used.resize(n, 0);
    graph.resize(n);
    math.resize(m, -1);
    rmath.resize(n, -1);
    ui k;
    for (ui i = 0; i < n; ++i) {
        cin >> k;
        while (k != 0) {
            graph[i].push_back(k - 1);
            cin >> k;
        }
    }
    for (ui i = 0; i < n; ++i) {
        used.assign(n, false);
        dfs_kun(i);
    }
    ui answer = 0;
    for (ui i = 0; i < m; i++) {
        if (math[i] != -1) {
            rmath[math[i]] = i;
            answer++;
        }
    }
    cout << answer << '\n';
    for (ui i = 0; i < n; i++) {
        if (rmath[i] != -1) {
            cout << i + 1 << ' ' << rmath[i] + 1<< '\n';
        }
    }
    return 0;
}