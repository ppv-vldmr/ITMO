#include <iostream>
#include <vector>
#include <set>
#include <queue>
#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <ctime>

using namespace std;

class Edge {
public:
    int u, v, color;
    Edge(int u, int v, int c) : u(u), v(v), color(c) {};
};

void wuff() {
    int d = rand() % 100;
//    cout << "--- " << d << endl;
    for (int i = 0; i < d; i++) {}
}

int n, m;
vector<Edge> edges;
vector<int> bp;
vector<vector<int>> g;
set<int> ans;


int get(int v, vector<int>& p) {
    if (v == p[v]) {
        return v;
    } else {
        return p[v] = get(p[v], p);
    }
}

void unite(int x, int y, vector<int>& p) {
    x = get(x, p);
    y = get(y, p);
    if (x != y) {
        wuff();
        p[x] = y;
    }
}

set<int> bfs(int start, vector<bool>& gic, vector<vector<int>>& g) {
    int n = g.size();
    vector<bool> used(n);
    vector<int> p(n);
    queue<int> q;
    set<int> result;

    used[start] = true;
    p[start] = -1;
    q.push(start);
    int res = -1;
    bool f = false;

    while (!q.empty() && !f) {
        int v = q.front();
        q.pop();
        for (int i = 0; i < g[v].size(); i++) {
            int u = g[v][i];

            if (!used[u]) {
                used[u] = true;
                q.push(u);
                p[u] = v;
            }

            if (gic[u]) {
                f = true;
                res = u;
                break;
            }
        }
    }

    for (int v = res; v != -1; v = p[v]) {
        result.insert(v);
    }
    return result;
}

int main() {
    freopen("rainbow.in", "r", stdin);
    freopen("rainbow.out", "w", stdout);
    srand(time(nullptr));
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        bp.push_back(i);
    }
    for (int i = 0; i < m; ++i) {
        int u, v, c;
        cin >> u >> v >> c;
        u--;
        v--;
        c--;
        edges.emplace_back(u, v, c);
    }
    ans.insert(0);
    bool flag = false;
    while (!flag) {
        vector<int> indep, dep;
        vector<bool> used(100, false);
        auto s = ans.begin();
        for (int i = 0; i < m; i++) {
            if (s != ans.end() && i == *s) {
                wuff();
                indep.push_back(i);
                used[edges[i].color] = true;
                s++;
            } else {
                wuff();
                dep.push_back(i);
            }
        }
        g = vector<vector<int>>(m + 1);
        vector<bool> intersect(m, false);
        for (int x : dep) {
            if (!used[edges[x].color]) {
                wuff();
                g[m].push_back(x);
            }
            for (int y : indep) {
                if (!used[edges[x].color] || edges[y].color == edges[x].color) {
                    wuff();
                    g[y].push_back(x);
                }
            }
        }
        for (int i = 0; i < indep.size(); i++) {
            int y = indep[i];
            vector<int> p = vector<int>(bp.cbegin(), bp.cend());
            for (int ny : indep) {
                if (ny != y) {
                    wuff();
                    unite(edges[ny].v, edges[ny].u, p);
                }
            }
            for (int x : dep) {
                if (get(edges[x].v, p) != get(edges[x].u, p)) {
                    wuff();
                    g[x].push_back(y);
                }
            }
            unite(edges[y].v, edges[y].u, p);
            for (int x : dep) {
                if (get(edges[x].v, p) != get(edges[x].u, p)) {
                    wuff();
                    intersect[x] = true;
                }
            }
        }
        set<int> path = bfs(m, intersect, g);
        path.erase(m);
        if (path.empty()) {
            wuff();
            flag = true;
        } else {
            vector<int> mp(ans.size() + path.size());
            wuff();
            auto it = set_symmetric_difference(ans.begin(), ans.end(), path.begin(),path.end(), mp.begin());
            ans = set<int>(mp.begin(), it);
        }
    }
    cout << ans.size() << "\n";
    for (int i : ans) {
        cout << i + 1 << " ";
    }
    return 0;
}