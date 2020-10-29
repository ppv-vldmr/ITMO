//
//  main.cpp
//  9
//
//  Created by Владимир on 22.10.2020.
//

#include <iostream>
#include <vector>
#include <iomanip>
#include <math.h>


using namespace std;

const int INF = 999999999;
               
struct Vertex {
    int x, y;
    
    Vertex() {}
    
    Vertex(int x, int y) {
        this->x = x;
        this->y = y;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    int n, a, b, min_pos, mn, w;
    double ans = 0;
    cin >> n;
    vector<Vertex> v(n);
    vector<int> d(n, INF);
    vector<bool> visited(n, false);
    
    for (int i = 0; i < n; i++) {
        cin >> a >> b;
        v[i] = Vertex(a, b);
    }
    
    d[0] = 0;
    while (true) {
        min_pos = -1;
        mn = INF;
        for (int i = 0; i < n; ++i) {
            if (!visited[i] && d[i] < mn) {
                mn = d[i];
                min_pos = i;
            }
        }
        if (min_pos == -1) {
            break;
        }
        ans += sqrt(mn);
        visited[min_pos] = true;
        for (int i = 0; i < n; i++) {
            w = (v[min_pos].x - v[i].x) * (v[min_pos].x - v[i].x) + (v[min_pos].y - v[i].y) * (v[min_pos].y - v[i].y);
            if (d[i] > w) {
                d[i] = w;
            }
        }
    }
    cout << setprecision(11) << ans << endl;
    return 0;
}



