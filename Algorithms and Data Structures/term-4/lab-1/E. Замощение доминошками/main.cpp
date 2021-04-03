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
#include <cmath>

using namespace std;

typedef long long ll;
typedef long double ld;
typedef pair<ll, ll> pl;
typedef pair<ld, ld> pd;
typedef unsigned long long ull;
typedef unsigned int uint;

const ll N = 1e6 + 10;
const int INF = 1e9 + 100;
const ll NO_EDGE = 100000;
const ll M = 1e3 + 100;
const ull prime = 31;
const size_t DAY = 1440;
const int dx[] = {0, 1, 0, -1};
const int dy[] = {1, 0, -1, 0};

#define x first
#define y second
#define pb push_back

vector<vector<int>> g;
vector<int> matching;
vector<bool> used;
int n, m;
double maxspeed;
vector<vector<bool>> board;

bool free(pair<int, int> p)
{
    return p.x >= 0 && p.x < n && p.y >= 0 && p.y < m && !board[p.x][p.y];
}

bool dfs(int v)
{
    if (used[v])
    {
        return false;
    }

    used[v] = true;
    for (const auto to : g[v])
    {
        if (matching[to] == -1)
        {
            matching[to] = v;
            return true;
        } else
        {
            bool res = dfs(matching[to]);
            if (res)
            {
                matching[to] = v;
                return true;
            }
        }
    }

    return false;
}

int main()
{
    int a, b;
    cin >> n >> m >> a >> b;

    vector<vector<int>> num;

    board.resize(n);
    num.resize(n);
    int cnt = 0;
    int cntFree = 0;

    for (size_t i = 0; i < n; i++)
    {
        string s;
        cin >> s;
        board[i].resize(s.size());
        num[i].resize(s.size());

        for (size_t j = 0; j < s.size(); j++)
        {
            num[i][j] = cnt;
            cnt++;
            if (s[j] == '.')
            {
                board[i][j] = 1;
            } else
            {
                cntFree++;
            }
        }
    }


    g.resize(cnt);
    matching.resize(cnt, -1);
    used.resize(cnt);

    for (size_t i = 0; i < n; i++)
    {
        for (size_t j = 0; j < m; j++)
        {
            if (free({i, j}) && (i + j) % 2)
            {
                for (size_t k = 0; k < 4; k++)
                {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (free({x, y}))
                    {
                        g[num[i][j]].pb(num[x][y]);
                    }
                }
            }
        }
    }


    for (size_t i = 0; i < cnt; i++)
    {
        used.clear();
        used.resize(cnt, false);
        dfs(i);
    }


    vector<pair<int, int>> ans;


    for (size_t i = 0; i < cnt; i++)
    {
        if (matching[i] != -1)
        {
            ans.pb({matching[i], i});
        }
    }

    int sz = static_cast<int>(ans.size());

    cout << min(cntFree * b, sz * a + (cntFree - sz * 2) * b);

    return 0;
}