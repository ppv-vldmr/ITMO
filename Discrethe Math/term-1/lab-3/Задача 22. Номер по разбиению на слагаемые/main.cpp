#include <bits/stdc++.h>

using namespace std;

vector<vector<long long> > d;

vector<int> parse(string st)
{
    vector<int> res;
    while (st.find("+") != -1)
    {
        res.push_back(atoi(st.substr(0,st.find("+")).c_str()));
        st = st.substr(st.find("+") + 1);
        //cout << st << endl;
    }
    res.push_back(atoi(st.c_str()));
    return res;
}

int part2num(vector<int> a, int N)
{
    long long numOfPart = 0;
    int sum = 0, last = 0;
    for (int i = 0; i < a.size(); i++)
    {
        for (int j = last; j < a[i]; j++)
        {
            numOfPart += d[N - sum - j][j];
        }
        sum += a[i];
        last = a[i];
    }
    return numOfPart;
}

int main()
{
    freopen("part2num.in", "r", stdin);
    freopen("part2num.out", "w", stdout);
    string st;
    cin >> st;
    vector<int> a = parse(st);
    int sum = 0;
    for (int i = 0; i < a.size(); i++)
        sum += a[i];
    d.resize(sum + 1);
    for (int i = 0; i < d.size(); i++)
        d[i].resize(sum + 1);
    for (int i = 0; i < sum; i++)
    {
        for (int j = i; j >= 0; j--)
        {
            if (i == j)
                d[i][j] = 1;
            else
                if (j + 1 < sum && i - j >= 0)
                        d[i][j] = d[i][j + 1] + d[i - j][j];
        }
    }
    for (int i = 0; i < d.size(); i++)
        d[i][0] = 0;
    /*for (int i = 0; i < d.size(); i++)
    {
        for (int j = 0; j < d[i].size(); j++)
            cout << d[i][j] << " ";
        cout << endl;
    }
*/
    cout << part2num(a,sum);
    return 0;
}
