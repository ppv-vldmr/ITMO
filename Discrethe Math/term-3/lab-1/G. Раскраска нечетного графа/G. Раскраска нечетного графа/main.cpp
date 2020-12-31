//
//  main.cpp
//  G. Раскраска нечетного графа
//
//  Created by Владимир on 30.12.2020.
//

#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<bool> visited;
vector<int> color;

void dfs(int v) {
    if (visited[v]) {
        return;
    }
    visited[v] = 1;
    set<int> colors;
    for (int i = 0; i < graph[v].size(); i++) {
        if (color[graph[v][i]]) {
            colors.insert(color[graph[v][i]]);
        }
    }
    int c = 1;
    while (colors.find(c) != colors.end()) {
        c++;
    }
    color[v] = c;
    for (size_t i = 0; i < graph[v].size(); ++i) {
        dfs(graph[v][i]);
    }
}

int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n >> m;
    graph.resize(n);
    color.resize(n, 0);
    visited.resize(n, false);
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        graph[a - 1].push_back(b - 1);
        graph[b - 1].push_back(a - 1);
    }
    dfs(0);
    int cnt = 0;
    for (int i = 0; i < graph.size(); i++) {
        if (graph[i].size() > cnt) {
            cnt = graph[i].size();
        }
    }
    cnt += (cnt % 2 == 0);
    cout << cnt << endl;
    for (int i = 0; i < color.size(); i++) {
        cout << color[i] << endl;
    }
    return 0;
}
