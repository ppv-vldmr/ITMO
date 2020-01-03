#include <bits/stdc++.h>

using namespace std;

vector<int> nextMultiperm(vector<int> b)
{
    int i = b.size() - 2;
    while (i >= 0 && b[i] >= b[i + 1])
    i--;
    if (i >= 0)
    {
        int j = i + 1;
        while (j < b.size() - 1 && b[j + 1] > b[i])
            j++;
        swap(b[i], b[j]);
        reverse(b.begin() + i + 1, b.end());
    }
    else
    {
        for (int i = 0; i < b.size(); i++)
            b[i] = 0;
    }
    return b;
}

int main()
{
    freopen("nextmultiperm.in", "r", stdin);
    freopen("nextmultiperm.out", "w", stdout);
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++)
        cin >> a[i];
    a = nextMultiperm(a);
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    return 0;
}
