#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool comp(pair<int, int> a, pair<int, int> b) {
    if (a.first < b.first)
        return true;
    return a.first == b.first && a.second < b.second;
}

long long int cnt = 0;

vector<int> merges(vector <int> a, vector <int> b) {
    int i = 0, j = 0;
    vector<int> c(a.size() + b.size());
    while (i + j < a.size() + b.size()) {
        if (j == b.size() || (i < a.size() && a[i] <= b[j])) {
            c[i + j] = a[i];
            i++;
        } else {
            c[i + j] = b[j];
            j++;
            cnt += a.size() - i;
            //cout << endl << cnt << endl;
        }
    }
    return(c);
}

void mergesort(vector<int> &a) {
    if (a.size() > 1) {
        vector<int> x, y;
        for (long long int i = 0; i < a.size()/2; i++)
            x.push_back(a[i]);
        for (long long int i = a.size()/2; i < a.size(); i++)
            y.push_back(a[i]);
        /*for (int i = 0; i < x.size(); i++)
            cout << x[i] << " ";
        cout << endl;
        for (int i = 0; i < y.size(); i++)
            cout << y[i] << " ";
        cout << endl;*/
        mergesort(x);
        mergesort(y);
        a = merges(x,y);
    }
}

int main() {
    freopen("john.in", "r", stdin);
    freopen("john.out", "w", stdout);
    int n;
    cin >> n;
    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }
    sort(a.begin(), a.end(), comp);
    vector<int> inv(n);
    for (int i = 0; i < n; i++) {
        inv[i] = a[i].second;
    }
    mergesort(inv);
//    for (int i = 0; i < n; i++) {
//        cout << a[i].first << " " << a[i].second << "\n";
//    }
    cout << cnt;
    return 0;
}
