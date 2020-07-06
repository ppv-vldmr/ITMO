#include <bits/stdc++.h>

using namespace std;

int main()
{
    string st1 = "", st2 = "";
    cin >> st1 >> st2;
    vector<vector<int> > d;
    d.resize(st1.size() + 1);
    for (int i = 0; i < d.size(); i++)
        d[i].resize(st2.size() + 1);
    for (int i = 0; i <= st1.size(); i++)
        d[i][0] = i;
    for (int i = 0; i <= st2.size(); i++)
        d[0][i] = i;
    for (int i = 1; i < d.size(); i++)
        for (int j = 1; j < d[i].size(); j++)
            if (st1[i - 1] == st2[j - 1])
                d[i][j] = d[i - 1][j - 1];
            else
                d[i][j] = min(min(d[i][j - 1], d[i - 1][j]), d[i - 1][j - 1]) + 1;
    cout << d[st1.size()][st2.size()];
    /*for (int i = 0; i < d.size(); i++)
    {
        for (int j = 0; j < d[i].size(); j++)
            cout << d[i][j] << " ";
        cout << endl;
    }*/
    return 0;
}
