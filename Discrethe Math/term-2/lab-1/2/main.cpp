#include <iostream>
#include <cstdio>
#include <vector>
#include <string>

using namespace std;

vector<int> dopusk;
vector<int> perehody[10001][26];
bool way[10001][101];

int main() {
    ios_base::sync_with_stdio(false);
    freopen("problem2.in", "r", stdin);
    freopen("problem2.out", "w", stdout);
    string word;
    int n, m, k;
    cin >> word >> n >> m >> k;

    for (int i = 0; i <= word.size(); ++i)
        for (int j = 1; j <= n; ++j)
            way[i][j] = false;
    way[0][1] = true;

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

    for (int i = 1; i <= word.size(); ++i) {
        for (int j = 1; j <= n; ++j) {
            for (int k = 0; k < perehody[j][word[i - 1] - 'a'].size(); ++k)
                way[i][j] = way[i][j] | way[i - 1][perehody[j][word[i - 1] - 'a'][k]];
        }
    }

    for (int i = 0; i < k; i++) {
        if (way[word.size()][dopusk[i]]){
            cout << "Accepts";
            return 0;
        }
    }
    cout << "Rejects";
    return 0;
}