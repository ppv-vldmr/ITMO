#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> perehody_reverse[100000][26];  // состояние + символ -> старое состояние
int perehody[100000][26]; // старое состояние + символ -> новое состояние
bool dopusk[100000];
bool useful[100000];
int visited[100000];
vector<int> topsort;

//ok
void find_all_useful(int n) {
    useful[n] = true;
    for (int i = 0; i < 26; i++) {
        for (int k = 0; k < perehody_reverse[n][i].size(); k++) {
            if (perehody_reverse[n][i][k] != -1)
                if (!useful[perehody_reverse[n][i][k]])
                    find_all_useful(perehody_reverse[n][i][k]);
        }
    }
}

//ok
bool has_cycle(int n) {
    visited[n] = 1;
    for (int i = 0; i < 26; i++) {
        if (perehody[n][i] != -1) {
            if (visited[perehody[n][i]] == 0) {
                if(has_cycle(perehody[n][i]))
                    return true;
            } else if (visited[perehody[n][i]] == 1 && useful[perehody[n][i]])
                return true;
        }
    }
    visited[n] = 2;
    topsort.push_back(n);
    return false;
}

long long paths(int n) {
    if (n == -1)
        return 0;
    if (n == 0)
        return 1;
    long long result = 0;
    for (int i = 0; i < 26; i++) {
        for (int k = 0; k < perehody_reverse[n][i].size(); k++) {
            result += paths(perehody_reverse[n][i][k]);
            result %= 1000000007;
        }
    }
    return result;
}

int main() {
    freopen ("problem3.in", "r", stdin);
    freopen ("problem3.out", "w", stdout);

    int n, m, k;
    cin >> n >> m >> k;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 26; j++) {
            perehody[i][j] = -1;
        }
    }

    for (int i = 0; i < n; i++) {
        dopusk[i] = false;
        useful[i] = false;
    }

    for (int i = 0; i < k; i++) {
        int x;
        cin >> x;
        --x;
        dopusk[x] = true;
    }

    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        a--;
        b--;
        perehody_reverse[b][c - 'a'].push_back(a);
        perehody[a][c - 'a'] = b;
    }

    for (int i = 0; i < n; i++)
        visited[i] = 0;

    for (int i = 0 ; i < n; i++) {
        if (dopusk[i])
            find_all_useful(i);
    }

    for (int i = 0; i < n; i++)
        visited[i] = 0;

    bool cycle = has_cycle(0);

    if (cycle) {
        cout << -1;
    } else {
        long long cnt = 0;
        reverse(topsort.begin(), topsort.end());
        vector<int> paths(n, 0);
        paths[0] = 1;
        for(size_t i = 0; i < topsort.size(); i++) {
            int to = topsort[i];
            for (int c = 0; c < 26; c++)
                for (size_t j = 0; j < perehody_reverse[to][c].size(); j++) {
                    paths[to] = (paths[to] + paths[perehody_reverse[to][c][j]]) % 1000000007;
                }
        }

        for(int i = 0; i < n; i++) {
            if (dopusk[i])
            cnt = (cnt + paths[i]) % 1000000007;
        }
        cout << cnt;
    }
    return 0;
}