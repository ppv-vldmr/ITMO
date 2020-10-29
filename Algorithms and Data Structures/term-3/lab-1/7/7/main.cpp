//
//  main.cpp
//  7
//
//  Created by Владимир on 23.10.2020.
//

#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>
#include <set>
#include <map>
#include <string>
#include <algorithm>

using namespace std;

vector<vector<int>> g, gr;
vector<bool> visited;
vector<int> order, color;
map<string, int> name_num;
map<int, string> val_name;

void dfs(int v) {
    visited[v] = true;
    for (int i = 0; i < g[v].size(); i++) {
        int to = g[v][i];
        if (!visited[to]) {
            dfs(to);
        }
    }
    order.push_back(v);
}

void dfs2(int v, int cnt) {
    visited[v] = true;
    color[v] = cnt;
    for (int i = 0; i < gr[v].size(); i++) {
        int to = gr[v][i];
        if (!visited[to]) {
            dfs2(to, cnt);
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    string st;
    for (int i = 0; i < n; i++) {
        cin >> st;
        name_num.insert(pair<string, int>(st, i));
        val_name.insert(pair<int, string>(i, st));
    }
    n *= 2;
    g.resize(n);
    gr.resize(n);
    color.resize(n);
    string st1, st2;
    for (int i = 0; i < m; ++i) {
        int num1 = 0, num2 = 0;
        cin >> st1 >> st2 >> st2;
        if (st1[0] == '-') {
            num1 = n / 2;
        }
        if (st2[0] == '-') {
            num2 = n / 2;
        }
        st1 = st1.substr(1);
        st2 = st2.substr(1);
        num1 += name_num.at(st1);
        num2 += name_num.at(st2);
        g[num1].push_back(num2);
        g[(num2 + n / 2) % n].push_back((num1 + n / 2) % n);
        gr[num2].push_back(num1);
        gr[(num1 + n / 2) % n].push_back((num2 + n / 2) % n);
    }
    visited.resize(n, false);
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i);
        }
    }
    reverse(order.begin(), order.end());
    int cnt = 0;
    visited.clear();
    visited.resize(n, false);
    for (int i = 0; i < n; i++) {
        int v = order[i];
        if (!visited[v]) {
            dfs2(v, cnt++);
        }
    }
    for (int i = 0; i < n / 2; i++) {
        if (color[i] == color[i + n/2]) {
            cout << "-1\n";
            return 0;
        }
    }
    vector<string> s;
    for (int i = 0; i < n / 2; i++) {
        if (color[i] > color[n / 2 + i]) {
            s.push_back(val_name.at(i));
        }
    }
    cout << s.size() << '\n';
    for (int i = 0; i < s.size(); i++) {
        cout << s[i] << ' ';
    }
    return 0;
}
