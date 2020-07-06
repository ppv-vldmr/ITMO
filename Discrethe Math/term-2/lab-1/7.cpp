#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

vector<vector<int> > perehody1, perehody2; // старое состояние + символ -> новое состояние
vector<bool> dopusk1, dopusk2;
vector<vector<bool> > used;
int n1, m1, k1, n2, m2, k2;

bool is_isomorphic() {
    queue<pair<int, int> > q;
    q.push(make_pair(0, 0));
    used[0][0] = true;
    while (!q.empty()) {
        pair<int, int> temp = q.front();
        q.pop();
        if (dopusk1[temp.first] != dopusk2[temp.second])
            return false;
        for (int c = 0; c < 26; c++) {
            if (!used[perehody1[temp.first][c]][perehody2[temp.second][c]]) {
                q.push(make_pair(perehody1[temp.first][c], perehody2[temp.second][c]));
                used[perehody1[temp.first][c]][perehody2[temp.second][c]] = true;
            }
        }
    }
    return true;
}

int main() {
    freopen ("equivalence.in", "r", stdin);
    freopen ("equivalence.out", "w", stdout);

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

    used.resize(n1 + 1);
    for (int i = 0; i <= n1; i++) {
        used[i].resize(n2 + 1);
        for (int j = 0; j <= n2; j++)
            used[i][j] = false;
    }

    is_isomorphic() ? cout << "YES" : cout << "NO";
    return 0;
}