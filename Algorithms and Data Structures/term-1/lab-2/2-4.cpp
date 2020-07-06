#include <bits/stdc++.h>

using namespace std;

struct myQueue
{
    vector<int> l,r;
    void add(int x)
    {
        r.push_back(x);
    }
    int removeMy()
    {
        if (l.empty())
        while (!r.empty())
        {
            l.push_back(r.back());
            r.pop_back();
        }
        int temp = l.back();
        l.pop_back();
        return temp;
    }
    void write()
    {
        for (int i = 0; i < l.size(); i++)
        {
            cout << l[i] << " ";
        }
        for (int i = 0; i < r.size(); i++)
        {
            cout << r[i] << " ";
        }
        cout << endl;
    }
};

int main()
{
    int n;
    cin >> n;
    myQueue q1, q2;
    for (int i = 0; i < n; i++)
    {
        char c;
        cin >> c;
        if (c == '+')
        {
            int x;
            cin >> x;
            q2.add(x);
        }
        while (q1.l.size() + q1.r.size() < q2.l.size() + q2.r.size())
            q1.add(q2.removeMy());
        if (c == '-')
        {
            cout << q1.removeMy() << endl;
        }
        if (c == '*')
        {
            int x;
            cin >> x;
            q2.l.push_back(x);
        }
        while (q1.l.size() + q1.r.size() < q2.l.size() + q2.r.size())
            q1.add(q2.removeMy());
        //q1.write();
        //q2.write();
    }
    return 0;
}
