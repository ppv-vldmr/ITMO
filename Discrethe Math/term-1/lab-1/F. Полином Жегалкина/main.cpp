#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    int k = pow(2,n);
    int a[k][k];
    string b[k];
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < k; j++) {
            a[i][j] = 0;
        }
    }
    for (int i = 0; i < k; i++) {
        string st;
        int x;
        cin >> st >> x;
        b[i] = st;
        a[i][0] = x;
    }
    /*for (int i = 0; i < k; i++) {
        for (int j = 0; j < k - i; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    cout << endl;*/
    for (int j = 1; j < k; j++) {
        for (int i = k - 2; i >=0; i--) {
            a[i][j] = (a[i+1][j-1] + a[i][j-1]) % 2;
        }
    }
    /*for (int i = 0; i < k; i++) {
        for (int j = 0; j < k - i; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;*/
    for (int i = 0; i < k; i++) {
        cout << b[i] << " " << a[0][i] << endl;
    }
    return 0;
}
