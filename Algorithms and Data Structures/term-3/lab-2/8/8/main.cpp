//
//  main.cpp
//  8
//
//  Created by Владимир on 13.11.2020.
//
#include <iostream>
#include <vector>

using namespace std;

#define MAXN 100001

vector<vector<int>> gr(MAXN);
vector<int> top;
bool used[MAXN];

void topsort(int u) {
    used[u] = true;
    for (int v : gr[u]) {
        if (!used[v])
            topsort(v);
    }
    top.push_back(u);
}

vector<bool> wins(MAXN, true);

bool isWinVertex(int u) {
    for (int v : gr[u]) {
        if (!wins[v])
            return true;
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("game.in", "r", stdin);
    freopen("game.out", "w", stdout);
    int n, m, s;
    cin >> n >> m >> s;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        gr[u].push_back(v);
    }

    for (int i = 1; i <= n; i++) {
        topsort(i);
    }
        
    for (int u : top) {
        wins[u] = isWinVertex(u);
    }
        
    if (wins[s]) {
        cout << "First player wins";
    } else {
        cout << "Second player wins";
    }
    return 0;
}
