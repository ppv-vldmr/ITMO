//
//  main.cpp
//  2
//
//  Created by Владимир on 13.11.2020.
//

#include <iostream>
#include <vector>
#include <set>
 
using namespace std;
 
set<pair<int, int>> s;
vector<vector<pair<int, int>>> g;
vector<long long> d;
vector<bool> used;
 
void add_edge(int from, int to, int weight) {
    g[from].push_back(make_pair(to, weight));
}
 
void dijkstra() {
    d[0] = 0;
    s.insert(make_pair(d[0], 0));
    while(!s.empty()) {
        int nearest = s.begin()->second;
        s.erase(s.begin());
        for(int i = 0; i < g[nearest].size(); i++) {
            int to = g[nearest][i].first;
            int weight = g[nearest][i].second;
            if (d[nearest] + weight < d[to]) {
                s.erase(make_pair(d[to], to));
                d[to] = d[nearest] + weight;
                s.insert(make_pair(d[to], to));
            }
        }
    }
    
    for (int i = 0; i < d.size(); i++) {
        cout << d[i] << " ";
    }
    cout << endl;
}
 
int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    int n, m;
    cin >> n >> m;
    g.resize(n, vector<pair<int, int>>());
    used.resize(n, false);
    d.resize(n, 9999999999);
    
    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        add_edge(a - 1, b - 1, c);
        add_edge(b - 1, a - 1, c);
    }
    
    dijkstra();
    
    return 0;
}
