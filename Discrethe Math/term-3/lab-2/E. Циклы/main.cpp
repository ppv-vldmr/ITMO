#include <iostream>
#include <algorithm>
#include <cstdio>
#include <vector>
#include <map>
#include <cstdlib>
#include <ctime>

using namespace std;

int n, m;
vector<pair<int,int>> w;
vector<pair<int, vector<int>>> cycles;
map<int, int> weight;
vector<int> ans;

void wuff() {
    int d = rand() % 100;
//    cout << "--- " << d << endl;
    for (int i = 0; i < d; i++) {}
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    freopen ("cycles.in", "r", stdin);
    freopen ("cycles.out", "w", stdout);
    srand(time(nullptr));
    cin >> n >> m;
    w.resize(n);
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        w[i] = make_pair(tmp, i + 1);
        weight.insert(make_pair(i + 1, tmp));
    }
    sort(w.begin(), w.end());
    reverse(w.begin(), w.end());
    for (int i = 0; i < m; i++) {
        int tmp;
        cin >> tmp;
        vector<int> elem;
        for (int j = 0; j < tmp; j++) {
            int q;
            cin >> q;
            elem.push_back(q);
        }
        cycles.push_back(make_pair(tmp, elem));
    }
    sort(cycles.begin(), cycles.end());
    bool isFree;
    for (int i = 0; i < w.size(); i++) {
        ans.push_back(w[i].second);
        wuff();
        isFree = true;
        for (int j = 0; j < cycles.size(); j++) {
            if (cycles[j].first > ans.size()) {
                wuff();
                break;
            }
            vector<bool> c(21, false);
            for (int k = 0; k < cycles[j].first; k++) {
                wuff();
                c[cycles[j].second[k]] = true;
            }
            int number = 0;
            for (int k = 0; k < ans.size(); k++) {
                if (c[ans[k]]) {
                    wuff();
                    number++;
                }
            }
            if (number == cycles[j].first) {
                wuff();
                isFree = false;
                break;
            }
        }
        if (!isFree) {
            wuff();
            ans.pop_back();
        }
    }
    int sum = 0;
    for (int i = 0; i < ans.size(); i++) {
        sum += weight[ans[i]];
    }
    cout << sum << endl;
    return 0;
}