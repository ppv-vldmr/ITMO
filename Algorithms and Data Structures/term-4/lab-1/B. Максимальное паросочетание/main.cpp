#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <unordered_set>
#include <map>

using namespace std;

struct vertex
{
    int ind;
    int w;
};

bool dfs(int i, vector<vector<int>> &edges, vector<int> &r, vector<bool> &used)
{
    if (used[i]) {
        return false;
    }
    used[i] = true;

    for (int j : edges[i]) {
        if (r[j] == -1 || dfs(r[j], edges, r, used)) {
            r[j] = i;
            return true;
        }
    }

    return false;
}

void dfs2(int i, bool direction, vector<pair<int, int>> &edges, unordered_set<int> &match,
          vector<int> &from_trans, vector<int> &to_trans,
          vector<bool> &from_used, vector<bool> &to_used)
{
    if (from_used[i]) {
        return;
    }
    from_used[i] = true;

    int to = from_trans[i];
    edges.emplace_back(direction ? i : to, direction ? to : i);

    to_used[to] = true;

    if (to_trans[to] != -1 && match.count(to_trans[to])) {
        dfs2(to_trans[to], direction, edges, match, from_trans, to_trans, from_used, to_used);
    }
}

void input_w(vector<vertex> &vert, vector<int> &w)
{
    for (int i = 0; i < vert.size(); i++) {
        cin >> w[i];
        vert[i] = {i, w[i]};
    }
}

void max_matching(vector<int> &r, vector<vector<int>> &edges, vector<vertex> &vert, vector<bool> &used)
{
    sort(vert.begin(), vert.end(), [] (vertex a, vertex b) { return a.w > b.w; });
    for (auto [j, w] : vert) {
        if (dfs(j, edges, r, used)) {
            fill(used.begin(), used.end(), false);
        }
    }
}

void fill_match(unordered_set<int> &l_match, vector<int> &r, vector<int> &from_deg, vector<int> &to_deg)
{
    for (int j = 0; j < r.size(); j++) {
        if (r[j] == -1) {
            continue;
        }
        to_deg[j]++;
        from_deg[r[j]]++;
        l_match.insert(r[j]);
    }
}

void fill_trans(vector<int> &trans, vector<int> &r)
{
    for (int i = 0; i < r.size(); i++) {
        if (r[i] == -1) {
            continue;
        }
        trans[r[i]] = i;
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    size_t n, m;
    int e;
    cin >> n >> m >> e;

    vector<vertex> l_vert(n);
    vector<int> l_w(n);
    input_w(l_vert, l_w);

    vector<vertex> r_vert(m);
    vector<int> r_w(m);
    input_w(r_vert, r_w);

    vector<vector<int>> l_edges(n);
    vector<vector<int>> r_edges(m);
    map<pair<int, int>, int> num;

    for (int i = 0; i < e; i++) {
        int u, v;
        cin >> u >> v;
        num[{u - 1, v - 1}] = i + 1;
        l_edges[u - 1].push_back(v - 1);
        r_edges[v - 1].push_back(u - 1);
    }

    vector<int> l_r(m, -1);
    vector<bool> used(n, false);
    max_matching(l_r, l_edges, l_vert, used);

    used.clear();

    vector<int> r_r(n, -1);
    used.assign(m, false);
    max_matching(r_r, r_edges, r_vert, used);

    vector<int> l_deg(n, 0);
    vector<int> r_deg(m, 0);

    unordered_set<int> l_match;
    fill_match(l_match, l_r, l_deg, r_deg);

    unordered_set<int> r_match;
    fill_match(r_match, r_r, r_deg, l_deg);

    vector<bool> l_used(n, false);
    vector<bool> r_used(m, false);

    vector<int> l_trans(n, -1);
    fill_trans(l_trans, l_r);

    vector<int> r_trans(m, -1);
    fill_trans(r_trans, r_r);

    vector<pair<int, int>> edges;

    // paths from L
    for (int i = 0; i < n; i++) {
        if (l_deg[i] == 1 && l_match.count(i)) {
            dfs2(i, true, edges, l_match, l_trans, r_trans, l_used, r_used);
        }
    }

    // paths from R
    for (int i = 0; i < m; i++) {
        if (r_deg[i] == 1 && r_match.count(i)) {
            dfs2(i, false, edges, r_match, r_trans, l_trans, r_used, l_used);
        }
    }

    // cycles
    for (int i = 0; i < n; i++) {
        if (!l_used[i] && l_deg[i] == 2) {
            dfs2(i, true, edges, l_match, l_trans, r_trans, l_used, r_used);
        }
    }

    int ans = 0;
    for (auto [u, v] : edges) {
        ans += l_w[u] + r_w[v];
    }

    cout << ans << '\n';
    cout << edges.size() << '\n';
    for (auto p : edges) {
        cout << num[p] << ' ';
    }
}
