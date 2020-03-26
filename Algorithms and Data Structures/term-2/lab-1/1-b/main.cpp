#include <iostream>
#include <vector>
#include <cmath>

/*
 * @author Vladimir Popov (vova_57@bk.ru)
 */

using namespace std;

long long nextpow(long long a)
{
    return 1 << (int) ceil(log2(a));
}

void update(vector<long long> &tree, long long index, long long value)
{
    long long k = (tree.size() - 1) / 2;
    tree[k - 1 + index] = value;
    long long j = k - 1 + index;
    while (j > 0)
    {
        j = (j - 1) / 2;
        tree[j] = tree[2 * j + 1] + tree[2 * j + 2];
    }
}

long long sum(vector<long long> &tree, long long l, long long r)
{
    long long n = (tree.size() + 1)/2 - 1, ans = 0;
    l += n - 1;
    r += n - 1;
    while (r >= l)
    {
        if (l % 2 == 0)
            ans += tree[l];
        l /= 2;
        if (r % 2 != 0)
            ans += tree[r];
        r /= 2;
        r--;
    }
    return ans;
}

void coutVector(vector<long long> &tree)
{
    cout << endl;
    for (long long i = 0; i < tree.size(); i++)
        cout << tree[i] << " ";
    cout << endl << endl;
}

int main() {
    long long count_of_numbers;
    cin >> count_of_numbers;
    vector<long long> tree(2 * nextpow(count_of_numbers) - 1);
    long long t = nextpow(count_of_numbers);
    for (long long i = 0; i < count_of_numbers; i++)
        cin >> tree[tree.size() - t + i];
    for (long long i = tree.size() - t - 1; i > 0; i--)
    {
        tree[i] = tree[2*i+1]+tree[2*i+2];
    }
    string request;
    while (cin >> request)
    {
        if (request == "sum")
        {
            long long l, r;
            cin >> l >> r;
            cout << sum(tree, l, r) << endl;
        } else if (request == "set")
        {
            long long i, x;
            cin >> i >> x;
            update(tree, i, x);
            //coutVector(tree);
        }
    }
    return 0;
}
