#include <bits/stdc++.h>

using namespace std;

void qSort(vector<long long> &a, long long l, long long r)
{
    long long i = l, j = r, q = a[(i + j) / 2];
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

int main()
{
    freopen("huffman.in", "r", stdin);
    freopen("huffman.out", "w", stdout);
    long long n, res = 0;
    cin >> n;
    vector<long long> a, b;
    for (long long i = 0; i < n; i++)
    {
        long long x;
        cin >> x;
        a.push_back(x);
        b.push_back(1000000001);
    }
    a.push_back(100000000000000);
    b.push_back(1000000001);
    /*for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;*/
    qSort(a, 0, a.size() - 1);
    /*for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;
    for (int i = 0; i < b.size(); i++)
        cout << b[i] << " ";
    cout << endl;*/
    long long i =0, j = 0;
    for (long long k = 0; k < n - 1; k++)
    {
       long long x = a[i] + a[i + 1], y = a[i] + b[j], z = b[j] + b[j + 1];
       //cout << x << " " << y << " " << z << endl;
       if (x <= y && x <= z)
       {
           b[k] = x;
           res += x;
           i += 2;
       } else
       if (y <= x && y <= z)
       {
           b[k] = y;
           res += y;
           i++;
           j++;
       } else
       //if (z <= x && z <= y)
       {
           b[k] = z;
           res += z;
           j += 2;
       }
       /*for (int i = 0; i < b.size(); i++)
            cout << b[i] << " ";
        cout << endl;
       cout << res << endl;*/
    }
    /*for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;
    for (int i = 0; i < b.size(); i++)
        cout << b[i] << " ";
    cout << endl;*/
    cout << res << endl;
    return 0;
}
