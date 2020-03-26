#include <bits/stdc++.h>

//
// Created by Vladimir Popov on 03.03.2020.
//

using namespace std;

const int sm = 200000;
const int bz = 1 << 19;

struct Window
{
    int x;
    int y1;
    int y2;
    int t;
    Window (int xx = 0, int yy1 = 0, int yy2 = 0, int tt = 1)
    {
        x = xx;
        y1 = yy1;
        y2 = yy2;
        t = tt;
    }
};

struct my_pair
{
    int f;
    int s;
    my_pair(int ff = 0, int ss = 0)
    {
        f = ff;
        s = ss;
    }
};

struct Node
{

    my_pair m;
    my_pair s;
    Node ()
    {
        m.f = 0;
        m.s = 0;
        s.f = 0;
        s.s = 0;
    }

};

vector<Node> Tree(2 * bz);

void propagate(int v, int vl, int vr)
{
    if (vr - vl == 1)
    {
        return;
    }
    if (Tree[v].s.f == 0)
    {
        return;
    }
    int vm = (vl + vr) / 2;
    Tree[2 * v + 1].m.f += Tree[v].s.s;
    Tree[2 * v + 1].s.f = 1;
    Tree[2 * v + 1].s.s += Tree[v].s.s;
    Tree[2 * v + 2].m.f += Tree[v].s.s;
    Tree[2 * v + 2].s.f = 1;
    Tree[2 * v + 2].s.s += Tree[v].s.s;
    Tree[v].s.f = 0;
    Tree[v].s.s = 0;
    return;
}

void build(int v, int vl, int vr)
{
    if (vr - vl == 1)
    {
        Tree[v].m.s = vl;
        return;
    }
    int vm = (vl + vr) / 2;
    build(2 * v + 1, vl, vm);
    build(2 * v + 2, vm, vr);
    Tree[v] = Tree[2 * v + 1];
}

void sum(int v, int vl, int vr, int l, int r, int c)
{
    if (l >= vr || r <= vl)
    {
        return;
    }
    if (l <= vl && vr <= r)
    {
        Tree[v].m.f += c;
        Tree[v].s.f = 1;
        Tree[v].s.s += c;
        return;
    }
    propagate(v, vl, vr);
    int vm = (vr + vl) / 2;
    sum(2 * v + 1, vl, vm, l, r, c);
    sum(2 * v + 2, vm, vr, l, r, c);
    if (Tree[2 * v + 1].m.f > Tree[2 * v + 2].m.f)
    {
        Tree[v].m = Tree[2 * v + 1].m;
    }
    else
    {
        Tree[v].m = Tree[2 * v + 2].m;
    }
    return;
}

my_pair max(int v, int vl, int vr, int l, int r)
{
    if (l >= vr || r <= vl)
    {
        return my_pair(-1, 0);
    }
    if (l <= vl && vr <= r)
    {
        return Tree[v].m;
    }
    propagate(v, vl, vr);
    int vm = (vr + vl) / 2;
    my_pair f = max(2 * v + 1, vl, vm, l, r);
    my_pair s = max(2 * v + 2, vm, vr, l, r);
    return f.f > s.f ? f : s;
}

bool cmp(const Window& f, const Window& s)
{
    return f.x < s.x || (f.x == s.x && f.t < s.t);
}

int main()
{
    int n;
    cin >> n;
    vector<Window> Windows(2 * n);
    for (int i = 0; i < 2 * n; i += 2)
    {
        int x1, x2, y1, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        x1 += sm;
        y1 += sm;
        x2 += sm;
        y2 += sm;
        Windows[i] = Window(x1, y1, y2, 0);
        Windows[i + 1] = Window(x2, y1, y2, 1);
    }
    sort(Windows.begin(), Windows.end(), cmp);
    build(0, 0, bz);
    int max_ans = 0;
    int x = 0, y = 0;
    for (int i = 0; i < 2 * n; i++)
    {
        if (Windows[i].t == 0)
        {
            sum(0, 0, bz, Windows[i].y1, Windows[i].y2 + 1, 1);
        }
        my_pair ans = max(0, 0, bz, Windows[i].y1, Windows[i].y2 + 1);
        if (ans.f > max_ans)
        {
            max_ans = ans.f;
            x = Windows[i].x;
            y = ans.s;
        }
        if (Windows[i].t == 1)
        {
            sum(0, 0, bz, Windows[i].y1, Windows[i].y2 + 1, -1);
        }
    }
    cout << max_ans << endl << x - sm << " " << y - sm;
}