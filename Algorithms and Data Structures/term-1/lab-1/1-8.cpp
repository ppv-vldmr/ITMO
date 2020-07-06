#include <bits/stdc++.h>

using namespace std;

long long searching(long long w, long long h, long long n) {
    long long l = -1, r = 1000000000*min(w,h), m;
    while (r - l > 1) {
        m = (l + r)/2;
        if ((m / max(w,h)) * (m / min(w,h)) < n) l = m; else r = m;
        //cout << m / max(w,h) << " " << m / min(w,h) << " " << (m / max(w,h)) * (m / min(w,h)) << endl;
    }
    return r;
}

int main()
{
    long long w, h, n;
    cin >> w >> h >> n;
    cout << searching(w,h,n);
    return 0;
}
