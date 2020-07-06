#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, m;
    cin >> n >> m;
    if (n < m)
        swap(n, m);
    vector<vector<long long> > d(n, vector<long long> (1 << m));
    for (int j = 0; j < (1 << m); j++)
        d[0][j] = 1;
    for (int i = 1; i < n; i++)
        for (int j = 0; j < (1 << m); j++)
        {
            for (int k = 0; k < (1 << m); k++)
            {
                int tmp = j ^ k;
                int tmp2 = j & k;
                int check = 0;
                while (tmp != 0 && (tmp % 4 != 0) || tmp2 % 4 == 1 || tmp2 % 4 == 2)
                {
                    check++;
                    tmp /= 2;
                    tmp2 /= 2;
                }
                if (check >= m - 1)
                    d[i][j] += d[i - 1][k];
            }
        }
    /*for (intt i = 0; i < n; i++)
    {
    for (int j = 0; j < (1 << m); j++)
    cout << d[i][j] << " ";
    cout << endl;
    }*/
    //cout << "!!!";
    long long res = 0;
    for (int j = 0; j < (1 << m); j++)
        res += d[n - 1][j];
    cout << res;
}
