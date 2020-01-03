#include <bits/stdc++.h>

using namespace std;

long long d[40][21],n;

long long get_number(string st)
{
	vector<char> ch;
	long long depth = 0, num = 0;
	for (long long i = n * 2 - 1; i >= 0; i--)
	{
		long long tmp = 0;
		switch (st[2 * n - 1 - i])
        {
            case '(':
            {
                ch.push_back('(');
                depth++;
                break;
            }
            case ')':
            {
                if (depth + 1 <= n)
                    tmp = d[i][depth + 1] << ((i - depth - 1) / 2);
                else
                    tmp=0;
                num += tmp;
                ch.pop_back();
                depth--;
                break;
            }
            case '[':
            {
                if (depth + 1 <= n)
                    tmp = d[i][depth + 1] << ((i - depth - 1) / 2);
                else
                    tmp = 0;
                num += tmp;
                if (depth - 1 >= 0)
                    tmp = d[i][depth - 1] << ((i - depth + 1) / 2);
                else
                    tmp = 0;
                if (ch.size() > 0)
                    if (ch.back() == '[')
                        tmp = 0;
                num += tmp;
                ch.push_back('[');
                depth++;
                break;
            }
            case ']':
            {
                if (depth + 1 <= n)
                    tmp = d[i][depth + 1] << ((i - depth - 1) / 2);
                else
                    tmp = 0;
                num += tmp;
                num += tmp;
                if (depth - 1 >= 0)
                    tmp = d[i][depth - 1] << ((i - depth + 1) / 2);
                else
                    tmp = 0;
                if (ch.size() > 0)
                    if (ch.back() == '[')
                        tmp = 0;
                num += tmp;
                ch.pop_back();
                depth--;
                break;
            }
		}
	}
	return num;
}

int main()
{
    freopen("brackets2num2.in", "r", stdin);
    freopen("brackets2num2.out", "w", stdout);
    string st;
	cin >> st;
	n=st.size()/2;
	d[0][0]=1;
	for(long long i = 1; i < n * 2; i++)
	{
		for(long long j = 0; j <= n; j++)
		{
			if (j - 1 >= 0)
                d[i][j] += d[i - 1][j - 1];
			if (j + 1 <= n)
                d[i][j] += d[i - 1][j + 1];
		}
	}
	cout << get_number(st);
	return 0;

}
