#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int>a,b;
    vector<string>res;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        if (a.empty() || a.back() >= x) {
            a.push_back(x);
            res.push_back("push");
        } else {
            while (!a.empty() && a.back() < x) {
                b.push_back(a.back());
                a.pop_back();
                res.push_back("pop");
            }
            a.push_back(x);
            res.push_back("push");
        }
    }
    while (!a.empty()) {
        b.push_back(a.back());
        a.pop_back();
        res.push_back("pop");
    }
    for (int i = 1; i < b.size(); i++)
        if (b[i - 1] > b[i]) {
            cout << "impossible";
            return 0;
        }
    for (int i = 0; i < res.size(); i++)
        cout << res[i] << endl;
    return 0;
}
