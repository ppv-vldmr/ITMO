#include <bits/stdc++.h>

using namespace std;
int n;
vector <vector <vector <int> > > stars;

void add(int x, int y, int z, int k)
{
    for (int i = x; i < n; i = i | (i + 1))
        for (int j = y; j < n; j = j | (j + 1))
            for (int m = z; m < n; m = m | (m + 1))
                stars[i][j][m] += k;
    return;
}

int sum(int x, int y, int z)
{
    long long res = 0;
    for (int i = x; i >= 0; i = (i & (i + 1)) - 1)
        for (int j = y; j >= 0; j = (j & (j + 1)) - 1)
            for (int k = z; k >= 0; k = (k & (k + 1)) - 1)
                res += stars[i][j][k];
    return res;
}

int check(int x1, int y1, int z1, int x2, int y2, int z2)
{
    long long res;
    res =  sum(x2, y2, z2) + sum(x1 - 1, y1 - 1, z2)
                     + sum(x1 - 1, y2, z1 - 1) + sum(x2, y1 - 1, z1 - 1)
                     - sum(x2, y1 - 1, z2) - sum(x2, y2, z1 - 1) - sum(x1 - 1, y2, z2)
                     - sum(x1 - 1, y1 - 1, z1 - 1);
    return res;
}

int main()
{
    cin >> n;
    stars.resize(n,vector <vector <int> > (n,vector <int> (n, 0)));
    int m;
    while (cin >> m)
    {
        if (m == 1) 
        {
            int x, y, z, k;
            cin >> x >> y >> z >> k;
            add(x, y, z, k);
        } 
        else if (m == 2)
        {
            int x1, y1, z1, x2, y2, z2;
            cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;
            cout << check(x1, y1, z1, x2, y2, z2) << endl;
        } 
        else
            break;
    }
    return 0;
}