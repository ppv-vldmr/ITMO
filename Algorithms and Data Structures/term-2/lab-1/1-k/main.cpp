#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <string>

using namespace std;

long long mas[3000000];
int n, m;
long long val, place, inf = LLONG_MAX-1;

void setFreePlace(int v, int tl, int tr, int pos)
{
    if (tl > tr || tr < pos || tl > pos)
        return;
    if (tl == tr)
    {
        mas[v] = val;
        return;
    }
    int tmp = (tl + tr) >> 1;
    if (pos <= tmp)
        setFreePlace((v << 1) + 1, tl, tmp, pos);
    else
        setFreePlace((v << 1) + 2, tmp + 1, tr, pos);
    mas[v] = min(mas[(v << 1) + 1], mas[(v << 1) + 2]);
}

long long min(int v, int tl, int tr,int l, int r)
{
    if (l > r)
        return inf;
    if (tl > tr || tr < l || tl > r)
        return inf;
    if (l <= tl && tr <= r)
        return mas[v];
    int tmp = (tl + tr) >> 1;
    return min(min((v << 1) + 1, tl, tmp, l, min(r, tmp)), min((v << 1) + 2, tmp + 1, tr, max(l, tmp + 1), r));
}

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

void build (int v, int tl, int tr) {
    if (tl == tr)
        mas[v] = tl;
    else {
        int tm = (tl + tr) / 2;
        build ((v << 1) + 1, tl, tm);
        build ((v << 1) + 2, tm + 1, tr);
        mas[v] = min(mas[(v << 1) + 1], mas[(v << 1) + 2]);
    }
}

int main() {
    ifstream fin("parking.in");
    ofstream fou("parking.out");
    fin >> n >> m;
    int tmp;
    string str;
    int line_l = (1 << get_hight()) - 1;
    build(0,1,line_l+1);
    while(fin >> str >> tmp) {
        if (str == "enter") {
            place = min(0, 1, line_l + 1, tmp, n);
            if (place == inf)
                place = min(0, 1, line_l + 1, 1, tmp - 1);
            val = inf;
            setFreePlace(0, 1, line_l + 1, place);
            fou << place << '\n';
        }
        if (str == "exit") {
            val=tmp;
            setFreePlace(0, 1, line_l + 1, tmp);
        }
    }
    fin.close();
    fou.close();
    return 0;
}
