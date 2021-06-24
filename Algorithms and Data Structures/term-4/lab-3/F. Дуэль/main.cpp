#define _FORTIFY_SOURCE 0
#pragma GCC optimize("Ofast")
#pragma GCC optimize("no-stack-protector")
#pragma GCC optimize("unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,popcnt,abm,mmx,tune=native")
#pragma GCC optimize("fast-math")

#include <iostream>
#include <math.h>
#include <algorithm>
#include <iomanip>
#include <vector>
#include <set>
#include <map>
#include <deque>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <random>
#include <cassert>
#include <complex>

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;
typedef unsigned long long ull;
typedef unsigned int uint;
typedef tuple<vector<ll>, vector<ll>, vector<ll>> tuple3v;
typedef complex<long double> comp;

const size_t N = 2e6 + 100;
const ll INF = 1e18 + 100;
const ll M = 2000;
const long double pi = acos(-1);
const long double okr = 0.4999999;

#define x first
#define y second
#define pb push_back

void fft(vector<comp> &a, int rev)
{
    if (a.size() == 1) return;

    vector<comp> a0(a.size() / 2), a1(a.size() / 2);

    for (size_t i = 0; i < a.size(); i += 2)
    {
        a0[i / 2] = a[i];
    }

    for (size_t i = 1; i < a.size(); i++)
    {
        a1[i / 2] = a[i];
    }

    fft(a0, rev);
    fft(a1, rev);


    long double angle = 2 * pi / a.size();
    if (rev) angle = -angle;

    comp z(1), root = comp(cos(angle), sin(angle));

    size_t h = a.size() / 2;
    for (size_t i = 0; i < h; i++)
    {
        a[i] = a0[i] + z * a1[i];
        a[i + h] = a0[i] - z * a1[i];
        z *= root;
        if (rev)
        {
            a[i] /= 2;
            a[i + h] /= 2;
        }
    }
}

ll find_pow2(ll a, ll b)
{
    size_t n = 1;
    while (n < a || n < b)
    {
        n = n << 1;
    }

    return n << 1;
}

void normalize(vector<ll> &p)
{
    ll c = 0;
    for (size_t i = 0; i < p.size(); i++)
    {
        p[i] += c;
        c = p[i] / 10;
        p[i] %= 10;
    }

    while (p.back() == 0) p.pop_back();
    reverse(p.begin(), p.end());
}

vector<ll> multiply(const vector<ll> &a, const vector<ll> &b)
{
    vector<comp> a1(a.begin(), a.end());
    vector<comp> b1(b.begin(), b.end());

    size_t sz = find_pow2(a.size(), b.size());

    a1.resize(sz);
    b1.resize(sz);

    fft(a1, 0);
    fft(b1, 0);

    vector<comp> c(sz);
    for (size_t i = 0; i < a1.size(); i++)
    {
        c[i] = a1[i] * b1[i];
    }

    fft(c, 1);

    vector<ll> ans(sz);
    for (size_t i = 0; i < c.size(); i++)
    {
        ans[i] = (ll)(c[i].real() + okr);
    }

    return ans;
}

vector<ll> to_vector(string s)
{
    vector<ll> res(s.size());
    for (size_t i = 0; i < s.size(); i++)
    {
        res[i] = s[i] - '0';
    }

    return res;
}

int main()
{
//    freopen("duel.in", "r", stdin);
//    freopen("duel.out", "w", stdout);


    string s;
    cin >> s;

    vector<ll> res = multiply(to_vector(s), to_vector(s));
    ll cnt = 0;

    for (size_t i = 0; i < s.size(); i++)
    {
        if (s[i] == '1')
        {
            cnt += res[2 * i] / 2;
        }
    }

    cout << cnt << endl;
    return 0;
}