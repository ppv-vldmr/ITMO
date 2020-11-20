//
//  main.cpp
//  1
//
//  Created by Владимир on 08.11.2020.
//

#include <iostream>
#include <cstdio>
#include <vector>
 
using namespace std;
 
vector<vector<int>> d;
 
int INF = 1000000000;
 
int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    int n;
    cin >> n;
    d.resize(n);
    for (int i = 0; i < n; i++) {
        d[i].resize(n);
        for (int j = 0; j < n; j++) {
            cin >> d[i][j];
        }
    }
    
    for (int k=0; k<n; ++k) {
        for (int i=0; i<n; ++i) {
            for (int j=0; j<n; ++j) {
                if (d[i][k] < INF && d[k][j] < INF) {
                    d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
    
    for (int i = 0; i < d.size(); i++) {
        for (int j = 0; j < d[i].size(); j++) {
            cout << d[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}
