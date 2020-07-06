#include <bits/stdc++.h>

using namespace std;

long long int BinSearchLower(long long int x, vector<long long int>& a) {
    long long int l = -1, r = a.size();
    while (r - l > 1) {
        if (a[(l + r)/2] <= x) l = (l + r)/2; else r = (l + r)/2;
    }
    return(l);
}

long long int BinSearchUpper(long long int x, vector<long long int>& a) {
    long long int l = -1, r = a.size();
    while (r - l > 1) {
        if (a[(l + r)/2] < x) l = (l + r)/2; else r = (l + r)/2;
    }
    return(r);
}

int main() {
    long long int n, k;
    cin >> n >> k;
    vector <long long int> a(n);
    for (long long int i = 0; i < n; i++)
        cin >> a[i];
    for (long long int i = 0; i < k; i++) {
        long long int x;
        cin >> x;
        long long int k1= BinSearchLower(x,a), k2 = BinSearchUpper(x,a);
        if (k1 == -1) cout << a[0] << endl; else
            if (k2 == n) cout << a[n - 1] << endl; else
                if (abs(a[k1] - x) == abs (a[k2] - x)) cout << a[k1] << endl; else
                    if (abs(a[k1] - x) >= abs (a[k2] - x)) cout << a[k2] << endl; else
                        if (abs(a[k1] - x) <= abs (a[k2] - x)) cout << a[k1] << endl;
    }
    return 0;
}
