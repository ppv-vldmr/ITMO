//
//  main.cpp
//  3
//
//  Created by Владимир on 15.10.2020.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> g;
vector<int> tin, fup, ans;
vector<bool> used, cutpoints;
int timer;
 
void dfs (int v, int p = -1) {
    used[v] = true;
    tin[v] = timer++;
    fup[v] = timer++;
    int children = 0;
    for (int i = 0; i < g[v].size(); ++i) {
        int to = g[v][i];
        if (to == p) {
            continue;
        }
        if (used[to]) {
            fup[v] = min(fup[v], tin[to]);
        } else {
            dfs(to, v);
            fup[v] = min(fup[v], fup[to]);
            if (fup[to] >= tin[v] && p != -1) {
                if (!cutpoints[v]) {
                    ans.push_back(v + 1);
                    cutpoints[v] = true;
                }
            }
            children++;
        }
    }
    if (p == -1 && children > 1){
        if (!cutpoints[v]) {
            ans.push_back(v + 1);
            cutpoints[v] = true;
        }
    }
}
 
int main() {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    g.resize(n);
    used.resize(n, false);
    tin.resize(n);
    fup.resize(n);
    cutpoints.resize(n, false);
    for (long i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        g[a - 1].push_back(b - 1);
        g[b - 1].push_back(a - 1);
    }
    timer = 0;
    for (int i = 0; i < n; i++) {
        if (!used[i]) {
            dfs(i, -1);
        }
    }
    sort(ans.begin(), ans.end());
    cout << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i] << " ";
    }
}
