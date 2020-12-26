#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <iostream>
using namespace std;

struct suffix {
    int index;
    int rank[2];
};

int comparator(suffix &a,suffix &b) {
    if (a.rank[1] < b.rank[1]) {
        if (a.rank[0] == b.rank[0]) {
            return 1;
        } else {
            if (a.rank[0] < b.rank[0]) {
                return 1;
            } else {
                return 0;
            }
        }
    } else {
        if (a.rank[0] == b.rank[0]) {
            return 0;
        } else {
            if (a.rank[0] < b.rank[0]) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

vector<int> getArray(string &str) {
    int n = str.size();
    vector<suffix> suffixes(n);
    for (int i = 0; i < n; i++) {
        suffixes[i].index = i;
        suffixes[i].rank[0] = str[i] - 'a';
        if ((i + 1) < n) {
            suffixes[i].rank[1] = str[i + 1] - 'a';
        } else {
            suffixes[i].rank[1] = -1;
        }
    }
    sort(suffixes.begin(), suffixes.end(), comparator);
    vector<int> ind(n);
    for (int k = 4; k < n << 1; k <<= 1) {
        int rank = 0;
        int prev_rank = suffixes[0].rank[0];
        suffixes[0].rank[0] = rank;
        ind[suffixes[0].index] = 0;
        for (int i = 1; i < n; i++) {
            if (suffixes[i].rank[0] == prev_rank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
                prev_rank = suffixes[i].rank[0];
                suffixes[i].rank[0] = rank;
            } else {
                prev_rank = suffixes[i].rank[0];
                suffixes[i].rank[0] = ++rank;
            }
            ind[suffixes[i].index] = i;
        }
        for (int i = 0; i < n; i++) {
            int nextindex = suffixes[i].index + k / 2;
            suffixes[i].rank[1] = (nextindex < n) ?
                                  suffixes[ind[nextindex]].rank[0] : -1;
        }
        sort(suffixes.begin(), suffixes.end(),comparator);
    }
    vector<int> suffixArr(n);
    for (int i = 0; i < n; i++) {
        suffixArr[i] = suffixes[i].index;
    }
    return suffixArr;
}

int main() {
    string str;
    cin >> str;
    int n = str.size();
    int k = 0;
    vector<int> suffArray = getArray(str);
    vector<int> lcp;
    lcp.resize(n-1);
    vector<int>rank;
    rank.resize(n);
    for (int i = 0; i < n; ++i) {
        rank[suffArray[i]] = i;
    }
    for (int i = 0; i < n; i++, k ? k-- : 0) {
        if (rank[i] == n - 1) {
            k = 0;
            continue;
        }
        int j = suffArray[rank[i] + 1];
        while (i + k < n && j + k < n && str[i + k] == str[j + k]) k++;
        lcp[rank[i]] = k;
    }
    long long ans = 0;
    for(int i = 0;i<n;i++){
         ans += n-suffArray[i];
    }
    long long ans2 = 0;
    for(int i = 0;i<n-1;++i){
        ans2+=lcp[i];
    }
    cout << ans-ans2;
return 0;
}
