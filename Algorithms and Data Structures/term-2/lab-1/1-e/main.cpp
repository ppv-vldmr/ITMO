#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

class Matrix;
int r, n, m, t1, t2, t3, t4;
ifstream fin("crypto.in");

class Matrix {
public:
    int a, b, c, d;
    Matrix () {
        a = d = 1;
        b = c = 0;
    }
    
    Matrix (int x, int y , int z, int f):
            a(x),
            b(y),
            c(z),
            d(f) {}
            
    Matrix operator*(const Matrix& rv) const {
        return Matrix((this->a * rv.a + this->b * rv.c) % r,
                      (this->a * rv.b + this->b * rv.d) % r,
                      (this->c * rv.a + this->d * rv.c) % r,
                      (this->c * rv.b + this->d * rv.d) % r);
    }
};

Matrix mas[1000000];
Matrix E;

Matrix mult(int v,int tl, int tr,int l, int r)
{
    if (l > r)
        return E;
    if (tl>tr || tr < l || tl > r)
        return E;
    if (l<=tl && tr<= r)
        return mas[v];
    int tmp = (tl + tr) >> 1;
    Matrix left = mult((v << 1) + 1, tl, tmp, l, min(r, tmp));
    Matrix right = mult((v << 1) + 2, tmp + 1, tr, max(l, tmp + 1), r);
    return left * right;
}

void buildTree (int v, int tl, int tr) {
    if (tl == tr) {
        if (tl <= n) {
            fin >> t1 >> t2 >> t3 >> t4;
            mas[v] = Matrix(t1, t2, t3, t4);
        } else
            mas[v] = E;
    } else {
        int tm = (tl + tr) / 2;
        buildTree ((v << 1) + 1, tl, tm);
        buildTree ((v << 1) + 2, tm + 1, tr);
        mas[v] = mas[(v << 1) + 1] * mas[(v << 1) + 2];
    }
}

int get_hight()
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

int main() {
    ios_base::sync_with_stdio(false);
    ofstream fou("crypto.out");
    E = Matrix(1, 0, 0, 1);
    fin >> r >> n >> m;
    int line_l = (1 << get_hight()) - 1;
    buildTree(0,1,line_l+1);
    Matrix tmp;
    for(int i = 0; i < m; ++i) {
        fin >> t1 >> t2;
        tmp = mult(0, 1, line_l+1, t1, t2);
        fou << tmp.a << ' ' << tmp.b << '\n' << tmp.c << ' ' << tmp.d << '\n';
    }
    fin.close();
    fou.close();
    return 0;
}