#include <bits/stdc++.h>

using namespace std;

void write(vector<int> a)
{
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;
}

void action(int n, vector<int> a)
{
    write(a);
    if (a.size() == 0)
        for (int i = 1; i <= n; i++)
        {
            a.push_back(i);
            action(n,a);
            a.pop_back();
        }
    else
        for (int i = a.back() + 1; i <= n; i++)
        {
            a.push_back(i);
            action(n,a);
            a.pop_back();
        }
}

int main()
{
    freopen("subsets.in", "r", stdin);
    freopen("subsets.out", "w", stdout);
    int n;
    cin >> n;
    vector<int>a;
    action(n,a);
    return 0;
}
