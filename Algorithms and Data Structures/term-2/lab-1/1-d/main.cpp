#include <iostream>
#include <algorithm>

using namespace std;

struct Color {
    char color;
    long long point;
    long long trace;
};

struct Paint {
    long long  value_long;
    long long value_sum;
    bool is_black_left;
    bool is_black_right;
    bool flag_set;
};

Paint mas[4000300];
Color star[500100];
long long n, n_el;
int val, real_hight;

inline int get_hight(long long tmp_n)
{
    int l = tmp_n - 1;
    int i = 0;
    while (l > 0)
    {
        ++i;
        l = l >> 1;
    }
    return i;
}

void propagate(int v) {
    if (mas[v].flag_set) {
        if (mas[v].is_black_left) {
            mas[(v << 1) + 1].is_black_left = 1;
            mas[(v << 1) + 2].is_black_right = 1;
            mas[(v << 1) + 2].is_black_left = 1;
            mas[(v << 1) + 1].is_black_right = 1;
            mas[(v << 1) + 1].flag_set = 1;
            mas[(v << 1) + 2].flag_set = 1;
            mas[(v << 1) + 1].value_long = mas[v].value_long >> 1;
            mas[(v << 1) + 2].value_long = mas[v].value_long >> 1;
            mas[(v << 1) + 1].value_sum = mas[v].value_sum;
            mas[(v << 1) + 2].value_sum = mas[v].value_sum;
        } else {
            mas[(v << 1) + 1].is_black_left = 0;
            mas[(v << 1) + 2].is_black_right = 0;
            mas[(v << 1) + 2].is_black_left = 0;
            mas[(v << 1) + 1].is_black_right = 0;
            mas[(v << 1) + 1].flag_set = 1;
            mas[(v << 1) + 2].flag_set = 1;
            mas[(v << 1) + 1].value_long = mas[v].value_long;
            mas[(v << 1) + 2].value_long = mas[v].value_long;
            mas[(v << 1) + 1].value_sum = mas[v].value_sum;
            mas[(v << 1) + 2].value_sum = mas[v].value_sum;
        }
        mas[v].flag_set = 0;
    }
}

void set(long long v, int tl, int tr,int l, int r, long long sons)
{
    if (l > r)
        return;
    if (tl > tr || tr < l || tl > r)
        return;
    if (l <= tl && tr <= r)	{
        if (tl != tr) mas[v].flag_set=1;
        if (val == 1) {
            mas[v].is_black_left = 1;
            mas[v].is_black_right = 1;
            mas[v].value_sum = 1;
            mas[v].value_long = sons;
        } else {
            mas[v].is_black_left = 0;
            mas[v].is_black_right = 0;
            mas[v].value_sum = 0;
            mas[v].value_long = 0;
        }
        return;
    }
    int tmp = (tl + tr) >> 1;
    propagate(v);
    set((v << 1) + 1, tl, tmp, l, min(r,tmp),sons >> 1);
    set((v << 1) + 2, tmp+1, tr, max(l, tmp + 1), r, sons >> 1);
    if (mas[(v << 1) + 2].is_black_left && mas[(v << 1) + 1].is_black_right)
        mas[v].value_sum = mas[(v << 1) + 2].value_sum + mas[(v << 1) + 1].value_sum - 1;
    else
        mas[v].value_sum = mas[(v << 1) + 2].value_sum + mas[(v << 1) + 1].value_sum;
    mas[v].value_long = mas[(v << 1) + 2].value_long + mas[(v << 1) + 1].value_long;
    if (mas[(v << 1) + 1].is_black_left)
        mas[v].is_black_left = 1;
    else
        mas[v].is_black_left = 0;
    if (mas[(v << 1) + 2].is_black_right)
        mas[v].is_black_right = 1;
    else
        mas[v].is_black_right = 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin >> n_el;
    cin >> star[0].color >> star[0].point >> star[0].trace;
    long long min_x = star[0].point, max_x = star[0].point + star[0].trace;
    for(long long i = 1; i < n_el; ++i)  {
        cin >> star[i].color >> star[i].point >> star[i].trace;
        if (star[i].point < min_x)
            min_x = star[i].point;
        if (max_x < star[i].point + star[i].trace)
            max_x = star[i].point + star[i].trace;
    }
    n = max_x - min_x + 1;
    real_hight = get_hight(n);
    int line_l = (1 << real_hight ) - 1;
    long long st = 1;
    if (min_x != 0 ) st = -min_x + 1;
    for(long long i = 0; i < n_el; ++i) {
        if (star[i].color == 'W')
            val = 0;
        else
            val = 1;
        set(0, 1, line_l+1, star[i].point + st, star[i].point + star[i].trace + st - 1, line_l+1);
        cout << mas[0].value_sum << ' ' << mas[0].value_long << '\n';
    }
    return 0;
}