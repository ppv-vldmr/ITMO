#include <bits/stdc++.h>

using namespace std;

long long factorial(int n)
{
    long long sum = 1;
    for (int i  = 1; i <= n; i++)
        sum *= i;
    return sum;
}

int notUsed(vector<bool> &check, int x) {

    int j, pos = 0;
    for (j = 1; j < check.size(); j++) {
        if (!check[j]) pos++;
        if (x == pos)
            break;
    }
    return j;
}

int main()
{
    freopen("num2perm.in", "r", stdin);
    freopen("num2perm.out", "w", stdout);
    long long n, k;
    cin >> n >> k;
    k++;
    vector<int> a;
    vector<bool> check(n+1, false);
    for (int i = 0; i < n; i++)
    {
        long long x = (k - 1) / factorial(n - i - 1) + 1;
        long long y = notUsed(check, x);
        a.push_back(y);
        check[y] = true;
        k = (k - 1) % factorial(n - i - 1) + 1;
    }
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    return 0;
}
