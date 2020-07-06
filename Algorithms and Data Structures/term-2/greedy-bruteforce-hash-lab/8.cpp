#include <iostream>
#include <vector>

using namespace std;

vector<string> res;

void gen(int n, string vec) {
    if (vec.size() == n) {
        res.push_back(vec);
        return;
    } else {
        gen(n, vec + "0");
        if (vec.size() == 0 || vec.substr(vec.size() - 1) != "1") {
            gen(n, vec + "1");
        }
    }
}

int main() {
    freopen("vectors2.in", "r", stdin);
    freopen("vectors2.out", "w", stdout);
    int n;
    cin >> n;
    gen(n, "");
    cout << res.size() << "\n";
    for (const auto & i : res) {
        cout << i << "\n";
    }
    return 0;
}
