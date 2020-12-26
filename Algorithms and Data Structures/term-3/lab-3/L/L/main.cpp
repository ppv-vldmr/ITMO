#include <fstream>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

string s;
int n;
const int maxlen = 500000;
const int alph = 1000;

int main() {
    cin >> s;
    int k;
    int r=-1;
    cin>>k;
    vector<int> p;
    vector<int>check;
    vector<int> help_p;
    vector<int> help_c;
    vector<int> eq;
    vector<int> count;
    count.resize(maxlen);
    p.resize(maxlen);
    eq.resize(maxlen);
    help_p.resize(maxlen);
    help_c.resize(maxlen);
    fill(count.begin(),count.begin()+alph,0);
    for (int i = 0; i < s.size(); ++i) {
        ++count[s[i]];
    }
    for (int i = 1; i < alph; ++i) {
        count[i] += count[i - 1];
    }
    for (int i = 0; i < s.size(); ++i) {
        p[--count[s[i]]] = i;
    }
    eq[p[0]] = 0;
    int classs = 1;
    for (int i = 1; i < s.size(); ++i) {
        if (s[p[i]] != s[p[i - 1]]) ++classs;
        eq[p[i]] = classs - 1;
    }
    for (int j = 0; (1 << j) < s.size(); ++j) {
        for (int i = 0; i < s.size(); ++i) {
            help_p[i] = p[i] - (1 << j);
            if (help_p[i] < 0) help_p[i] += s.size();
        }
        fill(count.begin(),count.begin()+classs,0);
        for (int i = 0; i < s.size(); ++i) {
            ++count[eq[help_p[i]]];
        }
        for (int i = 1; i < classs; ++i) {
            count[i] += count[i - 1];
        }
        for (int i = s.size()-1; i >= 0; --i) {
            p[--count[eq[help_p[i]]]] = help_p[i];
        }
        help_c[p[0]] = 0;
        classs = 1;
        for (int i = 1; i < s.size(); ++i) {
            int mid = (p[i] + (1 << j)) % s.size();
            int mid2 = (p[i - 1] + (1 << j)) % s.size();
            if (eq[p[i]] != eq[p[i - 1]] || eq[mid] != eq[mid2]) ++classs;
            help_c[p[i]] = classs - 1;
        }
        eq=help_c;
    }
    k--;
    for(int i = 0 ; i < s.size();++i){
      check.push_back(0);
    }
    for(int i = 0;i<s.size();++i){
      if(!check[eq[p[i]]]){
        r++;
        check[eq[p[i]]] = 1;
      }
      if(r==k){
        string ans = s.substr(p[i],s.size()-p[i]) + s.substr(0,p[i]);
        cout<<ans;
        return 0;
      }
    }
    cout<<"IMPOSSIBLE";
    return 0;
}
