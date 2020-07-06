#include <bits/stdc++.h>

using namespace std;

int main()
{
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n, m;
    cin >> n >> m;
    vector<vector<int> > a, d;
    a.resize(n);
    d.resize(n);
    for (int i = 0; i < a.size(); i++)
    {
        a[i].resize(m);
        d[i].resize(m);
    }
    for (int i = 0; i < a.size(); i++)
        for (int j = 0; j < a[i].size(); j++)
        {
            cin >> a[i][j];
            d[i][j] = 0;
        }
    string way = "";
    d[0][0] = a[0][0];
    for (int i = 1; i < n; i++)
        d[i][0] = d[i - 1][0] + a[i][0];
    for (int i = 1; i < m; i++)
        d[0][i] = d[0][i - 1] + a[0][i];
    for (int i = 1; i < d.size(); i++)
    {
        for (int j = 1; j < d[i].size(); j++)
            d[i][j] = max(d[i - 1][j], d[i][j - 1]) + a[i][j];
    }
    int i = n - 1, j = m - 1;
    while (i > 0 && j > 0)
    {
        if (d[i - 1][j] > d[i][j - 1])
        {
            way = "D" + way;
            i--;
        }
        else
        {
            way = "R" + way;
            j--;
        }
    }
    for (int k = i; i > 0; i--)
        way = "D" + way;
    for (int k = j; j > 0; j--)
        way = "R" + way;
    /*for (int i = 0; i < d.size(); i++)
    {
        for (int j = 0; j < d[i].size(); j++)
            cout << d[i][j] << " ";
        cout << endl;
    }*/
    cout << d[n - 1][m - 1] << endl << way;
    return 0;
}
