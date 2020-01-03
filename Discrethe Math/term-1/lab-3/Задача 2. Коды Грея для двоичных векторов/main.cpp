#include <bits/stdc++.h>

using namespace std;

void action(vector <string> &g, int n)
{
    if (g[0].size() == n)
    {
        for (int i = 0; i < g.size(); i++)
            cout << g[i] << endl;
    }
    else
    {
        int x = g.size();
        for (int i = 0; i < x; i++)
            g.push_back(g[x - i - 1]);
        for (int i = 0; i < x; i++)
        {
            g[i] = "0" + g[i];
            g[i + x] = "1" + g[i + x];
        }
        action(g, n);
    }
}

int main()
{
    freopen("gray.in", "r", stdin);
    freopen("gray.out", "w", stdout);
    int n;
    cin >> n;
    vector <string> g(2);
    g[0] = "0"; g[1] = "1";
    action(g, n);
    return 0;
}
