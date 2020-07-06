#include <bits/stdc++.h>

using namespace std;

long long int cnt = 0;

vector<long long int> merges(vector <long long int> a, vector <long long int> b) {
    long long int i = 0, j = 0;
    vector<long long int> c(a.size() + b.size());
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

void mergesort(vector<long long int> &a) {
    if (a.size() > 1) {
        vector<long long int> x, y;
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

int main()
{
    long long int n;
    cin >> n;
    vector<long long int> a(n), b, c;
    for (long long int i = 0; i < n; i++)
        cin >>a[i];
    /*for (int i = 0; i < n/2; i++) {
        int x;
        cin >> x;
        a.push_back(x);
    }
    for (int i = n/2; i < n; i++) {
        int x;
        cin >> x;
        b.push_back(x);
    }*/
    mergesort(a);
    /*for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";*/
    cout << cnt << endl;
    return 0;
}
