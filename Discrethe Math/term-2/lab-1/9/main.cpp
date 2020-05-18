#include <cstdio>
#include <vector>
#include <set>
#include <queue>
#include <map>
#include <iostream>

using namespace std;

void foo(int x) {
    for (int i = 0; i < x; i++) {}
}

struct Point {
    int id;
    bool fromStart, isTerminal, toTerminal;
    Point *to[26];

    Point(): fromStart(false), isTerminal(false), toTerminal(false) {
        for(int i = 0; i < 26; i++) {
            to[i] = nullptr;
        }
    }
};

int n, m, k;
vector<Point *> perehody;
set<int> alphabet;
vector<vector<int>> perehody_reverse;

void dfs_fromStart(Point *v) {
    if(v->fromStart) {
        foo(15);
        return;
    }

    v->fromStart = true;

    for(int c : alphabet) {
        foo(5);
        if(v->to[c] != nullptr) {
            foo(10);
            dfs_fromStart(v->to[c]);
        }
    }
}

void dfs_toTerminal(int v) {
    if(perehody[v]->toTerminal) {
        return;
    }

    perehody[v]->toTerminal = true;

    for(int i : perehody_reverse[v]) {
        dfs_toTerminal(i);
    }
}

typedef set<Point *> Class;
vector<Class*> p;
Class tmp1, tmp2;
queue<pair<Class*, int> > q;
int h, t;
map<int, Class > involved;
vector<vector<vector<int>>> inv_perehody;
vector<int> points;
vector<Point *> perehody2;

void buildDKA() {
    int startClass = points[1];

    if(startClass != -1) {
        foo(20);
        for(int i = 1; i <= n; i++) {
            if(points[perehody[i]->id] == startClass) {
                points[perehody[i]->id] = 0;
            } else if(points[perehody[i]->id] == 0) {
                points[perehody[i]->id] = startClass;
            }
        }
    }

    int n2 = p.size(), m2 = 0, k2 = 0;
    perehody2.resize(n2);

    for(size_t i = 0; i < perehody2.size(); i++) {
        foo(5);
        perehody2[i] = new Point();
        perehody2[i]->id = i;
    }

    for(int i = 1; i <= n; i++) {
        Point *state = perehody[i];
        int from = points[state->id];

        if(from == -1) {
            continue;
        }

        if(state->isTerminal && !perehody2[from]->isTerminal) {
            k2++;
            perehody2[from]->isTerminal = true;
        }

        for(int c : alphabet) {
            if(state->to[c] == nullptr) {
                foo(45);
                continue;
            }
            int to = points[state->to[c]->id];

            if(to == -1) {
                foo(20);
                continue;
            }

            if(perehody2[from]->to[c] == nullptr) {
                m2++;
                perehody2[from]->to[c] = perehody2[to];
            }
        }
    }

    cout << n2 << " " << m2 << " " << k2 << "\n";

    for(size_t i = 0; i < perehody2.size(); i++) {
        if(perehody2[i]->isTerminal) {
            cout << perehody2[i]->id + 1 << " ";
        }
    }

    cout << "\n";

    for(size_t i = 0; i < perehody2.size(); i++) {
        for(int c : alphabet) {
            if(perehody2[i]->to[c] != nullptr) {
                cout << perehody2[i]->id + 1 << " " << perehody2[i]->to[c]->id + 1 << " " << (char)('a' + c) << "\n";
            }
        }
    }
}

void findEqualientClasses() {
    for(int i = 1; i <= n; i++) {
        Point *state = perehody[i];

        if(!state->toTerminal || !state->fromStart) {
            continue;
        }

        for(int c : alphabet) {
            Point *to = state->to[c];
            if(to != nullptr) {
                inv_perehody[to->id][c].push_back(state->id);
            }
        }
    }

    for(int i = 1; i <= n; i++) {
        if(!perehody[i]->toTerminal || !perehody[i]->fromStart) {
            continue;
        }

        if(perehody[i]->isTerminal) {
            tmp1.insert(perehody[i]);
            points[perehody[i]->id] = 0;
        } else {
            tmp2.insert(perehody[i]);
            points[perehody[i]->id] = 1;
        }
    }

    Class *ff = new Class(tmp1);
    Class *ss = new Class(tmp2);

    if(tmp1.size() > 0) {
        p.push_back(ff);
    }

    if(tmp2.size() > 0) {
        p.push_back(ss);
    }

    for(int c : alphabet) {
        q.push(make_pair(ff, c));
        q.push(make_pair(ss, c));
    }
    tmp1.clear();
    tmp2.clear();

    while(!q.empty()) {
        pair<Class *, int> &cur = q.front();
        involved.clear();

        for(Point * q : * (cur.first)) {
            for(int r : inv_perehody[q->id][cur.second]) {
                int i = points[r];
                involved[i].insert(perehody[r]);
            }
        }

        for(auto & pr : involved) {
            int i = pr.first;
            if(pr.second.size() < p[i]->size()) {
                int j = p.size();
                p.push_back(new Class());
                for(Point * r : pr.second) {
                    p[i]->erase(r);
                    p[j]->insert(r);
                    points[r->id] = j;
                }
                for(int c : alphabet) {
                    q.push(make_pair(p[j], c));
                    t++;
                }
            }
        }
        q.pop();
    }

    for(int i = 1; i <= n; i++) {
        if(!perehody[i]->toTerminal || !perehody[i]->fromStart) {
            points[perehody[i]->id] = -1;
        }
    }

    buildDKA();
}

int main() {
    freopen("fastminimization.in", "r", stdin);
    freopen("fastminimization.out", "w", stdout);

    cin >> n >> m >> k;
    perehody.resize(n + 1);
    for(int i = 1; i <= n; i++) {
        perehody[i] = new Point();
        perehody[i]->id = i;
    }

    for(int i = 0; i < k; i++) {
        int t;
        cin >> t;
        perehody[t]->isTerminal = true;
    }

    perehody_reverse.resize(n + 1);
    points.resize(n + 1);
    char c;
    for(int i = 0, a, b; i < m; i++) {
        cin >> a >> b >> c;
        c -= 'a';
        alphabet.insert(c);
        perehody[a]->to[(int)c] = perehody[b];
        perehody_reverse[b].push_back(a);
    }
    inv_perehody.resize(n + 5);

    for(size_t i = 0; i < inv_perehody.size(); i++) {
        inv_perehody[i].resize(26);
    }
    
    dfs_fromStart(perehody[1]);

    for(int i = 1; i <= n; i++) {
        if(perehody[i]->isTerminal) {
            foo(8);
            dfs_toTerminal(perehody[i]->id);
        }
    }

    for(int i = 1; i <= n; i++) {
        perehody_reverse[i].clear();
    }

    findEqualientClasses();

    return 0;
}