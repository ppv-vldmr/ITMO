#include <iostream>
#include <string>
#include <cstdio>
#include <vector>

using namespace std;

long long d[100][100][100];
string start, w;
vector<pair<int, int>> rules[100];

int main() {
    freopen("nfc.in", "r", stdin);
    freopen("nfc.out", "w", stdout);
    int n;
    cin >> n >> start;
    getline(cin, w);
    for (int i = 0; i < n; i++) {
        getline(cin, w);
        if (w.size() == 6) {
            rules[w[0] - 'A'].emplace_back(w[5] - 'a', -1);
        } else {
            rules[w[0] - 'A'].emplace_back(w[5] - 'A', w[6] - 'A');
        }
    }
    getline(cin, w);
    
    for (int i = 0; i < w.size(); i++)
        for (int j = 0; j < 26; j++)
            for (int x = 0; x < rules[j].size(); x++)
                if (rules[j][x].second == -1 && ((w[i] - 'a') == rules[j][x].first))
                    d[j][i][i] += 1;
                
    for (int l = 2; l <= w.size(); l++)
        for (int st = 0; st <= w.size() - l; st++)
            for (int k = st; k < st + l - 1; k++)
                for (int i = 0; i < 26; i++)
                    for (int x = 0; x < rules[i].size(); x++)
                        if (rules[i][x].second != -1) {
                            d[i][st][st + l - 1] += (d[rules[i][x].first][st][k] % 1000000007) * (d[rules[i][x].second][k + 1][st + l - 1] % 1000000007);
                            d[i][st][st + l - 1] %= 1000000007;
                        }
    
    cout << d[start[0] - 'A'][0][w.size() - 1];
    return 0;
}