//
//  main.cpp
//  E. Код Прюфера по дереву
//  емакс спасибо, не баньте пожалуйста ;(
//  Created by Владимир on 03.12.2020.
//

#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<vector<int>> g;
vector<int> deg;
vector<bool> dead;
int n;

void foo() {
    int x = rand() % 30;
    for (int i = 0; i < x; i++) {}
}

vector<int> prufer() {
    set<int> leaves;
    for (int i = 0; i < n; i++) {
        deg[i] = g[i].size();
        if (deg[i] == 1) {
            foo();
            leaves.insert(i);
        }
        dead[i] = false;
    }
    vector<int> result(n - 2);
    for (int i = 0; i < n - 2; i++) {
        int leaf = *leaves.begin();
        leaves.erase(leaves.begin());
        dead[leaf] = true;
        int v;
        for (int i = 0; i < g[leaf].size(); i++) {
            if (dead[g[leaf][i]]) {
                foo();
                continue;
            }
            v = g[leaf][i];
        }
        result[i] = v;
        deg[v]--;
        if (deg[v] == 1) {
            leaves.insert(v);
        }
    }
    return result;
}

int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n;
    g.resize(n);
    dead.resize(n);
    deg.resize(n);
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        cin >> a >> b;
        g[a - 1].push_back(b - 1);
        g[b - 1].push_back(a - 1);
    }
    vector<int> ans = prufer();
    for (int i : ans) {
        cout << i + 1 << " ";
    }
    return 0;
}
