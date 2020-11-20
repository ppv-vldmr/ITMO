//
//  main.cpp
//  4
//
//  Created by Владимир on 14.11.2020.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

long long inf = INT64_MAX / 3;

struct edge {
    int to;
    int w;
    edge(int b, int c) {
        to = b;
        w = c;
    }
};

vector<vector<edge>> graph;
vector<vector<long long>> d;


int main() {
    ios_base::sync_with_stdio(false);
    int n, m, k_step, s;
    cin >> n >> m >> k_step >> s;
    s--;
    graph.resize(n);
    d.resize(k_step + 1, vector<long long>(n, inf));
    d[0][s] = 0;
    int a, b;
    int c;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        graph[a - 1].push_back(edge(b - 1, c));
    }
    for (int k = 0; k < k_step; k++) {
        for (int v = 0; v < n; v++) {
            for (int j = 0; j < graph[v].size(); j++) {
                if (d[k][v] == inf) {
                    continue;
                }
                int from = v;
                int to = graph[v][j].to;
                int cost = graph[v][j].w;
                d[k + 1][to] = min(d[k + 1][to], d[k][from] + cost);
            }
        }
    }
    for (long long i = 0; i < n; ++i) {
        if (d[k_step][i] == inf) {
            cout << -1 << endl;
        } else {
            cout << d[k_step][i] << endl;
        }
    }
    return 0;
}
