#include <bits/stdc++.h>

using namespace std;

int main()
{
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    int n, k;
    cin >> n >> k;
    vector<int> a(n, 0), d(n), p, ans;
    p.push_back(1);
    for (int i = 1; i < n - 1; i++)
        cin >> a[i];
    for (int i = 1; i < n; i++)
    {
        int num_max = -10001, ind = 0;
        for (int j = i - k; j < i; j++)
        {
            if (j > -1 && d[j] > num_max)
            {
                num_max = d[j];
                ind = j;
            }
        }
        d[i] = a[i] + num_max;
        p.push_back(ind+1);
    }
    cout << d[n-1] << endl;
    int i = p.size()-1;
    ans.push_back(n);
    while(p[i] != 1)
    {
        ans.push_back(p[i]);
        i = p[i]-1;
    }
    ans.push_back(1);
    cout << ans.size()-1 << endl;
    sort(ans.begin(), ans.end());
    for (int i = 0; i < ans.size(); i++)
    {
        cout << ans[i] << " ";
    }
}
