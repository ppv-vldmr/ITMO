#include <iostream>
#include <vector>

using namespace std;

vector<int> dopusk;
vector <int> perehody[10000][26]; //предыдущее состояние -> в текущее состояние по ключу
bool way[10000][100];  //символ строки -> состояние автомата на данный момент

bool has_way_between_points(int i, int j, char key) {
    bool result = false;
    vector<int> temp = perehody[j][key - 'a'];
    for (int k = 0; k < temp.size(); k++)
        result = result | way[i - 1][temp[j]];
    return result;
}

int main() {
    for (int i = 0; i < 10000; i++) {
        for (int j = 0; j < 100; j++)
            way[i][j] = false;
    }

    ios_base::sync_with_stdio(false);
    freopen("problem2.in", "r", stdin);
    freopen("problem2.out", "w", stdout);
    string word;
    int n, m, k;
    cin >> word >> n >> m >> k;
    for(int i = 0; i < k; i++) {
        int x;
        cin >> x;
        dopusk.push_back(x);
    }
    for (int h = 0; h < m; h++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        perehody[--b][c - 'a'].push_back(--a);
    }
    for (int i = 0; i < word.size(); i++) {
        char c = word[i];
        for (int j = 0; j < n; i++) {
            way[i][j] = has_way_between_points(i, j, c);
        }
    }
    for (int i = 0; i < 26; i++)
        if (dopusk[way[word.size()][i]]) {
            cout << "Accepts";
            return 0;
        }
    cout << "Rejects";
    return 0;
}