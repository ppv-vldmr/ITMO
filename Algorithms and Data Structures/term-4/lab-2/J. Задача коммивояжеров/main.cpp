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
    ll c, from, to, num, revNum, f, cost;

    Edge() = default;

    Edge(const Edge &other) = default;

    Edge &operator=(const Edge &other) = default;

    Edge(ll c, ll f, ll to, ll num, ll cost) : c(c), f(f), to(to), num(num), cost(cost)
    {}
};

pair<pair<ll, bool>, vector<ll>>
dijkstra(const vector<vector<ll>> &g, const ll s, const ll &t, const vector<Edge> &edgesList)
{
    set<pair<ll, ll>> q;
    vector<ll> d(g.size(), INF);
    vector<pair<ll, ll>> parent(g.size());

    d[s] = 0;
    parent[s] = {-1, -1};
    q.insert({0, s});

    while (!q.empty())
    {
        ll v = (*q.begin()).y;
        q.erase(q.begin());

        for (const ll it : g[v])
        {
            Edge e = edgesList.at(it);
            ll to = e.to;
            ll len = e.cost;

            if (d.at(v) + len < d.at(to) && e.f < e.c)
            {
                q.erase({d[to], to});
                d.at(to) = d.at(v) + len;
                q.insert({d.at(to), to});
                parent.at(to) = {v, it};
            }
        }
    }

    bool hasPath = (d.at(t) != INF);
    if (!hasPath)
    {
        return {{0, hasPath}, vector<ll>()};
    }

    vector<ll> edgesPath;
    pair<ll, ll> v = parent[t];
    while (v.first != -1)
    {
        edgesPath.push_back(v.second);
        v = parent.at(v.first);
    }
    reverse(edgesPath.begin(), edgesPath.end());
    ll minCapacity = INF;
    for (const ll it : edgesPath)
    {
        minCapacity = min(minCapacity, edgesList[it].c - edgesList[it].f);
    }
    return {{minCapacity, hasPath}, edgesPath};
}

void minCostFlow(vector<vector<ll>> &g, vector<Edge> &edgesList, const ll s, const ll t)
{
    while (true)
    {
        pair<pair<ll, bool>, vector<ll>> res = dijkstra(g, s, t, edgesList);
        ll minCapacity = res.first.first;
        bool hasPath = res.first.second;
        if (!hasPath)
        {
            break;
        }
        vector<ll> edgesInPath = res.second;

        for (const ll eInd : edgesInPath)
        {
            Edge e = edgesList.at(eInd);

            edgesList.at(eInd).f += minCapacity;
            edgesList.at(e.revNum).f -= minCapacity;
        }
    }
}

pair<vector<vector<ll>>,
vector<Edge>> createGraph(size_t n, const vector<ll> &s,
const vector<vector<ll>> &d, const vector<vector<ll>> &a)
{
vector<vector<ll>>g(2 * n);
vector<Edge> edgesList;
for (size_t i = 0; i < n; i++)
{
for (size_t j = n; j < 2 * n; j++)
{
Edge e;
e.cost = (i == j - n ? s[i] : d[i][j - n]);
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

g.push_back(vector<ll>());
ll st = g.size() - 1;

for (size_t i = 0; i < n; i++)
{
Edge e;
e.from = st;
e.to = i;
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

g.push_back(vector<ll>());
ll t = g.size() - 1;

for (size_t i = n; i < 2 * n; i++)
{
Edge e;
e.from = i;
e.to = t;
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

return {g, edgesList};
}

int main()
{
    size_t n, m;
    cin >> n >> m;

    vector<ll> s(n);
    vector<vector<ll>> d(n, vector<ll>(n, INF));
    vector<vector<ll>> a(n, vector<ll>(n, INF));

    for (size_t i = 0; i < n; i++)
    {
        d[i][i] = 0;
    }

    for (size_t i = 0; i < n; i++)
    {
        cin >> s[i];
    }

    for (size_t i = 0; i < m; i++)
    {
        int u, v, c;
        cin >> u >> v >> c;
        u--, v--;

        a[u][v] = c;
        d[u][v] = c;
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
            }
        }
    }

    auto gr = createGraph(n, s, d, a);
    auto g = gr.first;

    auto edgesList = gr.second;
    minCostFlow(g, edgesList, g.size() - 2, g.size() - 1);
    ll ans = 0;
    for (size_t i = 0; i < edgesList.size(); i += 2)
    {
        ans += (edgesList.at(i).f * edgesList.at(i).cost);
    }
    cout << ans;
}