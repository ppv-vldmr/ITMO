#include <bits/stdc++.h>

using namespace std;

long long factorial(int n)
{
    long long sum = 1;
    for (int i  = 1; i <= n; i++)
        sum *= i;
    return sum;
}

int main()
{
    freopen("perm2num.in", "r", stdin);
    freopen("perm2num.out", "w", stdout);
    int n;
    cin >> n;
    vector<int> a;
    vector<bool> used(n + 1, false);
    for (int i = 0; i < n; i++)
    {
        int x;
        cin >> x;
        a.push_back(x);
    }
    long long num = 0;
    for (int i = 0; i < n - 1; i++)
    {
        int x = a[i], cnt = 0;
        for (int j = 1; j < x; j++)
        {
            if (!used[j])
                cnt++;
        }
        num += cnt * factorial(n - i - 1);
        used[x] = true;
        /*for (int j = 0; j < used.size(); j++)
            cout << used[j] << " ";
        cout << endl;*/
    }
    cout << num << endl;
    return 0;
}
