#include <bits/stdc++.h>

using namespace std;

long long k, n;
vector <long long> a;

void nextPartition(){
    a[k - 1]--;
    a[k - 2]++;
    if (a[k - 2] > a[k - 1])
    {
        a[k - 2] += a[k - 1];
        a.pop_back();
        k--;
    }
    else
        while (a[k - 2] * 2 <= a[k - 1])
        {
            a.push_back(a[k - 1] - a[k - 2]);
            k = a.size();
            a[k - 2] = a[k - 3];
        }
}

int main()
{
    freopen("num2part.in","r",stdin);
    freopen("num2part.out","w",stdout);
    long long r;
    cin >> k >> r;
    a.resize(k);
    n = k;
    for (long long i = 0; i < n; i++){
        a[i] = 1;
    }
    for (int i = 0; i < r; i++)
        nextPartition();
    for (long long i = 0; i < a.size() - 1; i++)
        cout << a[i] << "+";
    cout << a.back();
}
