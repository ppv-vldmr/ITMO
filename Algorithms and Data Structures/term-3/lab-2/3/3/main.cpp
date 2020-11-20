//
//  main.cpp
//  3
//
//  Created by Владимир on 13.11.2020.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct edge {
    int a, b, cost;
    edge(int a, int b, int cost): a(a), b(b), cost(cost) {}
};

vector<edge> e;
long long INF = 100000000;
int inf = 10000;


int main() {
    ios_base::sync_with_stdio(false);
    int n, m = 0;
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0, w; j < n; j++) {
            cin >> w;
            if (w < inf) {
                e.push_back(edge(i, j, w));
                m++;
            }
        }
    }
    vector<long long> d(n), p(n, -1);
    long long x;
    for (long long i = 0; i < n; i++) {
        x = -1;
        for (long long j = 0; j < m; j++) {
            if (d[e[j].b] > d[e[j].a] + e[j].cost) {
                d[e[j].b] = max(-INF, d[e[j].a] + e[j].cost);
                p[e[j].b] = e[j].a;
                x = e[j].b;
            }
        }
    }
    if (x == -1) {
        cout << "NO";
    } else {
        long long y = x;
        for (long long i = 0; i < n; i++) {
            y = p[y];
        }
        vector<int> path;
        for (long long cur = y; ; cur = p[cur]) {
            path.push_back(cur);
            if (cur == y && path.size() > 1) {
                break;
            }
        }
        reverse(path.begin(), path.end());
        cout << "YES" << endl;
        cout << path.size() - 1 << endl;
        for (int i = 1; i < path.size(); i++) {
            cout << path[i] + 1 << ' ';
        }
    }
    return 0;
}
