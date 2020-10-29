//
//  main.cpp
//  10
//
//  Created by Владимир on 21.10.2020.
//
#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>
#include <algorithm>

using namespace std;

struct Edge {
    int a, b, w;
    
    Edge() {}
    
    Edge(int a, int b, int w) {
        this->a = a;
        this->b = b;
        this->w = w;
    }

    bool operator<(const Edge& edges) const {
        return w < edges.w;
    }
};

vector<int> dsu;
vector<Edge> edges;

int get(int v) {
    return (v == dsu[v]) ? v : (dsu[v] = get(dsu[v]));
}

void unite(int a, int b) {
    a = get(a);
    b = get(b);
    if (a == b) {
        return;
    }
    dsu[a] = b;
}

               
int main() {
    ios_base::sync_with_stdio(false);
    long long n, m;
    cin >> n >> m;
    edges.resize(m);
    dsu.resize(n);
    for (int i = 0; i < n; i++) {
        dsu[i] = i;
    }
    for (int i = 0; i < m; i++) {
        cin >> edges[i].a >> edges[i].b >> edges[i].w;
        edges[i].a--;
        edges[i].b--;
    }
    sort(edges.begin(), edges.end());
    long long ans = 0;
    for (int i = 0; i < m; i++)
        if (get(edges[i].a) != get(edges[i].b)) {
            unite(edges[i].a, edges[i].b);
            ans += edges[i].w;
        }
    cout << ans << endl;
    return 0;
}



