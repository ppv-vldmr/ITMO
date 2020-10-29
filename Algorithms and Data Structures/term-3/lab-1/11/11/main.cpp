//
//  main.cpp
//  11
//
//  Created by Владимир on 23.10.2020.
//
#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 2000000100;

class Edge {
public:
    int in, to, w;
    Edge() {}
    Edge(int a, int b, int c): in(a), to(b), w(c) {}
};

int n, m;

vector<Edge> all_edges;
vector<vector<int>> g, gr;
vector<int> res, num, min_e;
vector<bool> used, color;

void top_sort(int v) {
    color[v] = true;
    for (int i = 0; i < g[v].size(); i++) {
        int to = g[v][i];
        if (!color[to]) {
            top_sort(to);
        }
    }
    res.push_back(v);
}

void cond(int v, int numb) {
    color[v] = true;
    num[v] = numb;
    for (int i = 0; i < gr[v].size(); i++) {
        int to = gr[v][i];
        if (!color[to]) {
            cond(to, numb);
        }
    }
}


void cond_g(const vector<Edge>& edges, int cnt) {
    num.clear();
    num.resize(n, 0);
    g.clear();
    g.resize(n);
    gr.clear();
    gr.resize(n);
    res.clear();
    for (int i = 0; i < edges.size(); i++) {
        g[edges[i].in].push_back(edges[i].to);
        gr[edges[i].to].push_back(edges[i].in);
    }
    color.clear();
    color.resize(n, 0);
    for (int i = 0; i < cnt; i++) {
        if (!color[i]) {
            top_sort(i);
        }
    }
    reverse(res.begin(), res.end());
    color.clear();
    color.resize(n, 0);
    int cur = 0;
    for (int i = 0; i < cnt; i++) {
        if (color[res[i]] == 0) {
            cond(res[i], cur);
            cur++;
        }
    }
}

void dfs(int v, const vector<Edge>& edges) {
    used[v] = 1;
    for (int i = 0; i < edges.size(); i++) {
        if (edges[i].in == v && (!used[edges[i].to])) {
            dfs(edges[i].to, edges);
        }
    }
}


long long chinese(int root, vector<Edge> edges, int cnt) {
    long long res = 0;
    min_e.clear();
    min_e.resize(cnt, INF);
    for (int i = 0; i < edges.size(); i++) {
        if (min_e[edges[i].to] > edges[i].w) {
            min_e[edges[i].to] = edges[i].w;
        }
    }
    for (int i = 0; i < cnt; i++) {
        if (i != root && min_e[i] != INF)
            res += min_e[i];
    }
    vector<Edge> min_edges;
    for (int i = 0; i < edges.size(); i++) {
        edges[i].w -= min_e[edges[i].to];
        if (edges[i].w == 0) {
            min_edges.push_back(edges[i]);
        }
    }
    used.clear();
    used.resize(n, 0);
    dfs(root, min_edges);
    bool mst = true;
    for (int i = 0; i < min_edges.size(); i++) {
        if ((!used[min_edges[i].in]) || (!used[min_edges[i].to])) {
            mst = false;
        }
    }
    if (mst) {
        return res;
    }
    int new_cnt = 0;
    cond_g(min_edges, cnt);
    vector<Edge> new_edges;
    for (int i = 0; i < edges.size(); i++) {
        if (num[edges[i].in] != num[edges[i].to]) {
            new_cnt = max(new_cnt, max(num[edges[i].in], num[edges[i].to]));
            new_edges.push_back(Edge(num[edges[i].in], num[edges[i].to], edges[i].w));
        }
    }
    return res + chinese(num[root], new_edges, new_cnt + 1);
}

int main() {
    int a, b, c;
    cin >> n >> m;
    g.resize(n);
    gr.resize(n);
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        all_edges.push_back(Edge(a - 1, b - 1, c));
    }
    used.resize(n, 0);
    dfs(0, all_edges);
    for (int i = 0; i < n; ++i) {
        if (!used[i]) {
            cout << "NO";
            return 0;
        }
    }
    cout << "YES" << '\n' << chinese(0, all_edges, n);
    return 0;
}
