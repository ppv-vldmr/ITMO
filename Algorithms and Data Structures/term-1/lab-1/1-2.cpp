#include <bits/stdc++.h>

using namespace std;

int main()
{
    vector<int> a(101, 0);
    int x;
    while (cin >> x)
        a[x]++;
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < a[i]; j++)
            cout << i << " ";
    }
    return 0;
}
