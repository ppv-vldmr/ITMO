#include <bits/stdc++.h>

using namespace std;

void write(vector<int> a)
{
    for (int i = 0; i < a.size() - 1; i++)
        cout << a[i] << " ";
    cout << a.back();
}

void nextPermutation(vector<int> a)
{
    for (int i = a.size() - 2; i >= 0; i--)
    {
        if (a[i] < a[i + 1])
        {
            int mini = i + 1;
            for (int j = i + 1; j < a.size(); j++)
                if (a[j] < a[mini] && a[j] > a[i])
                    mini = j;
            swap(a[i], a[mini]);
            reverse(a.begin() + i + 1, a.end());
            write(a);
            return;
        }
    }
    for (int i = 0; i < a.size(); i++)
        a[i] = 0;
    write(a);
}

void prevPermutation(vector<int> a)
{
    for (int i = a.size() - 2; i >= 0; i--)
    {
        if (a[i] > a[i + 1])
        {
            int maxi = i + 1;
            for (int j = i + 1; j < a.size(); j++)
                if (a[j] > a[maxi] && a[j] < a[i])
                    maxi = j;
            swap(a[i], a[maxi]);
            reverse(a.begin() + i + 1, a.end());
            write(a);
            return;
        }
    }
    for (int i = 0; i < a.size(); i++)
        a[i] = 0;
    write(a);
}

int main()
{
    freopen("nextperm.in", "r", stdin);
    freopen("nextperm.out", "w", stdout);
    int n;
    cin >> n;
    vector<int> a(n);
    for(int i = 0; i < n; i++)
        cin >> a[i];
    prevPermutation(a);
    cout << endl;
    nextPermutation(a);
    return 0;
}
