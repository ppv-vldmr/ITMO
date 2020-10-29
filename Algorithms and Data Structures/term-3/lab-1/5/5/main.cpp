//
//  main.cpp
//  5
//
//  Created by Владимир on 20.10.2020.
//

#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>
#include <set>
#include <map>
#include <algorithm>
#include <stack>
#include <string>
#include <fstream>

using namespace std;

const int INF = 10000010;


vector<vector<pair<int, int>>> g;
vector<bool> used;
vector<int> tin, tou, color;
map<pair<int, int>, vector<int>> input;
set<int> points;
stack <pair<int,int>> s;
int _time = 0, cnt = 0, a, b;

void dfs(int v, int p = -1) {
    used[v] = true;
    tin[v] = tou[v] = _time++;
    for (size_t i = 0; i < g[v].size(); ++i) {
        int to = g[v][i].first;
        if (to == p) continue;
        if (used[to]) {
            if (tin[to] < tin[v])
                s.push(make_pair(v, g[v][i].first));
        } else {
            s.push(make_pair(v, g[v][i].first));
            dfs(to, v);
            if (tou[to] >= tin[v]) {
                cnt++;
                while (s.top() != make_pair(v, g[v][i].first)) {
                    map<pair<int, int>, vector<int>>::iterator it = input.find(s.top());
                    for (int j = 0; j < it->second.size(); ++j) {
                        color[it->second[j]] = cnt;
                    }
                    s.pop();
                }
                map<pair<int, int>, vector<int>>::iterator it = input.find(make_pair(v, g[v][i].first));
                for (int j = 0; j < it->second.size(); ++j) {
                    color[it->second[j]] = cnt;
                }
                s.pop();
            }
        }
        tou[v] = min(tou[v], tou[to]);
    }
}


int main() {
    std::ios::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    g.resize(n);
    for (int i = 0; i < m; ++i) {
        cin >> a >> b;
        if (input.count(make_pair(a - 1, b - 1))) {
            input.find(make_pair(a - 1, b - 1))->second.push_back(i);
            input.find(make_pair(b - 1, a - 1))->second.push_back(i);
            continue;
        }
        vector<int> vt;
        vt.push_back(i);
        input.insert(std::pair<std::pair<int, int>, vector<int>>(make_pair(a - 1, b - 1), vt));
        input.insert(std::pair< std::pair<int, int>, vector<int>>(make_pair(b - 1, a - 1), vt));
        g[a - 1].push_back(make_pair(b - 1, i));
        g[b - 1].push_back(make_pair(a - 1, i));
    }
    used.resize(n, false);
    tin.resize(n, INF);
    tou.resize(n, INF);
    color.resize(m, 0);
    for (int i = 0; i < n; ++i)
        if (!used[i]) {
            _time = 0;
            dfs(i);
        }
    cout << cnt << '\n';
    for (int i = 0; i < m; ++i) cout << color[i] << ' ';
    return 0;
}
