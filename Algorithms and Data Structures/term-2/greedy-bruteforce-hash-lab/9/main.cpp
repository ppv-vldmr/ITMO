#include <iostream>
#include <vector>

using namespace std;

bool check_multiply(vector<vector<int>> a, vector<vector<int>> c) {
    vector<vector<int>> check = vector(4, vector<int>(4, 0));
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < a[i].size(); j++) {
            int temp = 0;
            for (int k = 0; k < 4; k++) {
                temp += a[i][k] * a[k][j];
            }
            check[i][j] = temp;
        }
    }
    int cnt = 0;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (check[i][j] % 2 == c[i][j]) {
                cnt++;
            }
        }
    }
    return cnt == 16;
}

vector<vector<int>> number_to_matrix(int n) {
    vector<vector<int>> a = vector(4, vector<int>(4, 0));
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            a[i][j] = (n >> (15 - i*4 -  j)) % 2;
        }
    }
    return a;
}

int find_solution(vector<vector<int>> a) {
    for (int i = 0; i < 65536; i++) {
        vector<vector<int>> temp = number_to_matrix(i);
        if (check_multiply(temp, a))
            return i;
    }
    return -1;
}

void write(vector<vector<int>> a) {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            cout << a[i][j] << " ";
        }
        cout << "\n";
    }
}

int main() {
    freopen("sqroot.in", "r", stdin);
    freopen("sqroot.out", "w", stdout);
    vector<vector<int>> a = vector(4, vector<int>(4, 0));
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> a[i][j];
        }
    }
    int ans = find_solution(a);
    if (ans == -1) {
        cout << "NO SOLUTION";
    } else {
        vector<vector<int>> res = number_to_matrix(ans);
        write(res);
    }
    return 0;
}
