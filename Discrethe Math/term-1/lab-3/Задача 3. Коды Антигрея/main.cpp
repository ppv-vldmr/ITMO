#include <bits/stdc++.h>

using namespace std;

string to_three(int x)
{
    string res = "";
    do
    {
        stringstream ss;
        ss << x % 3;
        x /= 3;
        res = ss.str() + res;
    } while (x > 0);
    return res;
}

string digitCircleShift(string &st)
{
    string res = "";
    for (int i = 0; i < st.size(); i++)
    {
        string s = st.substr(i,1);
        int x = stoi(s);
        x = (x + 1) % 3;
        stringstream ss;
        ss << x;
        res += ss.str();
    }
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
    freopen("antigray.in", "r", stdin);
    freopen("antigray.out", "w", stdout);
    int n;
    cin >> n;
    vector <string> a;
    for (int i = 0; i < pow(3, n); i++)
    {
        a.push_back(normalize(to_three(i), n));
    }
    for (int i = 0; i < a.size()/3; i++)
    {
        cout << a[i] << endl;
        string x = digitCircleShift(a[i]);
        cout << x << endl;
        a[i] = x;
        x = digitCircleShift(a[i]);
        cout << x << endl;
    }
    return 0;
}
