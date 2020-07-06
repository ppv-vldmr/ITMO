#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<long long> a(n), d(n), way, from(n);
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        d[i] = 1;
        from[i] = i;
    }
    for (int i = 0; i < d.size(); i++)
    {
        for (int j = 0; j < i; j++)
            if (a[j] < a[i])
                if (d[j] + 1 > d[i])
                {
                    d[i] = d[j] + 1;
                    from[i] = j;
                }
    }
    int len = 0, ind = -1;
    for (int i = 0; i < d.size(); i++)
        if (len < d[i])
        {
            len = d[i];
            ind = i;
        }
    while (d[ind] != 1)
    {
        way.push_back(a[ind]);
        ind = from[ind];
    }
    way.push_back(a[ind]);
    /*for (int i = 0; i < d.size(); i++)
        cout << d[i] << " ";
    cout << endl;*/
    cout << len << endl;
    for (int i = way.size() - 1; i >= 0; i--)
        cout << way[i] << " ";
    return 0;
}
