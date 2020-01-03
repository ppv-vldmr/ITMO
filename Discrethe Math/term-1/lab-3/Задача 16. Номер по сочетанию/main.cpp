#include <bits/stdc++.h>

using namespace std;

int main()

{
    freopen("choose2num.in", "r", stdin);
    freopen("choose2num.out", "w", stdout);
    int n, k;
    cin >> n >> k;
    vector <int> choose(k + 1);
    choose[0] = 0;
    for (int i = 0; i < k; i++)
    {
        int x;
        cin >> x;
        choose[i + 1] = x;
    }
    vector <vector <long long> > base(n + 1);
    base.resize(n+1);
    for (long long i = 0; i < base.size(); i++)
    {
        vector<long long> help(n + 1);
        base[i] = help;
    }
    for (int i = 0; i < n + 1; i++)
    {
        base[i][0] = 1;
        base[i][1] = i;
    }
    for (int i = 2; i < n+1; i++)
    {
        for (int j = 2; j <= i; j++)
        {
            base[i][j] = base[i - 1][j] + base[i - 1][j - 1];
        }
    }
    int result = 0;
    for (int i = 1; i < choose.size(); i++)
    {
        for (int j = choose[i - 1] + 1; j <= choose[i] - 1; j++)
        {
            result += base[n - j][k - i];
            //cout << i << " " << j << " " << result << endl;
        }
    }
    cout << result;
}
