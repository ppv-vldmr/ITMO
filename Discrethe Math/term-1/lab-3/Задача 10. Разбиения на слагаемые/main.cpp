#include <bits/stdc++.h>

using namespace std;

int check(vector<int> a)
{
    int sum = 0;
    for (int i = 0; i < a.size(); i++)
        sum += a[i];
    return sum;
}

void write(vector<int> a)
{
    string st = "";
    for (int i = 0; i < a.size(); i++)
        if (i < a.size() - 1)
            cout << a[i] << "+";
        else
            cout << a[i] << endl;
}

void action(int n, vector<int> a)
{
    if (check(a) == n)
    {
        write(a);
        return;
    }
    if (check(a) > n)
        return;
    if (a.size() == 0)
        for (int i = 1; i <= n; i++)
        {
            a.push_back(i);
            action(n,a);
            a.pop_back();
        }
    else
        for (int i = a.back(); i <= n; i++)
        {   a.push_back(i);
            action(n,a);
            a.pop_back();
        }
}

int main()
{
    freopen("partition.in", "r", stdin);
    freopen("partition.out", "w", stdout);
    int n;
    cin >> n;
    vector<int>a;
    action(n,a);
    return 0;
}
