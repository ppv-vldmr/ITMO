#include <bits/stdc++.h>

using namespace std;

void action(vector <string> &g, int n, int k)
{
    if (g[0].size() == n)
    {
        for (int i = 0; i < g.size(); i++)
            cout << g[i] << endl;
    }
    else
    {
        vector <string> g1 = g, g2 = g;
        reverse(g1.begin(), g1.end());
        for (int i = 0; i < g.size(); i++)
            g[i] += "0";
        for (int i = 1; i < k; i++) {
            if (i % 2 == 0)
            {
                g.insert(g.end(), g2.begin(), g2.end());

            }
            else
            {
                g.insert(g.end(),g1.begin(),g1.end());
            }
            for (int j = 0; j < g2.size(); j++)
            {
                stringstream ss;
                ss << i;
                g[g.size() - 1 - j] += ss.str();
            }
        }
        /*for (int i = 0; i < g.size(); i++)
            cout << g[i] << " ";
        cout << endl;*/
        action(g, n, k);
    }
}

int main()
{
    freopen("telemetry.in", "r", stdin);
    freopen("telemetry.out", "w", stdout);
    int n, k;
    cin >> n >> k;
    vector <string> g;
    for (int i = 0; i < k; i++)
    {
        stringstream ss;
        ss << i;
        g.push_back(ss.str());
    }
    action(g, n, k);
    return 0;
}
