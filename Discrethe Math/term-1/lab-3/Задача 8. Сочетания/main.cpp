#include <bits/stdc++.h>

using namespace std;

void create(int n, int k, vector<int> a, vector<int> b)
{
    if (b.size() == k)
    {
        for (int i = 0; i < b.size(); i++)
            cout << b[i] << " ";
        cout << endl;
    }
    else
    {
        for (int i = 0; i < n; i++)
        {
            if (a[i] == 0 && (b.size() == 0 || b.back() <= i + 1))
            {
                b.push_back(i+1);
                a[i]++;
                create(n,k,a,b);
                b.pop_back();
                a[i]--;
            }
        }
    }

}

int main()
{
    freopen("choose.in", "r", stdin);
    freopen("choose.out", "w", stdout);
    int n, k;
    cin >> n >> k;
    vector<int> a(n),b;
    create(n,k,a,b);
    return 0;
}
