#include <iostream>
#include <vector>
#include <set>

using namespace std;

int main() {
    freopen("epsilon.in", "r", stdin);
    freopen("epsilon.out", "w", stdout);
    int n;
    string start;
    cin >> n;
    vector<pair<string, string>> rules;
    string st;
    getline(cin, start);
    set<string> eps = {};
    for (int i = 0; i < n; i++) {
        getline(cin, st);
        string f = st.substr(0, st.find(" ->"));
        string s = st.substr(st.find(" -> ") + 4);
        if (s == ">") {
            s = "";
            eps.insert(f);
        }
        rules.push_back(make_pair(f, s));
    }
    bool modified = true;
    while (modified) {
        modified = false;
        for (int i = 0; i < rules.size(); i++) {
            if (eps.count(rules[i].first) != 0) {
                continue;
            }
            bool is_eps = true;
            for (int j = 0; j < rules[i].second.size(); j++) {
                if (eps.count(rules[i].second.substr(j, 1)) == 0) {
                    is_eps = false;
                    break;
                }
            }
            if (is_eps) {
                modified = true;
                eps.insert(rules[i].first);
            }
        }
    }
    for (auto i : eps) {
        cout << i << " ";
    }
    return 0;
}