//
//  main.cpp
//  4
//
//  Created by Владимир on 15.10.2020.
//
 
#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>
#include <set>
#include <map>
#include <algorithm>

using namespace std;

vector<vector<pair<int,int>>> graph;
vector<int> enter, ret, color;
set<int> bridges;
vector<bool> visited;

int t = 0;


void dfs2(int v, int c) {
    visited[v] = true;
    color[v] = c;
    for (size_t i = 0; i < graph[v].size(); i++) {
        int to = graph[v][i].first;
        if (!visited[to] && !bridges.count(graph[v][i].second)) {
            dfs2(to, c);
        }
    }
}


void dfs(int v, int p = -1) {
    visited[v] = 1;
    t++;
    enter[v] = t;
    ret[v] = t;
    for (long long i = 0; i < graph[v].size(); ++i) {
        int to = graph[v][i].first;
        if (visited[to]) {
            if (to != p) {
                ret[v] = min(ret[v], enter[to]);
            }
        } else {
            dfs(to, v);
            ret[v] = min(ret[v], ret[to]);
            if (enter[v] < ret[to]) {
                bridges.insert(graph[v][i].second);
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    graph.resize(n);
    color.resize(n, 0);
    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        graph[a - 1].push_back(make_pair(b - 1, i + 1));
        graph[b - 1].push_back(make_pair(a - 1, i + 1));
    }
    visited.resize(n, false);
    enter.resize(n);
    ret.resize(n);
    for (int i = 0; i < n; ++i)
        if (!visited[i])
            dfs(i);
    visited.clear();
    visited.resize(n, false);
    int cnt = 0;
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            dfs2(i, cnt++);
        }
    }
    cout << cnt << endl;
    for (int i = 0; i < n; ++i) {
        cout << color[i] + 1 << " ";
    }
    return 0;
}
