//
//  main.cpp
//  H. Хроматический многочлен
//
//  Created by Владимир on 31.12.2020.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void foo(int x) {
    for (int i = 0; i < x; i++) {}
}

vector<int> chromaticPolynom(vector<pair<int, int>> edges, int n) {
    if (edges.empty()) {
        vector<int> ans(n);
        ans[n - 1] = 1;
        foo(rand() % 40);
        return ans;
    }
    pair<int, int> last = edges.back();
    edges.pop_back();
    vector<bool> s(n);
    vector<pair<int, int>> new_edges;
    for (auto e: edges) {
        if (e.second == last.second || e.second == last.first) {
            foo(rand() % 50);
            swap(e.first, e.second);
        }
        if (e.first == last.first || e.first == last.second) {
            e.first = last.first;
            foo(rand() % 20);
            if (!s[e.second]) {
                s[e.second] = true;
                new_edges.push_back(e);
                foo(rand() % 20);
            }
        } else {
            new_edges.push_back(e);
        }
    }
    vector<int> ans1 = chromaticPolynom(new_edges, n - 1);
    vector<int> ans = chromaticPolynom(edges, n);
    for (int i = 0; i + 1 < n; i++) {
        ans[i] -= ans1[i];
    }
    return ans;
}

int main() {
    int n, m;
    cin >> n >> m;
    vector<pair<int, int>> edges;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        edges.push_back(make_pair(u - 1, v - 1));
    }
    vector<int> ans = chromaticPolynom(edges, n);
    cout << n << endl;
    for (int i = 0; i < ans.size(); i++) {
        cout << ans[ans.size() - 1 - i] << " ";
    }
    cout << "0" << endl;
}
