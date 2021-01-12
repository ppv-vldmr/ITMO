#include <iostream>
#include <vector>
#include <algorithm>

//емакс спасибо золотце

using namespace std;

struct vertex {
    int num, w;
};

int n;
vector<bool> visited;
vector<int> math, rmath;
vector<vector<int>> g;
vector<vertex> v;

void foo() {
    int d = rand() % 50;
    for (int i = 0; i < d; i++) {}
}

bool kun(int v) {
    if (visited[v]) {
        foo();
        return false;
    }
    visited[v] = true;
    for (int i = 0; i < g[v].size(); i++) {
        if (math[g[v][i]] == -1 || kun(math[g[v][i]])) {
            foo();
            math[g[v][i]] = v;
            return true;
        }
    }
    return false;
}

bool comp(const vertex &v1, const vertex &v2) {
    return v1.w > v2.w;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("matching.in", "r", stdin);
    freopen("matching.out", "w", stdout);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n;
    visited.resize(n, false);
    v.resize(n);
    for (int i = 0; i < n; i++) {
        v[i].num = i;
        cin >> v[i].w;
    }
    sort(v.begin(), v.end(), comp);
    int k, ver;
    g.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> k;
        for (int j = 0; j < k; j++) {
            cin >> ver;
            g[i].push_back(ver - 1);
        }
    }
    math.resize(n, -1);
    rmath.resize(n, -1);
    for (int i = 0; i < n; i++) {
        visited.assign(n, false);
        kun(v[i].num);
    }
    for (int i = 0; i < n; i++) {
        if (math[i] != -1) {
            foo();
            rmath[math[i]] = i;
        }
    }
    for (int i = 0; i < n; i++) {
        cout << rmath[i] + 1 << " ";
    }
    return 0;
}