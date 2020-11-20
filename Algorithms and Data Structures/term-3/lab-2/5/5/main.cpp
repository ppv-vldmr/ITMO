//
//  main.cpp
//  5
//
//  Created by Владимир on 17.11.2020.
//

#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long
 
using namespace std;
 
const ll INF = 5000000000000000010;
 
struct edge {
    int to;
    ll w;
    edge(int b, ll c) {
        to = b;
        w = c;
    }
};
 
int n, m, s;
vector<vector<edge>> graph;
vector<ll> d;
vector<bool> no_path, used;
 
void dfs(int v) {
    used[v] = true;
    no_path[v] = true;
    for (int i = 0; i < graph[v].size(); i++) {
        if (!used[graph[v][i].to])
            dfs(graph[v][i].to);
    }
 
}
 
void ford(int st, bool t) {
    for (int i = 0; i < 2 * n; i++) {
        for (int k = 0; k < n; k++)
            for (int j = 0; j < graph[k].size(); j++) {
                int in = k, to = graph[k][j].to;
                ll  w = graph[k][j].w;
                if (d[in] < INF) {
                    if (d[to] > d[in] + w) {
                        d[to] = max(-INF, d[in] + w);
                        if (t == true)
                            no_path[to] = true;
                    }
                }
            }
    }
 
}
 
int main() {
    std::ios::sync_with_stdio(false);
    cin >> n >> m >> s;
    --s;
    graph.resize(n);
    d.resize(n, INF);
    d[s] = 0;
    no_path.resize(n, false);
    int a, b;
    ll c;
    for (int i = 0; i < m; ++i) {
        cin >> a >> b >> c;
        --a;
        --b;
        graph[a].push_back(edge(b, c));
    }
    ford(s, false);
    ford(s, true);
    for (int i = 0; i < n; ++i) {
        if (no_path[i]) {
            used.resize(m, false);
            dfs(i);
        }
    }
    for (int i = 0; i < n; i++) {
        if (d[i] == INF) {
            cout << "*" << endl;
        }
        else if (no_path[i]) {
            cout << "-" << endl;
        }
        else {
            cout << d[i] << endl;
        }
    }
    return 0;
}
