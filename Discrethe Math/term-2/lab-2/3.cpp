#include <iostream>
#include <vector>
#include <set>

using namespace std;

string not_term = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

bool check(string st) {
    for (int i = 0; i < st.size(); i++) {
        if (not_term.find(st.substr(i, 1)) != -1)
            return false;
    }
    return true;
}

int main() {
    freopen("useless.in", "r", stdin);
    freopen("useless.out", "w", stdout);
    int n;
    string start;
    cin >> n >> start;
    vector<pair<string, string>> rules, rules_const;
    string st;
    getline(cin, st);
    set<string> useful = {}; //все порождающие
    for (int i = 0; i < n; i++) {
        getline(cin, st);
        string f = st.substr(0, st.find(" ->"));
        string s = st.substr(st.find(" -> ") + 4);
        if (s == ">") {
            s = "";
        }
        rules.push_back(make_pair(f, s));
    }
    rules_const = rules;
    bool modified = true;
    bool start_visited = false;
    while (modified) {
        modified = false;
        for (int i = 0; i < rules.size(); i++) {
            bool use = true;
            for (int j = 0; j < rules[i].second.size(); j++) {
                if (not_term.find(rules[i].second.substr(j, 1)) != -1)
                    if (useful.count(rules[i].second.substr(j, 1)) == 0) {
                        use = false;
                        break;
                    }
            }
            if (rules[i].first == start)
                start_visited = true;
            if (use && useful.count(rules[i].first) == 0) {
                useful.insert(rules[i].first);
                modified = true;
            }
        }
    }
    for (int i = rules.size() - 1; i >= 0; i--) {
        bool to_del = false;
        for (int j = 0; j < rules[i].second.size(); j++) {
            if (not_term.find(rules[i].second.substr(j, 1)) != -1 && useful.count(rules[i].second.substr(j, 1)) == 0)
                to_del = true;
        }
        if (to_del) {
            rules.erase(rules.cbegin() + i, rules.cbegin() + i + 1);
        }
    }
    set<string> dost = {start};
    modified = true;
    while (modified) {
        modified = false;
        for (int i = 0; i < rules.size(); i++) {
            if (dost.count(rules[i].first) != 0) {
                for (int j = 0; j < rules[i].second.size(); j++) {
                    if (not_term.find(rules[i].second.substr(j, 1)) != -1 && dost.count(rules[i].second.substr(j, 1)) == 0) {
                        dost.insert(rules[i].second.substr(j, 1));
                        modified = true;
                    }
                }
            }
        }
    }
    if (not_term.find(start) != -1 && dost.count(start) == 0) {
        dost.insert(start);
    }
//    for (auto i : useful) {
//        cout << i << " ";
//    }
//    cout << endl;
//    for (auto i : dost) {
//        cout << i << " ";
//    }
//    cout << endl;
    set<string> res = {};
    for (int i = 0; i < rules_const.size(); i++) {
        if ((useful.count(rules_const[i].first) == 0 || dost.count(rules_const[i].first) == 0)) {
            res.insert(rules_const[i].first);
        }
        for (int j = 0; j < rules_const[i].second.size(); j++) {
            if ((useful.count(rules_const[i].second.substr(j, 1)) == 0 || dost.count(rules_const[i].second.substr(j, 1)) == 0) && not_term.find(rules_const[i].second.substr(j, 1)) != -1) {
                res.insert(rules_const[i].second.substr(j, 1));
            }
        }
    }
    if ((useful.count(start) == 0 || dost.count(start) == 0) && !start_visited) {
        res.insert(start);
    }
    for (auto i : res) {
        cout << i << " ";
    }
    return 0;
}
