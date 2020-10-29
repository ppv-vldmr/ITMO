//
//  main.cpp
//  6
//
//  Created by Владимир on 18.10.2020.
//

#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>
#include <set>
#include <algorithm>

using namespace std;

vector<pair<set<int>, set<int>>> comp;

vector<vector<int>> graph, graph_rev;
vector<bool> visited;
vector<int> order, color;
set<pair<int, int>> edges;

void dfs(int v) {
    visited[v] = true;
    for (int i = 0; i < graph[v].size(); i++) {
        int to = graph[v][i];
        if (!visited[to]) {
            dfs(to);
        }
    }
    order.push_back(v);
}

void dfs2(int v, int cnt) {
    visited[v] = true;
    color[v] = cnt;
    for (int i = 0; i < graph_rev[v].size(); i++) {
        int to = graph_rev[v][i];
        if (!visited[to]) {
            dfs2(to, cnt);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    int n, m, v;
    cin >> n >> m;
    graph.resize(n);
    graph_rev.resize(n);
    color.resize(n);
    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        graph[a - 1].push_back(b - 1);
        graph_rev[b - 1].push_back(a - 1);
    }
    visited.resize(n, false);
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            dfs(i);
        }
    }
    reverse(order.begin(), order.end());
    int cnt = 0;
    visited.clear();
    visited.resize(n, false);
    for (int i = 0; i < n; ++i) {
        v = order[i];
        if (!visited[v]) {
            dfs2(v, cnt++);
        }
    }
    
    vector<vector<bool>> g_edge(cnt,vector<bool>(cnt,false));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < graph[i].size(); ++j) {
            int to = graph[i][j];
            if (color[i] != color[to]) {
                g_edge[color[i]][color[to]] = true;
            }
        }
    }
    
    int ans = 0;
    for (int i = 0; i < cnt; ++i) {
        for (int j = 0; j < cnt; ++j) {
            if (i == j) {
                continue;
            }
            if (g_edge[i][j]) {
                ++ans;
            }
        }
    }

    cout << ans;
    return 0;
}
