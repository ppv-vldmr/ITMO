#include <bits/stdc++.h>

using namespace std;

bool check(vector<string> a, string st)
{
    for (int i = 0; i < a.size(); i++)
        if (a[i] == st) return true;
    return false;
}

int main()
{
    freopen("lzw.in", "r", stdin);
    freopen("lzw.out", "w", stdout);
    string ex = "abcdefghijklmnopqrstuvwxyz";
    vector<string> a;
    for (int i = 0; i < ex.size(); i++)
        a.push_back(ex.substr(i,1));
    string st;
    getline(cin,st);
    int i = 0, q = 0, j = 0;
    string s = "", s1 = "";
    while (i < st.size())
    {
        s = s1 + st[i];
        j = 0;
        while (j < a.size() && (a[j] != s))
            j++;
        if (a[j] == s)
        {
            s1 = a[j];
            q = j;
            i++;
        } else {
            a.push_back(s);
            cout << q << " ";
            s1 = "";
        }
    }
    cout << j;
    /*cout << endl;
    for (int i = 0; i < a.size(); i ++)
        cout << a[i] << " ";
    cout << endl;*/
    return 0;
}
