//
//  main.cpp
//  C. Интерактивная восточная сказка
//
//  Created by Владимир on 29.12.2020.
//

#pragma GCC target ("avx2")
#pragma GCC optimization ("O3")
#pragma GCC optimization ("unroll-loops")

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> ans;

bool comp(int a, int b) {
    cout << 1 << " " << a << " " << b << endl;
    cout.flush();
    string st;
    cin >> st;
    return st[0] == 'Y';
}

int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n;
    for (int i = 1; i <= n; i++) {
        ans.push_back(i);
    }
    std::stable_sort(ans.begin(), ans.end(), comp);
    cout << 0 << " ";
    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i] << " ";
    }
    return 0;
}
