#include <bits/stdc++.h>

using namespace std;

vector<vector<long long> > d;
long long n;

long long get_number(string s)
{
    long long num = 0, depth = 0;
    for (long long i = 0; i < 2 * n; i++)
        if (s[i] == '(')
            depth++;
        else
        {
            if (depth + 1 <= n)
                num += d[2 * n - i - 1][depth + 1];
            depth--;
        }
    return num;
}

int main()
{
    freopen("brackets2num.in","r",stdin);
    freopen("brackets2num.out","w",stdout);
    string s = "";
    cin >> s;
    n = s.size() / 2;
    d.resize(2*n + 1);
    for (long long i = 0; i < d.size(); i++)
        d[i].resize(n+1);
    for (long long i = 0; i < d.size(); i++)
        for (long long j = 0; j < d[i].size(); j++)
            d[i][j] = 0;
    d[0][0] = 1;
    for (long long i = 1; i < 2 * n; i++)
        for (long long j = 0; j < n + 1; j++)
        {
            if (j == 0)
                d[i][j] = d[i - 1][j + 1];
            else
                if (j == n)
                    d[i][j] = d[i - 1][j - 1];
                else
                    d[i][j] = d[i - 1][j - 1] + d[i - 1][j + 1];
        }
    /*for (long long i = 0; i < d.size(); i++)
    {
        for (long long j = 0; j < d[i].size(); j++)
            cout << d[i][j] << " ";
        cout << endl;
    }*/
    cout << get_number(s);
    return 0;
}
