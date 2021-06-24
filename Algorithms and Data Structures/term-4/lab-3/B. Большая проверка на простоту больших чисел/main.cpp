#include <bits/stdc++.h>

using namespace std;

using ll = long long;

int n;

__int128 binPow(__int128 a, __int128 N, __int128 MD) {
    __int128 res = 1;
    while (N)
        if (N & 1) {
            res *= a;
            res %= MD;
            --N;
        } else {
            a *= a;
            a %= MD;
            N /= 2;
        }
    return res;
}

__int128 powP(__int128 a, int k) {
    __int128 ans = 1;
    for (int i = 0; i < k; i++) {
        ans *= a;
    }
    return ans;
}

bool isPrimeMillerRabin(ll c) {
    if (c == 2 || c == 3)
        return true;
    if (c == 1 || c % 2 == 0)
        return false;
    __int128 cCopy = c - 1;
    __int128 s = 0;
    while(cCopy % 2 == 0) {
        s++;
        cCopy /= 2;
    }
    mt19937 gen;
    uniform_int_distribution<long long> dis(2, c - 2);
    for (int i = 0; i < 100; i++) {
        __int128 a = dis(gen);
        __int128 x = binPow(a, cCopy, c);
        if (x == 1 || x == c - 1)
            continue;
        for (int j = 1; j < s; j++) {
            x = (x * x) % c;
            if (x == 1)
                return false;
            if (x == c - 1)
                break;
        }
        if (x != c - 1)
            return false;
    }

    return true;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        ll a;
        cin >> a;
        if (isPrimeMillerRabin(a))
            cout << "YES\n";
        else
            cout << "NO\n";
    }
}