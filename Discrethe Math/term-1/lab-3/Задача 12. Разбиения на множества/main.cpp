#include <bits/stdc++.h>

using namespace std;

int n;

void write(vector<vector<int> > a)
{
    for (int i = 0; i < a.size(); i++)
    {
        for (int j = 0; j < a[i].size(); j++)
            cout << a[i][j] << " ";
        cout << endl;
    }
}

void write(vector<bool> a)
{
    for (int j = 0; j < a.size(); j++)
        cout << a[j] << " ";
    cout << endl;
}

int cntNumbers(vector<vector<int> > sets)
{
    int res = 0;
    for (int i = 0; i < sets.size(); i++)
        for (int j = 0; j < sets[i].size(); j++)
        if (sets[i][j] != 0)
            res++;
    return res;
}

bool checkSets(vector<vector<int> > sets)
{
    for (int i = 0; i < sets.size(); i++)
        if (sets[i].size() == 0)
            return false;
        else
            for (int j = 1; j < sets[i].size(); j++)
                if (sets[i][j - 1] >= sets[i][j])
                    return false;
    if (cntNumbers(sets) == n)
        return true;
    else
        return false;
}

void action(vector<vector<int> > sets, vector<int> a, vector<bool> used)
{
    if (cntNumbers(sets) == n)
    {
        if (checkSets(sets))
        {
            //cout << "----------" << endl;
            write(sets);
            //cout << "----------" << endl;
            cout << endl;
        }
        else
            return;
    }
    else
    {
        int i = 0;
        while (i < used.size() && used[i])
            i++;
        int x = 0;
        while (x < sets.size() && sets[x].size() != 0)
            x++;
        if (x == sets.size())
            x--;
        used[i] = true;
        for (int j = 0; j <= x; j++)
        {
            sets[j].push_back(a[i]);
            //write(sets);
            action(sets,a,used);
            sets[j].pop_back();
        }
        used[i] = false;
    }
}

int main()
{
    freopen("part2sets.in", "r", stdin);
    freopen("part2sets.out", "w", stdout);
    int k;
    cin >> n >> k;
    vector<int> a(n);
    for (int i = 0; i < a.size(); i++)
        a[i] = i + 1;
    vector<vector<int> > sets;
    sets.resize(k);
    vector<bool> used(n, false);
    action(sets,a,used);
    return 0;
}
