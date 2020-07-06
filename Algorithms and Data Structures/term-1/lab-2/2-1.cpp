#include <bits/stdc++.h>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    int k;
    long long n;
    cin >> k;
    vector<long long> a,b;
    for (int j = 0; j < k; j++) {
        cin >> n;
        switch(n) {
            case 1 : {
                long long x;
                cin >> x;
                a.push_back(x);
                if (b.size() == 0)
                    b.push_back(x);
                else
                    if (b.back() < x)
                        b.push_back(b.back());
                    else
                        b.push_back(x);
                break;
            }
            case 2 : {
                a.pop_back();
                b.pop_back();
                break;
            }
            case 3 : {
                cout << b.back() << endl;
                break;
            }
        }
    }
    return 0;
}
