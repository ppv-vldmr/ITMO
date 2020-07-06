#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main() {
    int n, x, y, a0;
    cin >> n >> x >> y >> a0;
    vector<long long> a(n + 1);
    a[0] = 0;
    a[1] = a0;
    int t = 1 << 16;
    for (int i = 2; i < a.size(); i++)
        a[i] = ((x * a[i - 1] + y) % t);
    for (int i = 2; i < a.size(); i++)
        a[i] += a[i - 1];
//    for (long long i = 0; i < a.size(); i++)
//        cout << a[i] << " ";
    int t1 = 1 << 30;
    int m, z, c0;
    cin >> m >> z >> t >> c0;
    vector<long long> c(2 * m);
    if (m > 0)
    {
        c[0] = c0;
        for (int i = 1; i < c.size(); i++)
        {
            if ((z * c[i - 1] + t) % t1 < 0)
                c[i] = 0;
            else
                c[i] = (z * c[i - 1] + t) % t1;
        }
    }
    for (int i = 0; i < c.size(); i++)
        c[i] = c[i] % n;
//    for (long long i = 0; i < b.size(); i++)
//        cout << b[i] << " ";
//    cout << endl;
//    for (long long i = 0; i < c.size(); i++)
//        cout << c[i] << " ";
    long long sum = 0;
    for (int i = 0; i < m; i++)
            sum += a[max(c[2*i], c[2*i+1]) + 1] - a[min(c[2*i], c[2*i+1])];
    cout << sum;
    return 0;
}
