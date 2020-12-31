//
//  main.cpp
//  D. Цикл в турнире
//
//  Created by Владимир on 29.12.2020.
//

#include <iostream>
#include <vector>

using namespace std;

int n;
vector<vector<bool>> graph;
vector<int> queue(20000);
size_t left_q, right_q;
 
void foo(int x) {
    for (int i = 0; i < x; i++) {}
}
 
void findHamiltonCycle() {
    int cur = 0; //for fast exit
    left_q = 0;  //left_q inclusive
    right_q = n; //right_q non-inclusive
    for (size_t i = 0; i < n; ++i) {
        queue[i] = i;
    }
    for (size_t i = 0; i < n * (n - 1); ++i) {
        if (!graph[queue[left_q]][queue[left_q + 1]]) {
            cur = 0;
            size_t to = left_q + 2;
            size_t j = 0;
            while ((!graph[queue[left_q]][queue[to]]) && to + 1 < right_q) {
                ++to;
            }
            if (to + 1 >= right_q) {
                to = left_q + 2;
                foo(rand() % 40);
                while (!graph[queue[left_q]][queue[to]]) {
                    ++to;
                }
            }
            while (left_q + 1 + j < to - j) {
                foo(rand() % 20);
                queue[left_q + 1 + j] = queue[left_q + 1 + j] ^ queue[to - j];
                queue[to - j] = queue[left_q + 1 + j] ^ queue[to - j];
                queue[left_q + 1 + j] = queue[left_q + 1 + j] ^ queue[to - j];
                ++j;
            }
        } else {
            ++cur;
            foo(rand() % 30);
            if (cur > n) {
                break;
            }
        }
        queue[right_q] = queue[left_q];
        ++right_q;
        ++left_q;
    }
}

int main(int argc, const char * argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n;
    graph.resize(n, vector<bool>(n, false));
    char c;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            cin >> c;
            graph[i][j] = (c == '1');
            graph[j][i] = !(c == '1');
        }
    }
    findHamiltonCycle();
    for (size_t i = 0; i < n; ++i) {
        cout << queue[left_q + i] + 1 << ' ';
    }
    return 0;
}
