//
// Created by Владимир on 24.06.2021.
//

#include <bits/stdc++.h>

using namespace std;

int main() {
    int n;
    cin >> n;
    for (int i = 2; i < sqrt(n) + 1; i++) {
        while (n % i == 0) {
            cout << i << " ";
            n /= i;
        }
    }
    if (n != 1)
        cout << n;
}
