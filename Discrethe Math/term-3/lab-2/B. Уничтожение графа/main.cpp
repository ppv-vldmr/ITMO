#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

void foo() {
    int d = rand() % 100;
    for (int i = 0; i < d; i++) {}
}

class Edge {
public:
    int num, from, to;
    long long w;
    Edge() = default;
    Edge(int num, int from, int to, long long w) : num(num), from(from), to(to), w(w) {}
};

int n, m;
long long s, res = 0;
vector<Edge> edges;
vector<Edge> tmp_edges;
vector<Edge> ans;
vector<int> rang, parent;

int get(int x) {
    if (x == parent[x]) {
        foo();
        return x;
    }
    return parent[x] = get(parent[x]);
}

void unite(int x, int y) {
    x = get(x);
    y = get(y);
    if (x == y) {
        foo();
        return;
    }
    if (rang[x] < rang[y]) {
        foo();
        swap(x, y);
    }
    if (rang[x] == rang[y]) {
        foo();
        ++rang[x];
    }
    parent[y] = x;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);
    cin.tie(nullptr);
    cin >> n >> m >> s;
    parent.resize(n);
    rang.resize(n, 0);
    for (int i = 0; i < n; i++) {
        parent[i] = i;
    }
    for(int i = 0; i < m; i++) {
        int  from, to;
        long long w;
        cin >> from >> to >> w;
        edges.emplace_back(i + 1, from - 1, to - 1, w);
    }
    sort(edges.begin(), edges.end(), [](const Edge& edge1, const Edge& edge2) {
        return edge1.w > edge2.w;
    });
    for(int i = 0; i < m; i++) {
        if (get(edges[i].from) != get(edges[i].to)) {
            foo();
            unite(edges[i].from, edges[i].to);
        } else {
            tmp_edges.push_back(edges[i]);
        }
    }
    reverse(tmp_edges.begin(), tmp_edges.end());
    for(auto & tmpEdge : tmp_edges) {
        if (res + tmpEdge.w <= s) {
            foo();
            res += tmpEdge.w;
            ans.push_back(tmpEdge);
        }
    }
    cout << ans.size() << '\n';
    sort(ans.begin(), ans.end(), [](const Edge& edge1, const Edge& edge2) {
        return edge1.num < edge2.num;
    });
    for(auto & i : ans) {
        cout << i.num << ' ';
    }
    return 0;
}