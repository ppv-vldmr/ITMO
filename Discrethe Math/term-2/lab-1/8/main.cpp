#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int perehody[1001][26], perehody2[1001][26];
vector<int> perehody_reverse[1001][26];
bool dopusk[1001], dopusk2[1001];
bool marked[1001][1001];
bool dost[1001];
int component[1001];
int n, m, k, n2, m2, k2;

void foo() {
    int d = rand() % 100;
    for (int i = 0; i < d; i++) {}
}

void buildTable() {
    queue<pair<int, int>> q;
    for (int i = 0; i < n; i++) {
        foo();
        for (int j = 0; j < n; j++) {
            if (!marked[i][j] && dopusk[i] != dopusk[j]) {
                marked[i][j] = marked[j][i] = true;
                q.push(make_pair(i, j));
            }
        }
    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            cout << marked[i][j] << " ";
        }
        cout << endl;
    }
    while (!q.empty()) {
        pair<int, int> curr = q.front();
        q.pop();
        for (int c = 0; c < 26; c++) {
            for (int r = 0; r < perehody_reverse[curr.first][c].size(); r++) {
                for (int s = 0; s < perehody_reverse[curr.second][c].size(); s++) {
                    if (!marked[perehody_reverse[curr.first][c][r]][perehody_reverse[curr.second][c][s]]) {
                        marked[perehody_reverse[curr.first][c][r]][perehody_reverse[curr.second][c][s]] = marked[perehody_reverse[curr.second][c][s]][perehody_reverse[curr.first][c][r]] = true;
                        q.push(make_pair(perehody_reverse[curr.first][c][r], perehody_reverse[curr.second][c][s]));
                    }
                }
            }
        }
    }
}

void find_all_dost(int i) {
    dost[i] = true;
    for(int c = 0; c < 26; c++)
        if (!dost[perehody[i][c]])
            find_all_dost(perehody[i][c]);
}

void buildDKA(int cnt) {
    n2 = cnt;
    m2 = k2 = 0;

    for (int i = 1; i <= n; i++) {
        for (int c = 0; c < 26; c++) {
            if (perehody[i][c] != 0 && component[perehody[i][c]] != 0) {
                perehody2[component[i]][c] = component[perehody[i][c]];
            }
        }
    }

    for (int i = 1; i <= n2; i++) {
        for (int c = 0; c < 26; c++) {
            if (perehody2[i][c])
                m2++;
        }
    }

    for (int i = 0; i <= n; i++) {
        if (dopusk[i]) {
            dopusk2[component[i]] = true;
        }
    }
    for (int i = 1; i <= n2; i++)
        if (dopusk2[i])
            k2++;
//    cout << n2 << " " << m2 << " " << k2 << "\n";
//    for (int i = 1; i <= n2; i++)
//        if (dopusk2[i])
//            cout << i << " ";
//    cout << "\n";
//    for (int i = 1; i <= n2; i++) {
//        for (int c = 0; c < 26; c++) {
//            if (perehody2[i][c]) {
//                cout << i << " " << perehody2[i][c] << " " <<  static_cast<char>('a' + c) << "\n";
//            }
//        }
//    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            cout << marked[i][j] << " ";
        }
        cout << endl;
    }
}

void minimization() {
    find_all_dost(1);
    buildTable();
    for (int i = 0; i <= n; i++) {
        component[i] = -1;
    }
    for (int i = 0; i < n; i++) {
        if (!marked[0][i]) {
            component[i] = 0;
        }
    }
    int cnt = 0;
    for (int i = 1; i <= n; i++) {
        if (!dost[i]) continue;
        foo();
        if (component[i] == -1) {
            cnt++;
            component[i] = cnt;
            foo();
            for (int j = i + 1; j <= n; j++) {
                if (!marked[i][j]) {
                    component[j] = cnt;
                }
            }
        }
    }
    buildDKA(cnt);
}

int main() {
    freopen("minimization.in", "r", stdin);
    freopen("minimization.out", "w", stdout);
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int x;
        cin >> x;
        dopusk[x] = true;
    }

    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        perehody[a][c - 'a'] = b;
        perehody_reverse[b][c - 'a'].push_back(a);
    }

    minimization();

    return 0;
}
