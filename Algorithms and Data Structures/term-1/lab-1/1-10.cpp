#include <bits/stdc++.h>

using namespace std;

double vremya(double x1, double y1, double x2, double y2, int v) {
    return (sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)))/v;
}

double root (double c, int v1, int v2) {
    double l = 0, r = 1, m1, m2;
    while (r - l > 0.00001) {
        m1 = l + (r - l) / 3.0;
        m2 = l + 2 * (r - l) / 3.0;
        //cout << vremya(0.0, 1.0, m1, c, v1) + vremya(1.0, 0.0, m1, c, v2) << " " << vremya(0.0, 1.0, m2, c, v1) + vremya(1.0, 0.0, m2, c, v2) << " " << m1 << " " << m2 << endl;
        if (vremya(0.0, 1.0, m1, c, v1) + vremya(1.0, 0.0, m1, c, v2) < vremya(0.0, 1.0, m2, c, v1) + vremya(1.0, 0.0, m2, c, v2)) r = m2; else l = m1;
    }
    return ((l + r)/2);
}

int main()
{
    int vp, vf;
    cin >> vp >> vf;
    double a;
    cin >> a;
    cout <<setprecision(6) << root(a, vp, vf);
    return 0;
}
