#include <iostream>
#include <vector>

using namespace std;

void qSort(vector<pair<int, int>> &a, int l, int r) {
    int i = l, j = r, q = a[(i + j) / 2].first;
    do {
        while (a[i].first > q) i++;
        while (a[j].first < q) j--;
        if (i <= j) {
            pair<int, int> w = a[i];
            a[i] = a[j];
            a[j] = w;
            i++;
            j--;
        }
    } while (i < j);
    if (l < j) qSort(a, l, j);
    if (i < r) qSort(a, i, r);
}

int main() {
    freopen("sequence.in", "r", stdin);
    freopen("sequence.out", "w", stdout);
    int n;
    cin >> n;
    long long sum1 = 0, sum2 = 0;
    vector<int> s1, s2;
    vector<pair<int, int>> a(n);
    for (int i = 0 ; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
    }
    qSort(a, 0, n - 1);
    for (int i = 0; i < n; i++) {
        if (sum1 < sum2) {
            sum1 += a[i].first;
            s1.push_back(a[i].second);
        }
        else {
            sum2 += a[i].first;
            s2.push_back(a[i].second);
        }
    }
    if (sum1 != sum2) {
        cout << -1 << "\n";
    } else {
        cout << s1.size() << "\n";
        for (auto i : s1)
            cout << i + 1 << " ";
    }
    return 0;
}
