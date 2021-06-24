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
    q.insert({s, 0});

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

        for (const int eInd : edgesInPath)
        {
            Edge e = edgesList.at(eInd);

            edgesList.at(eInd).f += minCapacity;
            edgesList.at(e.revNum).f -= minCapacity;
        }
    }
}

int main()
{
    size_t n, m;
    cin >> n >> m;

    vector<vector<ll>> g(n);
    vector<Edge> edgesList;

    for (size_t i = 0; i < m; i++)
    {
        ll u, v, c, cost;
        cin >> u >> v >> c >> cost;
        u--, v--;


        Edge e = Edge(c, 0, v, edgesList.size(), cost);
        e.revNum = edgesList.size() + 1;
        e.from = u;
        edgesList.push_back(e);

        g.at(u).pb(e.num);

        Edge e1 = Edge(0, 0, u, edgesList.size(), -cost);
        e1.revNum = e.num;
        e1.from = v;
        edgesList.push_back(e1);

        g.at(v).pb(e1.num);
    }

    minCostFlow(g, edgesList, 0, n - 1);
    ll ans = 0;
    for (size_t i = 0; i < edgesList.size(); i += 2)
    {
        ans += (edgesList.at(i).f * edgesList.at(i).cost);
    }
    cout << ans;
}
