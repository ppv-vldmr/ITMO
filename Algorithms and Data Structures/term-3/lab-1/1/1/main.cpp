#include <iostream>
#include <vector>

using namespace std;

vector <vector<int>> graph;
vector <bool> visited;
vector <int> ans;
vector <int> color;

bool has_cycle = 0;

void dfs(int v) {
    if (visited[v]) {
        return;
    }
    visited[v] = 1;
    for (size_t i = 0; i < graph[v].size(); ++i) {
        dfs(graph[v][i]);
    }
    ans.push_back(v + 1);
}

void find_cycle(int v) {
    color[v] = 1;
    for (size_t i = 0; i < graph[v].size(); ++i) {
        if (has_cycle) {
            break;
        }
        int to = graph[v][i];
        if (color[to] == 0) {
            find_cycle(to);
        } else if (color[to] == 1) {
            has_cycle = true;
            break;
        }
    }
    color[v] = 2;
}

int main() {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    graph.resize(n);
    visited.resize(n);
    color.resize(n);
    for (long i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        graph[a - 1].push_back(b - 1);
    }
    for (int i = 0; i < n; ++i) {
        if (color[i] != 2) {
            find_cycle(i);
        }
        if (has_cycle) {
            cout << "-1";
            return 0;
        }
    }
    for (int i = 0; i < n; ++i) {
        dfs(i);
    }
    for (long i = n - 1; i >= 0; --i) {
        cout << ans[i] << " ";
    }
    return 0;
}
