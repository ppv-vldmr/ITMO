//
// Created by Владимир on 24.06.2021.
//

#define _FORTIFY_SOURCE 0
#pragma GCC optimize("Ofast")
#pragma GCC optimize("no-stack-protector")
#pragma GCC optimize("unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,popcnt,abm,mmx,tune=native")
#pragma GCC optimize("fast-math")

#include <iostream>
#include <math.h>
#include <algorithm>
#include <iomanip>
#include <vector>
#include <set>
#include <map>
#include <deque>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <random>
#include <cassert>

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;
typedef unsigned long long ull;
typedef unsigned int uint;
typedef tuple<vector<ll>, vector<ll>, vector<ll>> tuple3v;

const ll N = 1e6 + 10;
const ll INF = 1e18 + 100;
const ll M = 3 * 1e3;

#define x first
#define y second
#define pb push_back

struct Edge
{
    int c, from, to, num, revNum, f, cost;

    Edge() = default;

    Edge(const Edge &other) = default;

    Edge &operator=(const Edge &other) = default;

    Edge(ll c, ll f, ll to, ll num, ll cost) : c(c), f(f), to(to), num(num), cost(cost)
    {}
};

pair<pair<ll, bool>, vector<int>>
dijkstra(const vector<vector<int>> &g, const int s, const int &t, const vector<Edge> &edgesList)
{
    set<pair<ll, ll>> q;
    vector<ll> d(g.size(), INF);
    vector<pair<int, int>> parent(g.size());

    d[s] = 0;
    parent[s] = {-1, -1};
    q.insert({0, s});

    while (!q.empty())
    {
        ll v = (*q.begin()).y;
        q.erase(q.begin());

        for (const int it : g[v])
        {
            Edge e = edgesList[it];
            ll to = e.to;
            ll len = e.cost;

            if (d[v] + len < d[to] && e.f < e.c)
            {
                q.erase({d[to], to});
                d[to] = d[v] + len;
                q.insert({d[to], to});
                parent[to] = {v, it};
            }
        }
    }

    bool hasPath = (d[t] != INF);
    if (!hasPath)
    {
        return {{0, hasPath}, vector<int>()};
    }

    vector<int> edgesPath;
    pair<int, int> v = parent[t];
    while (v.first != -1)
    {
        edgesPath.push_back(v.second);
        v = parent[v.first];
    }
    reverse(edgesPath.begin(), edgesPath.end());
    ll minCapacity = INF;
    for (const int it : edgesPath)
    {
        minCapacity = min(minCapacity, (ll)edgesList[it].c - edgesList[it].f);
    }
    return {{minCapacity, hasPath}, edgesPath};
}

void minCostFlow(vector<vector<int>> &g, vector<Edge> &edgesList, const int s, const int t)
{
    while (true)
    {
        pair<pair<ll, bool>, vector<int>> res = dijkstra(g, s, t, edgesList);
        ll minCapacity = res.first.first;
        bool hasPath = res.first.second;
        if (!hasPath)
        {
            break;
        }
        vector<int> edgesInPath = res.second;

        for (const ll eInd : edgesInPath)
        {
            Edge e = edgesList[eInd];

            edgesList[eInd].f += minCapacity;
            edgesList[e.revNum].f -= minCapacity;
        }
    }
}

pair<vector<vector<int>>,
vector<Edge>> createGraph(size_t n, const vector<vector<int>> &d)
{
vector<vector<int>>g(2 * n);
vector<Edge> edgesList;
for (size_t i = 0; i < n; i++)
{
for (size_t j = n; j < 2 * n; j++)
{
Edge e;
e.cost = d[i][j - n];
e.f = 0;
e.from = i;
e.to = j;
e.num = edgesList.size();
e.revNum = edgesList.size() + 1;
e.c = 1;

edgesList.push_back(e);
g[e.from].push_back(e.num);

Edge w;
w.c = 0;
w.f = 0;
w.from = e.to;
w.to = e.from;
w.num = e.revNum;
w.revNum = e.num;
w.cost = -e.cost;

edgesList.push_back(w);
g[w.from].push_back(w.num);
}
}

g.push_back(vector<int>());
int st = g.size() - 1;

for (size_t i = 0; i < n; i++)
{
Edge e;
e.from = st;
e.to = i;
//        e.c = INF;
e.c = 1;
e.cost = 0;
e.num = edgesList.size();
e.revNum = edgesList.size() + 1;
e.f = 0;

edgesList.push_back(e);
g[e.from].push_back(e.num);

Edge w;
w.c = 0;
w.f = 0;
w.from = e.to;
w.to = e.from;
w.num = e.revNum;
w.revNum = e.num;
w.cost = -e.cost;

edgesList.push_back(w);
g[w.from].push_back(w.num);
}

g.push_back(vector<int>());
int t = g.size() - 1;

for (size_t i = n; i < 2 * n; i++)
{
Edge e;
e.from = i;
e.to = t;
e.c = 1;
//        e.c = INF;
e.cost = 0;
e.num = edgesList.size();
e.revNum = edgesList.size() + 1;
e.f = 0;

edgesList.push_back(e);
g[e.from].push_back(e.num);

Edge w;
w.c = 0;
w.f = 0;
w.from = e.to;
w.to = e.from;
w.num = e.revNum;
w.revNum = e.num;
w.cost = -e.cost;

edgesList.push_back(w);
g[w.from].push_back(w.num);
}

return {g, edgesList};
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    size_t n;
    cin >> n;

//    vector<vector<ll>> g(n);
//    vector<Edge> edgesList;
    vector<vector<int>> a(n, vector<int>(n));

    for (size_t i = 0; i < n; i++)
    {
        for (size_t j = 0; j < n; j++)
        {
            cin >> a[i][j];
        }
    }
    auto gr = createGraph(n, a);
    auto g = gr.first;
    auto edgesList = gr.second;

    minCostFlow(g, edgesList, g.size() - 2, g.size() - 1);
    ll ans = 0;
    for (size_t i = 0; i < edgesList.size(); i += 2)
    {
        ans += (edgesList[i].f * edgesList[i].cost);
    }
    cout << ans << endl;

    for (size_t i = 0; i < edgesList.size(); i += 2)
    {
        auto e = edgesList[i];
        if (e.f == 1 && e.to != g.size() - 1 && e.from != g.size() - 2)
        {
            cout << e.from + 1 << " " << e.to + 1 - n << endl;
        }
    }
}
