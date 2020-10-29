//
//  main.cpp
//  2
//
//  Created by Владимир on 13.10.2020.
//

#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

vector <vector<int>> graph;
vector <bool> visited;
vector <int> enter, ret, color, ans;
map<pair<int, int>, int> input;

int t = 0;

void dfs(int v, int p) {
    color[v] = 1;
    t++;
    enter[v] = t;
    ret[v] = t;
    for (int i = 0; i < graph[v].size(); ++i) {
        int to = graph[v][i];
        if (color[to]) {
            if (to != p) {
                ret[v] = min(ret[v], enter[to]);
            }
        } else {
            dfs(to, v);
            ret[v] = min(ret[v], ret[to]);
            if (enter[v] < ret[to]) {
//                cout << v + 1 << " " << to + 1 << endl;
                map<pair<int, int>, int>::iterator it = input.find(make_pair(to + 1, v + 1));
                if (it == input.end()) {
                    it = input.find(make_pair(v + 1, to + 1));
                }
                ans.push_back(it->second);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    graph.resize(n);
    visited.resize(n);
    color.resize(n, 0);
    enter.resize(n);
    ret.resize(n);
    for (long i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        graph[a - 1].push_back(b - 1);
        graph[b - 1].push_back(a - 1);
        input.insert(pair<pair<int, int>, int>(pair<int,int>(a, b), i));
    }
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            dfs(i, i);
        }
    }
    sort(ans.begin(), ans.end());
    cout << ans.size() << '\n';
    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i] + 1 << '\n';
    }
    return 0;
}

