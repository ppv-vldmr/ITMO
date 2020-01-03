#include <bits/stdc++.h>

using namespace std;

void create(int n, vector<int> a, vector<int> b)
{
    if (b.size() == n)
    {
        for (int i = 0; i < b.size(); i++)
            cout << b[i] << " ";
        cout << endl;
    }
    else
    {
        for (int i = 0; i < n; i++)
        {
            if (a[i] == 0)
            {
                b.push_back(i+1);
                a[i]++;
                create(n,a,b);
                b.pop_back();
                a[i]--;
            }
        }
    }

}

int main()
{
    freopen("permutations.in", "r", stdin);
    freopen("permutations.out", "w", stdout);
    int n;
    cin >> n;
    vector<int> a(n),b;
    create(n,a,b);
    return 0;
}
