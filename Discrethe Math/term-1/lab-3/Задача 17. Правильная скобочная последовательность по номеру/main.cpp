#include <bits/stdc++.h>

using namespace std;

int main()

{
    freopen("num2brackets.in","r",stdin);
    freopen("num2brackets.out","w",stdout);
    long long n, k;
    vector<vector<long long> > d;
    cin >> n >> k;
    k++;
    d.resize(2*n + 1);
    for (long long i = 0; i < d.size(); i++)
        d[i].resize(n+1);
    for (long long i = 0; i < d.size(); i++)
        for (long long j = 0; j < d[i].size(); j++)
            d[i][j] = 0;
    d[0][0] = 1;
    for (long long i = 1; i < d.size() - 1; i++)
        for (long long j = 0; j < n+1; j++)
        {
            if (j == 0)
                d[i][j] = d[i - 1][j + 1];
            else
                if (j == n)
                    d[i][j] = d[i - 1][j - 1];
                else
                    d[i][j] = d[i - 1][j - 1] + d[i - 1][j + 1];
        }
    long long depth = 0;
    string s = "";
    for (long long i = 0; i < 2 * n; i++)
    {
        if (depth + 1 <= n && d[2*n - (i + 1)][depth + 1] >= k)
        {
            s += "(";
            depth++;
        }
        else
        {
            if (depth + 1 <= n)
                k -= d[2*n - i - 1][depth + 1];
            s += ")";
            depth--;
        }
    }
    cout << s;
}
