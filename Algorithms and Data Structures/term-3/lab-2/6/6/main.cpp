//
//  main.cpp
//  6
//
//  Created by Владимир on 17.11.2020.
//

#include <iostream>
#include <algorithm>
#include <fstream>
#include <vector>
#include <set>
#include <string>
 
using namespace std;
 
const long long INF = INT64_MAX / 3;
#define ll long long
 
struct Edge {
    ll to, w;
    Edge(ll to, ll w) : to(to), w(w) {}
    Edge() :to(0), w(0) {}
};
 
ll n, m;
size_t min_num;
vector<vector<Edge>> graph;
vector<ll> d;
set<pair<ll, ll>> s;
 
int main() {
    std::ios::sync_with_stdio(false);
    cin >> n >> m;
    graph.resize(n);
    d.resize(n, INF);
    ll a, b, c;
    for (size_t i = 0; i < m; ++i) {
        cin >> a >> b >> c;
        graph[a - 1].push_back(Edge(b - 1, c));
        graph[b - 1].push_back(Edge(a - 1, c));
    }
    cin >> a >> b >> c;
    --a;
    --b;
    --c;
    d[a] = 0;
    s.insert(make_pair(d[a], a));
    while (!s.empty()) {
        min_num = s.begin()->second;
        s.erase(s.begin());
        for (size_t i = 0; i < graph[min_num].size(); ++i) {
            ll to = graph[min_num][i].to;
            ll len = graph[min_num][i].w;
            if (d[min_num] + len < d[to]) {
                s.erase(make_pair(d[to], to));
                d[to] = d[min_num] + len;
                s.insert(make_pair(d[to], to));
            }
        }
    }
    ll ab = d[b];
    ll ac = d[c];
    if (ab == INF || ac == INF) {
        cout << "-1";
        return 0;
    }
    ll bc;
    d.clear();
    d.resize(n, INF);
    d[b] = 0;
    s.insert(make_pair(d[b], b));
    while (!s.empty()) {
        min_num = s.begin()->second;
        s.erase(s.begin());
        for (size_t i = 0; i < graph[min_num].size(); ++i) {
            ll to = graph[min_num][i].to;
            ll len = graph[min_num][i].w;
            if (d[min_num] + len < d[to]) {
                s.erase(make_pair(d[to], to));
                d[to] = d[min_num] + len;
                s.insert(make_pair(d[to], to));
            }
        }
    }
    bc = d[c];
    if (bc == INF) {
        cout << "-1";
        return 0;
    }
    ll ans = min(ab + bc,min(bc + ac,ab + ac));
    cout << ans;
    return 0;
}
