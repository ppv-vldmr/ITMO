#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

vector<vector<int> > perehody1, perehody2; // старое состояние + символ -> новое состояние
vector<bool> dopusk1, dopusk2;
vector<bool> visited;
vector<int> associations;
int n1, m1, k1, n2, m2, k2;

bool dfs(int i1, int i2) {
    visited[i1] = true;
    if (dopusk1[i1] != dopusk2[i2])
        return false;
    associations[i1] = i2;
    bool result = true;
    for (int c = 0; c < 26; c++) {
        if ((perehody1[i1][c] == n1 && perehody2[i2][c] != n2) || (perehody1[i1][c] != n1 && perehody2[i2][c] == n2))
            return false;
        if (visited[perehody1[i1][c]])
            result &= (perehody2[i2][c] == associations[perehody1[i1][c]]);
        else
            result &= dfs(perehody1[i1][c], perehody2[i2][c]);
    }
    return result;
}

int main() {
    freopen ("isomorphism.in", "r", stdin);
    freopen ("isomorphism.out", "w", stdout);

    cin >> n1 >> m1 >> k1;

    dopusk1.resize(n1 + 1);
    for (int i = 0; i <= n1; i++) {
        dopusk1[i] = false;
    }

    for (int i = 0; i < k1; i++) {
        int x;
        cin >> x;
        --x;
        dopusk1[x] = true;
    }

    perehody1.resize(n1 + 1);
    for (int i = 0; i <= n1; i++) {
        perehody1[i].assign(26, n1);
    }
    for (int i = 0; i < m1; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--;
        b--;
        perehody1[a][c - 'a'] = b;
    }

    cin >> n2 >> m2 >> k2;

    dopusk2.resize(n2 + 1);
    for (int i = 0; i <= n2; i++) {
        dopusk2[i] = false;
    }

    for (int i = 0; i < k2; i++) {
        int x;
        cin >> x;
        --x;
        dopusk2[x] = true;
    }

    perehody2.resize(n2 + 1);
    for (int i = 0; i <= n2; i++) {
        perehody2[i].assign(26, n2);
    }
    for (int i = 0; i < m2; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--;
        b--;
        perehody2[a][c - 'a'] = b;
    }

    visited.assign(n1 + 1, false);
    associations.assign(n1 + 1, n1);

    dfs(0, 0) ? cout << "YES" : cout << "NO";
    return 0;
}