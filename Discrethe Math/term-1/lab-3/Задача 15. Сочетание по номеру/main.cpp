#include <bits/stdc++.h>

using namespace std;

void action(int n, int k, int m)
{
    vector<int> a(k);
    for(int i = 0; i < k; i++)
        a[i] = i;
    for (int h = 0; h < m; h++)
    {
        if (a[k - 1] < n - 1)
            a[k - 1]++;
        else
        {
            int j;
            for(j = k - 1; j > 0; j--)
                if (a[j] - a[j - 1] > 1)
                    break;
            if (j == 0)
                break;
            a[j - 1]++;
            for(int i = j; i < k; i++)
                a[i] = a[i-1] + 1;
        }
    }
    for(int i = 0; i < k; i++)
        cout << a[i] + 1 << " ";
}

int main()
{
    freopen("num2choose.in", "r", stdin);
    freopen("num2choose.out", "w", stdout);
    long long n, k, m;
    cin >> n >> k >> m;
    action(n, k, m);
    return 0;
}
