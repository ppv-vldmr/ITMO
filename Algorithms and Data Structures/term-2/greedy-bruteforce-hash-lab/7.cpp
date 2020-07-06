#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int r;
long long cnt = 0, p;

void check(vector<int> a) {
    int sum = 0;
    for (int i = 0; i < a.size() - 1; i++) {
        sum += a[i] * a[i + 1];
        sum %= r;
    }
    int k = 0;
    for (int i = 1; i * i <= sum; i++) {
        if (sum % i == 0) {
            k += 2;
        }
        if (i * i == sum)
            k--;
    }
    if (k % 3 == 0)
        cnt++;
}

int main() {
    freopen("beautiful.in", "r", stdin);
    freopen("beautiful.out", "w", stdout);
    int n;
    cin >> n >> r;
    vector<int> a(n), b;
    for (int i = 1; i <= n; i++)
        a[i - 1] = i;
    p = 1;
    for (int i = 1; i <= n; i++)
        p *= i;
    for(int i = 0; i < p; i++) {
        next_permutation(a.begin(), a.end());
        check(a);
    }
    cout << cnt;
    return 0;
}
