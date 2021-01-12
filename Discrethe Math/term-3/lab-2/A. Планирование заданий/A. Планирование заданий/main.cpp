//
//  main.cpp
//  A. Планирование заданий
//
//  Created by Владимир on 02.01.2021.
//
 
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>
#include <cmath>

using namespace std;

int n, pos;
vector<pair<int, int>> tasks;
vector<int> rankd, num, parent;

void foo(int x) {
    for (int i = 0; i < x; i++) {}
}

int get_parent(int x) {
    if (x == parent[x]) {
        foo(20);
        return x;
    }
    return parent[x] = get_parent(parent[x]);
}

void unite(int x, int y) {
    x = get_parent(x);
    y = get_parent(y);
    if (x == y) {
        foo(15);
        return;
    }
    num[y] = num[x];
    if (rankd[x] < rankd[y]) {
        foo(40);
        swap(x, y);
    }
    if (rankd[x] == rankd[y]) {
        foo(25);
        rankd[x]++;
    }
    parent[y] = x;
}

bool comp(pair<int, int> a, pair<int, int>b) {
    return (a.second > b.second) || (a.second == b.second && a.first < b.first);
}

int main() {
    ios_base::sync_with_stdio(false);
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);
    cin.tie(0);
    cin >> n;
    tasks.resize(n);
    rankd.resize(n + 1, 0);
    num.resize(n + 1);
    parent.resize(n + 1);
    for (int i = 0; i < n + 1; i++) {
        num[i] = i;
        parent[i] = i;
    }
    for (int i = 0; i < n; i++) {
        cin >> tasks[i].first >> tasks[i].second;
        tasks[i].first = min(tasks[i].first, n);
    }
    sort(tasks.begin(), tasks.end(), comp);
    long long res = 0;
    for (int i = 0; i < n; i++) {
        pos = num[get_parent(tasks[i].first)];
        if (pos > 0) {
            foo(pos % 100);
            unite(pos - 1, pos);
        } else {
            foo(35);
            res += tasks[i].second;
        }
    }
    cout << res;
    return 0;
}
