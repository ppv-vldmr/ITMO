#include <bits/stdc++.h>

using namespace std;

vector<int> nextChoose(vector<int> a, int n, int k)
{
    vector<int> b = a;
    b.push_back(n + 1);
    int i = k - 1;
    while (i >= 0 && b[i + 1] - b[i] < 2)
        i--;
    if (i >= 0)
    {
        b[i]++;
        for (int j = i + 1; j < k; j++)
            b[j] = b[j - 1] + 1;
            for (int i = 0; i < k; i++)
                a[i] = b[i];
    }
    else
    {
        a.resize(1);
        a[0] = -1;
    }
    return a;
}

int main()
{
    freopen("nextchoose.in", "r", stdin);
    freopen("nextchoose.out", "w", stdout);
    int n, k;
    cin >> n >> k;
    vector<int> a(k);
    for (int i = 0; i < k; i++)
        cin >> a[i];
    a = nextChoose(a,n,k);
    for(int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    return 0;
}
