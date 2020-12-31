#pragma GCC target ("avx2")
#pragma GCC optimization ("O3")
#pragma GCC optimization ("unroll-loops")

//викиконспекты дело небожителей
#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>
#include <assert.h>
  
using namespace std;
  
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n;
    cin >> n;
    vector<vector<bool>> graph(n, vector<bool>(n, false));
    deque<int> ans;
    char c;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            cin >> c;
            graph[i][j] = graph[j][i] = (c == '1');
        }
    }
    
//    for (int i = 0; i < graph.size(); i++) {
//        for (int j = 0; j < graph[i].size(); j++) {
//            cout << graph[i][j] << " ";
//        }
//        cout << endl;
//    }
    
    for (int i = 0; i < n; i++) {
        ans.push_back(i);
    }
    for(int i = 0; i < n * (n - 1); i++) {
        if (!graph[ans[0]][ans[1]]) {
            int j = 2;
            while (!(graph[ans[0]][ans[j]] && graph[ans[1]][ans[j + 1]])) {
                j++;
            }
            std::reverse(ans.begin() + 1, ans.begin() + j + 1);
        }
        ans.push_back(ans.front());
        ans.pop_front();
    }
    for (int i = 0; i < n; i++) {
        cout << ans[i] + 1 << " ";
    }
    return 0;
}
