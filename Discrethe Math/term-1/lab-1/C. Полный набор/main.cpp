#include <bits/stdc++.h>

using namespace std;

long long to_decimal(string st) {
    long long sum = 0, i = st.size() - 1, x = 0;
    while (i >= 0) {
        string s = st.substr(i,1);
        int r = atoi(s.c_str());
        sum += r * pow(2,x);
        x++;
        i--;
    }
    return sum;
}

bool verify_const0(string st) {
    if (st.substr(0,1) == "0")
        return true;
    else
        return false;
}

bool verify_const1(string st) {
    int l = st.size();
    if (st.substr(l-1,1) == "1")
        return true;
    else
        return false;
}

bool verify_self_duality(string st) {
    int l = st.size() / 2;
    for (int i = 0; i < st.size(); i++) {
        if (st.substr(i,1) == st.substr(st.size() - 1 - i, 1))
            return false;
    }
    return true;
}

bool verify_monotony(string st) {
    int l = st.size();
    for (int i = 0; i < l; i++)
        for (int j = i + 1; j < l; j++)
            if (((i | j) == j) && st.substr(i,1) > st.substr(j,1))
                return false;
    return true;
}

bool verify_linearity(string st) {
    int l = st.size();
    vector<vector<int>>a(l);
    for (int i = 0; i < l; i++) {
        for (int j = 0; j < l; j++) {
            a[i].push_back(0);
        }
    }
    for (int i = 0; i < st.size(); i++) {
        string s = st.substr(i,1);
        a[i][0] = atoi(s.c_str());
    }
    for (int j = 1; j < st.size(); j++) {
        for (int i = st.size() - 2; i >=0; i--) {
            a[i][j] = (a[i+1][j-1] + a[i][j-1]) % 2;
        }
    }
    /*for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < a[i].size() - i; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }*/
    for (int i = 1; i < a[0].size(); i++) {
        bool c = false;
        if (a[0][i] == 1) {
            c = false;
            for (int k = 0; k < 6; k++)
                if (i == pow(2,k))
                    c = true;
            if (!c) return false;
        }
    }
    return true;
}

int main() {
    int n;
    cin >> n;
    vector<bool> check(5,false);
    for (int i = 0; i < n; i++) {
        int k;
        string st;
        cin >> k >> st;
        if (!verify_const0(st)) check[0] = true;
        if (!verify_const1(st)) check[1] = true;
        if (!verify_self_duality(st)) check[2] = true;
        if (!verify_monotony(st)) check[3] = true;
        if (!verify_linearity(st)) check[4] = true;
        //cout << verify_const0(st) << " " << verify_const1(st) << " " << verify_self_duality(st) << " " << verify_monotony(st) << " " << verify_linearity(st) << endl;
    }
    if (check[0] && check[1] && check[2] && check[3] && check[4]) cout << "YES"; else cout << "NO";
    return 0;
}
