#include <iostream>
#include <vector>

using namespace std;

vector<int> dopusk;
vector<int> perehody[101][26];
long long way[1001][101];

int main() {
    ios_base::sync_with_stdio(false);
    freopen("problem4.in", "r", stdin);
    freopen("problem4.out", "w", stdout);
    int n, m, k, l;
    cin >> n >> m >> k >> l;

    for (int i = 0; i <= l; ++i)
        for (int j = 1; j <= n; ++j)
            way[i][j] = 0;
    way[0][1] = 1;

    for (int i = 0; i < k; ++i) {
        int x;
        cin >> x;
        dopusk.push_back(x);
    }

    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        perehody[b][c - 'a'].push_back(a);
    }

    for (int i = 1; i <= l; ++i) {
        for (int j = 1; j <= n; ++j) {
            for (int b = 0; b < 26; b++) {
                for (int k = 0; k < perehody[j][b].size(); ++k) {
                    way[i][j] += way[i - 1][perehody[j][b][k]];
                    way[i][j] %= (1000000000 + 7);
                }
            }
        }
    }

    long long cnt = 0;
    for (int i = 0; i < k; i++) {
        cnt += way[l][dopusk[i]];
        cnt %= (1000000000 + 7);
    }
    cout << cnt;
    return 0;
}
