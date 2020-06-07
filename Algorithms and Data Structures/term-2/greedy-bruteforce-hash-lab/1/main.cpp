// A. Проблема сапожника
#include <iostream>
#include <vector>

using namespace std;

void qSort(vector<int> &a, int l, int r) {
    int i = l, j = r, q = a[(i + j) / 2];
    do {
        while (a[i] < q) i++;
        while (a[j] > q) j--;
        if (i <= j) {
            int w = a[i];
            a[i] = a[j];
            a[j] = w;
            i++;
            j--;
        }
    } while (i < j);
    if (l < j) qSort(a, l, j);
    if (i < r) qSort(a, i, r);
}

int greedy(vector<int> &a, int k) {
    int result = 0, i = 0;
    while (k > 0 && i < a.size()) {
        if (k >= a[i]) {
            result++;
            k -= a[i];
        }
        i++;
    }
    return result;
}

int main() {
    freopen("cobbler.in", "r", stdin);
    freopen("cobbler.out", "w", stdout);
    int k, n;
    cin >> k >> n;
    vector<int> a (n, 1001);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    qSort(a, 0, n - 1);
    cout << greedy(a, k);
    return 0;
}
