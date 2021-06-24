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

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;
typedef unsigned long long ull;
typedef unsigned int uint;

const ll N = 1e6 + 10;
const ll INF = 1e9 + 100;
const ll NO_EDGE = 100000;
const ll M = 6e3 + 100;
const ull prime = 31;

#define x first
#define y second
#define pb push_back

struct Edge
{
    ll c, from, to, num, revNum;
    ll f;
    bool isReal;

    Edge() = default;

    Edge(const Edge &other) = default;

    Edge &operator=(const Edge &other) = default;

    Edge(ll c, ll f, ll to, ll num) : c(c), f(f), to(to), num(num)
    {}
};

ll dfs(ll v, ll minC, vector<vector<ll>> &g, vector<bool>& used, ll t, vector<Edge> &edgesList)
{
    if (v == t)
    {
        return minC;
    }

    used[v] = true;


    for (size_t i = 0; i < g[v].size(); i++)
    {
        Edge e = edgesList[g[v][i]];
        if (!used[e.to] && e.f < e.c)
        {
            ll delta = dfs(e.to, min(minC, e.c - e.f), g, used, t, edgesList);
            if (delta > 0)
            {
                edgesList[g[v][i]].f += delta;
                edgesList[edgesList[g[v][i]].revNum].f -= delta;
                return delta;
            }
        }
    }

    return 0;
}

bool pathFound = false;
void findPath(ll v, ll t, vector<vector<ll>> &g, vector<Edge> &edgesList, vector<ll> &path)
{

    if (v == t)
    {
        pathFound = true;
        return;
    }


    for (size_t i = 0; i < g[v].size(); i++)
    {
        Edge e = edgesList[g[v][i]];
        if (!e.isReal) continue;
        if (!pathFound && e.f == 1)
        {
            edgesList[g[v][i]].f = 0;
            path.pb(e.to);
            findPath(e.to, t, g, edgesList, path);
        }
    }
}

int main()
{

    size_t n, m, s, t;
    cin >> n >> m >> s >> t;

    s--, t--;

    vector<vector<ll>> g(n);
    vector<Edge> edgesList;

    for (size_t i = 0; i < m; i++)
    {
        ll u, v;
        cin >> u >> v;

        u--, v--;

        if (u == v) continue;

        Edge e = Edge(1, 0, v, edgesList.size());
        e.revNum = edgesList.size() + 1;
        e.from = u;
        e.isReal = true;
        edgesList.pb(e);


        g[u].pb(e.num);

        Edge w = Edge(0, 0, u, edgesList.size());
        w.revNum = e.num;
        w.from = v;
        edgesList.pb(w);


        g[v].pb(w.num);
    }

    vector<bool> used(n);

    while (dfs(s, INF, g, used, t, edgesList) > 0)
    {
        used.clear();
        used.resize(n);
    }

    ll maxFlow = 0;

    for (const auto it : edgesList)
    {
        if (it.isReal && it.from == s)
        {
            maxFlow += it.f;
        }
    }

    if (maxFlow < 2)
    {
        return cout << "NO", 0;
    }

    vector<ll> p1, p2;
    p1.pb(s);
    p2.pb(s);

    findPath(s, t, g, edgesList, p1);
    pathFound = false;
    findPath(s, t, g, edgesList, p2);

    if (p1.back() != t || p2.back() != t)
    {
        return cout << "NO", 0;
    }

    cout << "YES" << endl;

    for (const ll it : p1)
    {
        cout << it + 1 << " ";
    }

    cout << endl;

    for (const ll it : p2)
    {
        cout << it + 1 << " ";
    }

    cout << endl;

}