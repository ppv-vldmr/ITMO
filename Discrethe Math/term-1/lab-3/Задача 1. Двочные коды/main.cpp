#include <bits/stdc++.h>

using namespace std;

string to_bin(int a)
{
    string res = "";
    do
    {
        stringstream ss;
        ss << a % 2;
        a /= 2;
        res = ss.str() + res;
    } while (a > 0);
    return res;
}

string normalize(string st, int n)
{
    while (st.size() < n)
    {
        st = "0" + st;
    }
    return st;
}

int main()
{
    ifstream fin("allvectors.in");
    ofstream fout("allvectors.out");
    int n;
    fin >> n;
    for (int i = 0; i < pow(2,n); i++)
    {
        string st = to_bin(i);
        fout << normalize(st, n) << endl;
    }
    fin.close();
    fout.close();
    return 0;
}
