#include <bits/stdc++.h>

using namespace std;

string get_next(string s)
{
    string s1 = s;
    int cnt_open = 0, cnt_close = 0;
    for (int i = s.size() - 1; i >= 0; i--)
    {
        if (s[i] == '(')
        {
            cnt_open++;
            if (cnt_close > cnt_open)
                break;
        }
        else
            cnt_close++;
    }
    //cout << s.size() - cnt_open - cnt_close << endl;
    s.erase(s.size() - cnt_open - cnt_close);
    //cout << s << endl;
    if (s == "")
        return "-";
    else
    {
        s += ")";
        s += string(cnt_open, '(');
        s += string(cnt_close - 1, ')');
        return s;
    }
}

int main()
{
    freopen("nextbrackets.in", "r", stdin);
    freopen("nextbrackets.out", "w", stdout);
    string s;
    cin >> s;
    cout << get_next(s);
    return 0;
}
