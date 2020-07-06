#include <bits/stdc++.h>

using namespace std;

long long int BinSearchLower(int x, int y, long long n) {
    long long l = -1, r = INT_MAX;
    while (r - l > 1) {
    //for (int t = 0; t < 30; t++) {
        long long m = (l + r)/2;
        if (m / x + m / y < n - 1) l = m; else r = m;
        //cout << l << " " << r << " " << m / x + m / y << endl;
    }
    return(r);
}

long long int BinSearchUpper(int x, int y, long long n) {
    long long l = 1, r = 200000000;
    while (r - l > 1) {
    //for (int t = 0; t < 30; t++) {
        long long m = (l + r)/2;
        if (m / x + m / y <= n) l = m; else r = m;
        //cout << l << " " << r << " " << m / x + m / y << endl;
    }
    return(r);
}

int main()
{
    long long n , x, y, cnt = 0, t = 0;
    cin >> n >> x >> y;
    cout << BinSearchLower(x, y, n) + min(x, y);
    return 0;
}
