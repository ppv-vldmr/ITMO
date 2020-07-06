#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<vector<int>> ans;
int n;

void preprocessing() {
    for (int j = 1; j < ceil(log2(n)); j++) {
        for (int i = 0; i < ans.size(); i++) {
            if (ans[i][j - 1] == -1)
                continue;
            ans[i][j] = ans[ans[i][j - 1]][j - 1];
        }
    }
    for (int i = 0; i < ans.size(); i++) {
        cout << i + 1 << ": ";
        for (int j = 0; j < ans[i].size(); j++) {
            if (ans[i][j] != -1)
                cout << ans[i][j] + 1 << " ";
        }
        cout << endl;
    }
}

int main() {
    cin >> n;
    ans.resize(n, vector<int>(ceil(log2(n)), -1));
    for (int i = 0; i < n; i++) {
        cin >> ans[i][0];
        ans[i][0]--;
    }
    preprocessing();
    return 0;
}