#include <bits/stdc++.h>

using namespace std;

struct allIn
{
    int value;
    int mini;
    int maxi;
    int cnt;
};

int change(vector<allIn> &a, int x)
{
    int x1 = x;
    while (a[x1].value != x1)
    {
        x1 = a[x1].value;
    }
    while (a[x].value != x)
    {
        x = a[x].value;
        a[x].value = x1;
    }
    return x;
}

void unite(vector<allIn> &a, int x, int y)
{
    int f = change(a, x), s = change(a, y);
    if (a[f].value == a[s].value)
        return;
    if (a[f].cnt > a[s].cnt)
        swap(f,s);
    a[f].value = s;
    a[s].mini = min(a[s].mini, a[f].mini);
    a[s].maxi = max(a[s].maxi, a[f].maxi);
    a[s].cnt += a[f].cnt;
}

int main()
{
    int n;
    cin >> n;
    vector<allIn> a(n);
    for (int i = 0; i < n; i++)
    {
        a[i].value = i;
        a[i].mini = i;
        a[i].maxi = i;
        a[i].cnt = 1;
    }
    string st;
    while (cin >> st)
    {
        if (st == "union")
        {
            int x, y;
            cin >> x >> y;
            unite(a, x - 1, y - 1);
        }
        else
        {
            int x;
            cin >> x;
            int c = change(a, x - 1);
            cout << a[c].mini + 1 << " " << a[c].maxi + 1 << " " << a[c].cnt << endl;
        }
    }
}
