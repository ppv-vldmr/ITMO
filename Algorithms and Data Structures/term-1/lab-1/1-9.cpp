#include <bits/stdc++.h>

using namespace std;

double root (double c) {
    double l = -1, r = 1000000001, m = (l + r)/2;
    while (r - l > 0.000001) {
        m = (l + r)/2;
        if (m*m + sqrt(m) <= c) l = m; else r = m;
    }
    return ((l + r)/2);
}

int main()
{
    double c;
    cin >> c;
    cout << setprecision(7) << root(c);
    return 0;
}
