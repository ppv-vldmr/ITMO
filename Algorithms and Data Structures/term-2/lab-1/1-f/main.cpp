#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

long long n, m, a, long_column;
long long val, place, inf = LLONG_MAX-1;
vector<vector<long long>> v;

inline int get_hight()
{
    int l = n - 1;
    int i = 0;
    while (l > 0)
    {
        ++i;
        l = l >> 1;
    }
    return i;
}

void build () {
    for(long long j = 1; j <= long_column; ++j) {
        for(long long i = 1; i < n + 1; ++i) {
            if (i + (1 << (j - 1)) <= n)
                v[i][j] = min(v[i][j - 1], v[i + (1 << (j - 1))][j - 1]);
            else
                v[i][j] = v[i][j - 1];
        }
    }
}

int fl(int len) {
    if (len == 1)
        return 0;
    else
        return fl(floor(len / 2)) + 1;
}

long long get_min(long long l, long long r) {
    long long j = fl(r - l + 1);
    return min(v[l][j],v[r-(1<<j)+1][j]);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin >> n >> m >> a;
    long_column = get_hight();
    v.resize(n + 1);
    v[1].resize(long_column+1);
    v[1][0]=a;
    for(long long i = 2; i < n + 1; ++i) {
        v[i].resize(long_column+1);
        v[i][0]=(23 * v[i-1][0] + 21563) % 16714589;
    }
    build();
    long long answer, l, r, l_prev,r_prev;
    cin >> l_prev >> r_prev;
    answer = get_min(min(l_prev,r_prev),max(l_prev,r_prev));
    for(long long i = 1; i < m; ++i) {
        l = ((17*l_prev + 751 + answer + 2*i) % n) + 1;
        r = ((13*r_prev + 593 + answer + 5*i) % n) + 1;
        answer = get_min(min(l,r),max(l,r));
        l_prev = l;
        r_prev = r;
    }
    cout << l << ' ' << r << ' ' << answer;
    return 0;
}
