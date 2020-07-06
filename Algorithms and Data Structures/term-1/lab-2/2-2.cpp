#include <bits/stdc++.h>

using namespace std;

void write(vector<int> &a) {
    for (int j = 0; j < a.size(); j++)
        cout << a[j] << " ";
    cout << endl;
}

int main()
{
    int n, x;
    cin >> n;
    int cnt = 0;
    vector<int> a, b;
    for (int i = 0; i < n; i++) {
        cin >> x;
        if (a.size() == 0) {
            b.push_back(1);
            a.push_back(x);
        } else
            if (a.back() == x) {
                b[b.size() - 1]++;
            } else {
                if (b.back() >= 3) {
                    cnt += b.back();
                    b.pop_back();
                    a.pop_back();
                }
                if (a.size() == 0 || a.back() != x) {
                    b.push_back(1);
                    a.push_back(x);
                } else
                    b[b.size() - 1]++;
            }
        /*write(a);
        write(b);*/
    }
    if (b.back() >= 3)
        cnt += b.back();
    cout << cnt;
    return 0;
}
