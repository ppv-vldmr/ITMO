#include <bits/stdc++.h>

using namespace std;

void get_prev_next(vector<int> cur)
{
    vector<int> prev = cur;
    int i = prev.size() - 1;
    while(i >= 0 && prev[i] != 1)
        i--;
    if (i < 0)
        cout << "-" << endl;
    else
    {
        prev[i] = 0;
        for (int j = i + 1; j < prev.size(); j++)
            prev[j] = 1;
        for (int j = 0; j < prev.size(); j++)
            cout << prev[j];
        cout << endl;
    }
    vector<int> next = cur;
    i = next.size() - 1;
    int tmp = 1;
    do
    {
        next[i] += tmp;
        if (next[i] == 2)
        {
            next[i] = 0;
            i--;
        }
        else
            tmp = 0;
    } while (i >= 0 && tmp != 0);
    if (i < 0)
        cout << "-" << endl;
    else
        for (int j = 0; j < next.size(); j++)
            cout << next[j];
}

int main()
{
    freopen("nextvector.in", "r", stdin);
    freopen("nextvector.out", "w", stdout);
    vector<int>cur;
    string st;
    cin >> st;
    for (int i = 0; i < st.size(); i++)
        cur.push_back(atoi(st.substr(i,1).c_str()));
    get_prev_next(cur);
    return 0;
}
