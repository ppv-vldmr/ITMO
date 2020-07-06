#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> l;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        if (x == 1) {
            int id;
            cin >> id;
            l.push_back(id);
        }
        if (x == 2) {
            l.erase(l.begin());
        }
        if (x == 3) {
            l.pop_back();
        }
        if (x == 4) {
            int q = 0, j = 0;
            cin >> q;
            while (l[j] != q) {
                j++;
            }
            cout << j << endl;
        }
        if (x == 5) {
            cout << l.front() << endl;
        }
    }
    return 0;
}
