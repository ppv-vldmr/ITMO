#include <bits/stdc++.h>

using namespace std;

int main()
{
    freopen("mtf.in", "r", stdin);
    freopen("mtf.out", "w", stdout);
    string st, ex = "abcdefghijklmnopqrstuvwxyz";
    getline(cin, st);
    for (int i = 0; i < st.size(); i++)
    {
        string s = st.substr(i, 1);
        int f = ex.find(s);
        cout << f + 1 << " ";
        s = ex.substr(f,1);
        ex.erase(f, 1);
        ex = s + ex;
    }
    return 0;
}
