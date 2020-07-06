#include <bits/stdc++.h>

using namespace std;

vector <vector <int> > a, res;
string st;

int action(int left, int right)
{
    if (left > right)
        return 0;
    else
    {
        //cout << left << " " << right << endl;
        if (a[left][right] == 0)
        {
            //cout << left << " " << right << " in 0 " << endl;
            int y = right - left;
            for (int i = left; i <= right; i++)
                cout << st[i];
            return 0;
        }
        if (a[left][right] == right - left +1)
            return 1;
        //} else
        if (res[left][right] == -1) {
            cout << st[left];
            action(left + 1, right - 1);
            cout << st[right];
            return 0;
        }
        action(left, res[left][right]);
        action(res[left][right]+1, right);
    }
}

int main()
{
    cin >> st;
    a.resize(st.length());
    res.resize(st.length());
    for (int i = 0; i < st.length(); i++)
        for (int j = 0; j < st.length(); j++)
        {
            a[i].resize(st.length());
            res[i].resize(st.length());
        }
   /*for (int i = 0; i < st.length(); i++)
   {
        for (int j = 0; j < st.length(); j++)
        {
            a[i][j] = 0;
            if (i == j)
                a[i][j] = 1;
            if (i == j-1)
            {
                if (st[i] == '[' && st[j] == ']' || st[i] == '{' && st[j] == '}' || st[i] == '(' && st[j] == ')')
                {
                    a[i][j] = 0;
                    res[i][j] = i;
                }
                //else {a[i][j] = 2; res[i][j] = 0;}
            }
        }
    }
/*for (int i = 0; i < st.length(); i++){
        for (int j = 0; j < st.length(); j++){
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
*/
    for (int j = 0; j < st.length(); j++)
    {
        for (int i = j; i >=0 ; i--)
            if (i != j)
            {
                int buf = 1000000000;
                int ind = -1;
                //res[i][j] = -1;
                if (st[i] == '[' && st[j] == ']' || st[i] == '{' && st[j] == '}' || st[i] == '(' && st[j] == ')')
                {
                    ind = -1;
                    buf = a[i+1][j-1];
                }
                int min_buf = a[i][i] + a[i+1][j]; //ind = i;
                for (int p = i; p < j; p++)
                    if (a[i][p] + a[p+1][j] < buf)
                    {
                        buf = a[i][p] + a[p+1][j];
                        ind = p;
                    }
                //buf = min(buf, min_buf);
                a[i][j] = buf;
                res[i][j] = ind;
            }
            else
                a[i][j] = 1;
    }
   /* for (int i = 0; i < st.length(); i++)
   `{
        for (int j = 0; j < st.length(); j++)
            cout << a[i][j] << " ";
        cout << endl;
    }
    cout << endl;
    for (int i = 0; i < st.length(); i++)
    {
        for (int j = 0; j < st.length(); j++)
            cout << res[i][j] << " ";
        cout << endl;
    }*/
    action(0, st.length()-1);
    //cout << st.length() - a[0][ st.length() - 1];
}
