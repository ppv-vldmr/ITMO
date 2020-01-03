#include <bits/stdc++.h>

using namespace std;

int main()
{
    freopen("bwt.in", "r", stdin);
    freopen("bwt.out", "w", stdout);
    string st;
    getline(cin, st);
    vector<string>a;
    for (int i = 0; i < st.size(); i++)
    {
        string s = st.substr(0,1);
        st.erase(0,1);
        st += s;
        a.push_back(st);
    }
    /*for (int i = 0; i < a.size(); i++)
    {
        cout << a[i] << " ";
    }
    cout << endl;*/
    sort(a.begin(),a.end());
    /*for (int i = 0; i < a.size(); i++)
    {
        cout << a[i] << " ";
    }
    cout << endl;*/
    for (int i = 0; i < a.size(); i++)
        cout << a[i].substr(a[i].size() - 1);
    return 0;
}
