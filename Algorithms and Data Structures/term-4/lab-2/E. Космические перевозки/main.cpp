#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <iomanip>
#include <unordered_map>

using namespace std;

int const INF = INT_MAX;

struct vert
{
    int u;
    int t;
};

struct edge
{
    vert a;
    vert b;
    int f;
    int c;
};

int l, r;
int n, m, k;

void add_edges(vector<vector<vector<int>>> &graph, vector<edge> &edges, vert a, vert b, int c)
{
graph[a.u][a.t].push_back((int) edges.size());
edges.push_back({a, b, 0, c});
graph[b.u][b.t].push_back((int) edges.size());
edges.push_back({b, a, 0, 0});
}

int shortest_path(int s, int t, vector<vector<int>> &g)
{
    vector<int> d(g.size(), INF);
    d[s] = 0;

    queue<int> q;
    q.push(s);
    while (!q.empty()) {
        auto u = q.front();
        q.pop();
        for (int v : g[u]) {
            if (d[v] == INF) {
                d[v] = d[u] + 1;
                q.push(v);
            }
        }
    }
    return d[t];
}

bool bfs(vector<vector<vector<int>>> &graph, vector<edge> &edges,
        vert s, vert t, vector<vector<int>> &d)
{
for (auto &vec : d) {
for (int &j : vec) {
j = -1;
};
}
d[s.u][s.t] = 0;

queue<vert> q;
q.push(s);

while (!q.empty()) {
vert u = q.front();
q.pop();

for (auto e : graph[u.u][u.t]) {
auto[_, v, f, c] = edges[e];
if (f < c && d[v.u][v.t] == -1) {
d[v.u][v.t] = d[u.u][u.t] + 1;
q.push(v);
}
}
}
return d[t.u][t.t] != -1;
}

int dfs(vector<vector<vector<int>>> &graph, vector<edge> &edges,
        vert u, vert t, int min_c, vector<vector<int>> &p, vector<vector<int>> &d)
{
if (((u.u == t.u) && (u.t == t.t)) || min_c == 0) {
return min_c;
}
for (; p[u.u][u.t] < graph[u.u][u.t].size(); p[u.u][u.t]++) {
int e = graph[u.u][u.t][p[u.u][u.t]];
auto [_, v, f, c] = edges[e];
if (d[v.u][v.t] != d[u.u][u.t] + 1) {
continue;
}

int pushed = dfs(graph, edges, v, t, min(min_c, c - f), p, d);
if (pushed) {
edges[e].f += pushed;
edges[e ^ 1].f -= pushed;
return pushed;
}
}
return 0;
}

int max_flow(vector<vector<vector<int>>> &graph, vector<edge> &graph_edges,
        vector<vector<vector<int>>> &new_graph, vector<edge> &new_edges,
        unordered_map<int, int> &num, vector<vector<int>> &g,
int i, int s, int t)
{
for (int u = 0; u < n; u++) {
for (int v : g[u]) {
for (int t = 0; t < l + i - 1; t++) {
add_edges(new_graph, new_edges, {u, t}, {v, t + 1}, 1);
add_edges(new_graph, new_edges, {v, t}, {u, t + 1}, 1);
num[new_edges.size() - 4] = (int) new_edges.size() - 2;
num[new_edges.size() - 2] = (int) new_edges.size() - 4;

}
}
}

for (int u = 0; u < n; u++) {
for (int t = 0; t < l + i - 1; t++) {
add_edges(new_graph, new_edges, {u, t}, {u, t + 1}, INF);
}
}

vector<vector<int>> p(new_graph.size(), vector<int> (new_graph[0].size()));
vector<vector<int>> d(new_graph.size(), vector<int> (new_graph[0].size()));
int ans = 0;
while (bfs(new_graph, new_edges, {s, 0}, {t, l + i - 1}, d)) {
for (auto &vec : p) {
for (int &j : vec) {
j = 0;
};
}
while (int cur_flow = dfs(new_graph, new_edges, {s, 0}, {t, l + i - 1}, INF, p, d)) {
ans += cur_flow;
}
}
return ans;
}

void set_one(vert a, vector<edge> &edges)
{

    for (auto & e : edges) {
        auto [u, v, f, c] = e;
        if ((a.u == u.u) && (a.t == u.t) && (v.u == a.u) && (v.t == a.t + 1) && (c > 0) ) {
            e.f += 1;
            return;
        }
    }
}

void create_path(vert u, vector<vert> &path, vector<vector<vector<int>>> &graph,
vector<edge> &edges, unordered_map<int, int> &num, int t)
{
path.push_back(u);
if (u.u == t) return;
for (auto e : graph[u.u][u.t]) {
auto [_, v, f, c] = edges[e];
if (f == 0 || c == 0) {
continue;
}
if (f == 1 && num.count(e) && edges[num[e]].f == 1) {
edges[e].f = 0;
edges[num[e]].f = 0;
set_one(edges[num[e]].a, edges);
create_path(edges[num[e]].b, path, graph, edges, num, t);
break;
}
edges[e].f -= 1;
edges[e + 1].f -= 1;
create_path(v, path, graph, edges, num, t);
break;
}
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int s, t;
    cin >> n >> m >> k >> s >> t;
    s--; t--;

    vector<vector<int>> dg((size_t) n);
    vector<vector<int>> g((size_t) n);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        g[u].push_back(v);
        dg[u].push_back(v);
        dg[v].push_back(u);
    }

    l = shortest_path(s, t, dg);
    r = l + k;

    vector<vector<vector<int>>> graph((size_t) n, vector<vector<int>>((size_t) r));
    vector<edge> edges;

    for (int u = 0; u < n; u++) {
        for (int v : g[u]) {
            for (int t = 0; t < r - 1; t++) {
                add_edges(graph, edges, {u, t}, {v, t + 1}, 1);
            }
        }
    }

    for (int u = 0; u < n; u++) {
        for (int t = 0; t < r - 1; t++) {
            add_edges(graph, edges, {u, t}, {u, t + 1}, INF);
        }
    }

    vector<vector<vert>> paths;
    int m = 0;

    for (int i = 0; i <= k; i++) {

        vector<vector<vector<int>>> new_graph ((size_t) n, vector<vector<int>> ((size_t) l + i));
        vector<edge> new_edges;
        unordered_map<int, int> num;

        int f = max_flow(graph, edges, new_graph, new_edges, num, g, i, s, t);
        if (f >= k) {
            for (int j = 0; j < k; j++) {
                vector<vert> path;
                create_path({s, 0}, path, new_graph, new_edges, num, t);
                paths.push_back(path);

                m = max(m, (int) path.size());
            }

            vector<vector<pair<int, int>>> ans;
            for (int x = 1; x < m; x++) {
                vector<pair<int, int>> res;

                for (int y = 0; y < k; y++) {
                    if (paths[y].size() <= x) {
                        continue;
                    }
                    if (paths[y][x].u == paths[y][x - 1].u) {
                        continue;
                    }
                    res.emplace_back(y + 1, paths[y][x].u + 1);
                }

                ans.push_back(res);
            }

            cout << ans.size() << '\n';
            for (const auto & res : ans) {
                cout << res.size() << "  ";
                for (auto [k,  ver] : res) {
                    cout << k << ' ' << ver << "  ";
                }
                cout << '\n';
            }
            cout << '\n';
            return 0;
        }
    }

    return 0;
}
