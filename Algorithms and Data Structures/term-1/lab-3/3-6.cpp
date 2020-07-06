#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> a, b;
    vector<vector<int> > d;
    a.resize(n);
    d.resize(n+1);
    for (int i = 0; i < n; i++)
        cin >> a[i];
    for (int i = 0; i < n + 1; i++)
        d[i].resize(n + 1);
    for (int i = 0; i < n+1; i++)
    {
        d[0][i] = 30001;
        d[i][0] = 0;
    }
    for (int i = 1; i < n + 1; i++)
        for (int j = 1; j < n + 1; j++)
            if (j > i)
                d[i][j] = 30001;
    for (int i = 1; i < n + 1; i++)
        for (int j = 0; j < i + 1; j++)
            if (a[i - 1] <= 100)
                d[i][j] = min(d[i - 1][j] + a[i - 1], d[i - 1][j + 1]);
            else
                if (j == 0)
                    d[i][j] = min(30001 + a[i - 1], d[i - 1][j + 1]);
                else
                    d[i][j] = min(d[i - 1][j - 1] + a[i - 1],d[i - 1][j + 1]);
    /*for (int i = 0; i < n+1; i++)
    {
        for (int j = 0; j < n+1; j++)
            cout << d[i][j] << " ";
        cout << endl;
    }
    cout << endl;*/
    int ans = d[n][0];
    int ind_ans = 0;
    for (int i = 1; i < n + 1; i++)
        if (d[n][i] > 0 && d[n][i] <= ans)
        {
            ans = d[n][i];
            ind_ans = i;
        }
    int n1 = n;
    int ind = ind_ans;
    if (ans != 0)
    while (n1 > 0)
    {
        if ( a[n1 - 1] <= 100 )
            if (d[n1][ind] == d[n1-1][ind+1])
            {
                b.push_back(n1);
                n1--;
                ind++;
            }
            else
                n1--;
        else
            if (d[n1][ind] == d[n1-1][ind+1])
            {
                b.push_back(n1);
                n1--;
                ind++;
            }
            else
            {
                n1--;
                ind--;
            }
    }
    sort(b.begin(), b.end());
    cout << ans << endl;
    cout << ind_ans << " " << b.size() << endl;
    for (int i = 0; i < b.size(); i++){
        cout << b[i] << endl;
    }
}
