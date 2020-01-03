#include <bits/stdc++.h>

using namespace std;

int to_dec(string st)
{
    int s = 0;
    for (int i = st.size() - 1; i >= 0; i--)
    {
        string x = st.substr(i,1);
        if (x == "1") s += pow(2, st.size() - 1 - i);
    }
    return s;
}

int main()
{
    freopen("chaincode.in", "r", stdin);
    freopen("chaincode.out", "w", stdout);
    int n;
    cin >> n;
    vector <string> a;
    vector <int> c(pow(2,n));
    string st = "";
    while (st.size() < n)
    {
        st += "0";
    }
    while (true)
    {
        st = st.substr(1);
        int x1 = to_dec(st + "1"), x2 = to_dec(st + "0");
        if (c[x1] == 0)
        {
            c[x1]++;
            a.push_back(st + "1");
            st += "1";
        }
        else
            if (c[x2] == 0)
            {
                c[x2]++;
                a.push_back(st + "0");
                st += "0";
            }
            else
                break;
    }
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << endl;
    return 0;
}
