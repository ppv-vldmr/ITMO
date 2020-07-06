#include <bits/stdc++.h>

using namespace std;

void write(vector<int> &a) {
    for (int i = 0; i < a.size(); i++)
        cout << a[i] << " ";
    cout << endl;
}

int main()
{
    string st;
    vector <int> a;
    while (cin >> st) {
        if (st == "+") {
            int y = a.back();
            a.pop_back();
            int x = a.back();
            a.pop_back();
            a.push_back(x + y);
        } else
            if (st == "-") {
                int y = a.back();
                a.pop_back();
                int x = a.back();
                a.pop_back();
                a.push_back(x - y);
            } else
                if (st == "*") {
                    int y = a.back();
                    a.pop_back();
                    int x = a.back();
                    a.pop_back();
                    a.push_back(x * y);
                } else {
                    int x = stoi(st);
                    a.push_back(x);
                }
        //write(a);
    }
    cout << a.front();
    return 0;
}
