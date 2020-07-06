#include <bits/stdc++.h>

using namespace std;

void qSort(int* a, int l, int r) {
    if (r - l < 1) return;
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

long long int BinSearchLower(int x, int* a, int n) {
    int l = -1, r = n;
    while (r - l > 1) {
    //for (int t = 0; t < 20; t++) {
        if (a[(l + r)/2] < x) l = (l + r)/2; else r = (l + r)/2;
    }
    return(l);
}

long long int BinSearchUpper(int x, int* a, int n) {
    int l = -1, r = n;
    while (r - l > 1) {
    //for (int t = 0; t < 20; t++) {
        if (a[(l + r)/2] <= x) l = (l + r)/2; else r = (l + r)/2;
    }
    return(r);
}

int main()
{
    int n, k;
    srand(time(0));
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++)
        cin >> a[i];
    random_shuffle(a, a + n);
    qSort(a, 0, n - 1);
    /*for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;*/
    cin >> k;
    for (int i = 0; i < k; i++) {
        int x, y;
        cin >> x >> y;
        x = BinSearchLower(x,a,n);
        y = BinSearchUpper(y,a,n);
        cout << y - x - 1 << " " << endl;
    }
    return 0;
}
