#include <bits/stdc++.h>

using namespace std;

void qSort(vector<int> &a, int l, int r) {
    int i = l, j = r, q = a[(i + j) / 2];
    do {
        while (a[i] < q) i++;
        while (a[j] > q) j--;
        if (i <= j) {
            swap(a[i], a[j]);
            i++;
            j--;
        }
    } while (i < j);
    if (l < j) qSort(a, l, j);
    if (i < r) qSort(a, i, r);
}

int main() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++)
        cin >> a[i];
    qSort(a, 0, n - 1);
    for (int i = 0; i < n; i++)
        cout << a[i] << " ";
    return 0;
}
