#include <bits/stdc++.h>

using namespace std;

int strtoint(string st)
{
    return atoi(st.c_str());
}

vector<int> nextPartition(vector<int> a)
{
    a[a.size()-1]--;
    a[a.size()-2]++;
    if (a[a.size()-2] > a[a.size()-1])
    {
         a[a.size() - 2] += a[a.size()-1];
         a.pop_back();
    }
    else
        while (a[a.size() - 2] * 2 <= a[a.size() - 1])
    {
        a.push_back(a[a.size() - 1] - a[a.size() - 2]);
        a[a.size()-2] = a[a.size()-3];
    }
    return a;
}

int main()
{
    //freopen("nextpartition.in", "r", stdin);
    //freopen("nextpartition.out", "w", stdout);
    string st;
    cin >> st;
    int x = st.find("=");
    int n = strtoint(st.substr(0,x));
    if (st.substr(x + 1) == st.substr(0, x))
    {
        cout << "No solution";
        return 0;
    }
    else
    {
        vector<int> a;
        st = st.substr(x + 1);
        do
        {
            x = st.find("+");
            a.push_back(strtoint(st.substr(0,x)));
            st = st.substr(x + 1);
        } while (x != -1);
        a = nextPartition(a);
        cout << n << "=";
        for (int i = 0; i < a.size() - 1; i++)
            cout << a[i] << "+";
        cout << a.back();
    }
    return 0;
}
