#include <bits/stdc++.h>

using namespace std;

int main()
{
    freopen("num2brackets2.in", "r", stdin);
    freopen("num2brackets2.out", "w", stdout);
    int n;
    long long k;
    cin >> n >> k;
    long long d[2 * n + 1][n + 1];
    k++;
    for (int i = 0; i <= 2 * n; i++)
        for (int j = 0; j <= n; j++)
        d[i][j] = 0;
    d[0][0] = 1;
    for (long long i = 1; i < 2 * n; i++)
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
    string res = "";
    int depth = 0;
    vector<string> log(2 * n);
    int s = 0;
    for (int i = 2 * n - 1; i >= 0; i--)
    {
        long long c;
        if (depth + 1 <= n)
            c = d[i][depth + 1] << ((i - depth - 1) / 2);
        else
            c = 0;
        if (c >= k)
        {
            res += "(";
            log[s++] = "(";
            depth++;
            continue;
        }
        k -= c;
        if (s > 0 && log[s - 1] == "(" && depth > 0)
            c = d[i][depth - 1] << ((i - depth + 1) / 2);
        else
            c = 0;
        if (c >= k)
        {
            res += ")";
            --s;
            --depth;
            continue;
        }
        k -= c;

        if (depth < n)
            c = d[i][depth + 1] << ((i - depth - 1) / 2);
        else
            c = 0;
        if (c >= k)
        {
            res += "[";
            log[s++] = "[";
            ++depth;
            continue;
        }
        k -= c;
        res += "]";
        --s;
        depth--;
    }
    cout << res;
    return 0;
}
