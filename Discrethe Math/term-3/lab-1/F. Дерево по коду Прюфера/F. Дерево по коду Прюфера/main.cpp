//
//  main.cpp
//  F. Дерево по коду Прюфера
//
//  Created by Владимир on 04.12.2020.
//

#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<int> code, inCode;
set<int> v;
int n;

int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n;
    code.resize(n - 2);
    inCode.resize(n, 1);
    for (int i = 0; i < n - 2; i++) {
        cin >> code[i];
        --code[i];
        inCode[code[i]]++;
    }
    for (int i = 0; i < inCode.size(); i++) {
        if (inCode[i] == 1) {
            v.insert(i);
        }
    }
    for (int i = 0; i < n - 2; i++) {
        int p1 = *v.begin();
        v.erase(v.begin());
        int i1 = code[i];
        cout << p1 + 1 << " " << i1 + 1 << endl;
        --inCode[i1];
        if (inCode[i1] == 1) {
            v.insert(i1);
        }
    }
    cout << *v.begin() + 1 << " " << *--v.end() + 1;
    return 0;
}
