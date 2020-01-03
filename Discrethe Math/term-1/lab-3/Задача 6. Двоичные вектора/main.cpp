#include <bits/stdc++.h>

using namespace std;

string to_bin(int a)
{
    string res = "";
    do
    {
        stringstream ss;
        ss << a % 2;
        a /= 2;
        res = ss.str() + res;
    } while (a > 0);
    return res;
}

string normalize(string st, int n)
{
    while (st.size() < n)
    {
        st = "0" + st;
    }
    return st;
}

int main()
{
    freopen("vectors.in", "r", stdin);
    freopen("vectors.out", "w", stdout);
    vector <string> a;
    int n, cnt = 0;
    cin >> n;
    for (int i = 0; i < pow(2,n); i++)
    {
        string st = normalize(to_bin(i), n);
        if (st.find("11") == -1)
        {
            cnt++;
            a.push_back(st);
        }
    }
    cout << cnt << endl;
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << endl;
    return 0;
}
