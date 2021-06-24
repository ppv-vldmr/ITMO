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

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;
typedef unsigned long long ull;
typedef unsigned int uint;
typedef tuple<vector<ll>, vector<ll>, vector<ll>> tuple3v;

const size_t N = 2e6 + 100;
const ll INF = 1e18 + 100;
const ll M = 2000;

#define x first
#define y second
#define pb push_back

ll gcd(ll a, ll b)
{
    if (b == 0)
    {
        return a;
    }

    return gcd(b, a % b);
}

// n -> (x, y) : n = 2^x * y
pair<ll, ll> to2Degs(ll n)
{
    ll cnt = 0;
    while (n % 2 == 0)
    {
        cnt++;
        n /= 2;
    }

    return {cnt, n};
}

ll bin_pow(ll a, ll n, ll mod)
{
    if (n == 0) return 1;

    if (n % 2 == 0)
    {
        ll b = bin_pow(a, n / 2, mod);
        return (b * b) % mod;
    } else
    {
        return (a * bin_pow(a, n - 1, mod)) % mod;
    }
}

ll gcd_ex(ll a, ll b, ll &x, ll &y)
{
    if (!a)
    {
        x = 0;
        y = 1;
        return b;
    }

    ll x1, y1;
    ll ans = gcd_ex(b % a, a, x1, y1);
    x = y1 - (b / a) * x1;
    y = x1;

    return ans;
}

ll rev_element(ll a, ll mod)
{
    ll x, y;
    ll gcd = gcd_ex(a, mod, x, y);

    assert(gcd == 1);

    x = (x % mod + mod) % mod;
    return x;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    ll n, e, c;
    cin >> n >> e >> c;

    ll f;
    for (ll i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
        {

            f = (i - 1) * (n / i - 1);
            break;
        }
    }

    cout << bin_pow(c, rev_element(e, f), n);
    return 0;
}
