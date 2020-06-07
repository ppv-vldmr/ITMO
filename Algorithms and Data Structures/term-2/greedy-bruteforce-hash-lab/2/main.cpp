//B. Выбор заявок
#include <iostream>
#include <vector>
#include <set>

using namespace std;

void qSort(vector<pair<int, int>> &a, int l, int r) {
    int i = l, j = r, q = a[(i + j) / 2].second;
    do {
        while (a[i].second < q) i++;
        while (a[j].second > q) j--;
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

int greedy(vector<pair<int, int>> &a) {
    int result = 1, prev = a[0].second;
    for (int i = 1; i < a.size(); i++) {
        if (a[i].first >= prev) {
            result++;
            prev = a[i].second;
        }
    }
    return result;
}

int main() {
    freopen("request.in", "r", stdin);
    freopen("request.out", "w", stdout);
    int n;
    cin >> n;
    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }
    qSort(a, 0, n - 1);
    cout << greedy(a);
    return 0;
}
