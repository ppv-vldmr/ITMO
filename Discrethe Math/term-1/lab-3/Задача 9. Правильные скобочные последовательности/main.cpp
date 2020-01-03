#include <bits/stdc++.h>

using namespace std;

void gen(int n, int cnt_open, int cnt_close, string ans)
{
    if (cnt_open + cnt_close == 2*n)
    {
        cout << ans << endl;
        return;
    }
    if (cnt_open < n)
        gen(n, cnt_open + 1, cnt_close, ans + "(");
    if (cnt_open > cnt_close)
        gen(n, cnt_open, cnt_close + 1, ans + ")");
}

int main()
{
    freopen("brackets.in", "r", stdin);
    freopen("brackets.out", "w", stdout);
    int n;
    cin >> n;
    gen(n,0,0,"");
    return 0;
}
