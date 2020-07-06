#include <iostream>
#include <vector>
#include <queue>
#include <map>

using namespace std;

vector<int> dopusk;
vector<int> perehody_reverse[100001][26];  // состояние + символ -> старое состояние
vector<int> perehody[100001][26]; // состояние + символ -> новое состояние
long long way[1001][100001];
bool is_dopusk[101];

void foo(int x) {
    for (int i = 0; i < x; i++) {}
}

int NFA_to_DFA() {
    vector<pair<vector<int>, int>> queue;
    map<vector <int>, int> ways_of_points;
    vector<int> start, cur;
    int current_number;
    bool add_to_dopusk;
    pair<vector<int>, int> tmp;
    start.push_back(1);
    queue.push_back(pair<vector<int>, int>(start, 1));
    ways_of_points.insert(pair<vector<int>, int>(start, 1));
    int n = 1;
    if (is_dopusk[1]) dopusk.push_back(1);
    while (!queue.empty()) {
        tmp = queue.front();
        queue.erase(queue.begin());
        cur = tmp.first;
        current_number = tmp.second;
        for (int i = 0; i < 26; ++i) {
            vector<int> qcur;
            bool visited[1001] = {};
            add_to_dopusk = false;
            for (int j : cur)
                for (int & k : perehody[j][i]) {
                    if (!visited[k]) {
                        foo(10);
                        visited[k] = true;
                        qcur.push_back(k);
                    }
                    if (is_dopusk[k])
                        add_to_dopusk = true;
                }
            if (ways_of_points.find(qcur) == ways_of_points.end()) {
                queue.push_back(pair<vector<int>, int>(qcur, n + 1));
                ways_of_points.insert(pair<vector<int>, int>(qcur, n + 1));
                perehody_reverse[n + 1][i].push_back(current_number);
                foo(20);
                if (add_to_dopusk) dopusk.push_back(n + 1);
                n++;
            } else {
                foo(30);
                perehody_reverse[ways_of_points.find(qcur)->second][i].push_back(current_number);
            }
        }
    }
    return n;
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("problem5.in", "r", stdin);
    freopen("problem5.out", "w", stdout);
    int n, m, k, l;
    long long cnt = 0;
    char c;
    cin >> n >> m >> k >> l;

    for (int i = 0; i < k; ++i) {
        int x;
        cin >> x;
        is_dopusk[x] = true;
    }

    for (int i = 0; i < m; ++i) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        perehody[a][c - 'a'].push_back(b);
    }

    n = NFA_to_DFA();
    way[0][1] = 1;

    for (int i = 1; i <= l; i++) {
        for (int j = 1; j <= n; j++) {
            for (int c = 0; c < 26; c++) {
                for (int q = 0; q < perehody_reverse[j][c].size(); q++) {
                    way[i][j] += way[i - 1][perehody_reverse[j][c][q]];
                    way[i][j] %= (1000000000 + 7);
                }
            }
        }
    }
    
    for (int i = 0; i < dopusk.size(); ++i) {
        cnt += way[l][dopusk[i]];
        cnt %= (1000000000 + 7);
    }
    cout << cnt;
    return 0;
}