#include <bits/stdc++.h>

using namespace std;

vector <long long> a,pad;
int n;

int func()
{
    a[1] = pad[8] + pad[6];
    a[2] = pad[7] + pad[9];
    a[3] = pad[4] + pad[8];
    a[4] = pad[3] + pad[9] + pad[0];
    a[5] = 0;
    a[6] = pad[1] + pad[7] + pad[0];
    a[7] = pad[2] + pad[6];
    a[8] = pad[1] + pad[3];
    a[9] = pad[2] + pad[4];
    a[0] = pad[4] + pad[6];
    for (int i = 0; i < a.size(); i++)
        a[i] = a[i] % 1000000000;
}

int main()
{
    cin >> n;
    a.resize(10);
    pad.resize(10);
    for (int i = 0; i < 10; i++)
        a[i] = 1;
    a[8] = 0;
    a[0] = 0;
    long long sum = 0;
    if (n == 1)
        sum = 8;
    else
    {
        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j < 10; j++)
                pad[j] = a[j];
            func();
        }
        for (int i = 0; i < 10; i++)
            sum += a[i];
    }
    cout << sum % 1000000000;
}
