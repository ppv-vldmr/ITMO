#include <iostream>
#include <random>
#include <vector>

using namespace std;

auto make_distribution(unsigned l, unsigned r) {
    return std::uniform_int_distribution <unsigned>(l, r);
}

using RndGen = std::mt19937_64;

auto make_gen() {
    std::random_device rd;
    return RndGen(rd());
}

auto rnd_gen = make_gen();

auto dist = make_distribution(0, INT32_MAX);

struct Tree {

    struct Node {
        explicit Node(int value) {
            this->value = value;
            this->y = dist(rnd_gen);
            sum = 0;
            w = 1;
            this->min = value;
            left = nullptr;
            right = nullptr;
        }
        int w, value, y, min, sum;
        Node *left;
        Node *right;
    };

    int minInd;
    int lastInd;
    vector <int> a;

    Tree() {
        minInd = INT32_MAX;
        lastInd = 0;
        root = nullptr;
    }

    void insert(int pos, int value) {
        if (root == nullptr) {
            root = new Node(value);
        } else {
            root = insert(root, pos, value);
        }
    }

    void func(int index, int value) {
        pair <Node *, Node *> pr = splitTree(root, index - 1);
        Node *seg = pr.second;
        find(seg, 0);
        seg = deleteNode(seg, minInd);
        seg = mergeTree(new Node(value), seg);
        root = mergeTree(pr.first, seg);
    }

    void go() {
        go(root, 0);
    }

    void getArray() {
        getArray(root);
    }

private:
    Node *root;

    void update(Node *t) {
        if (t != nullptr) {
            t->sum = t->value + getSum(t->left) + getSum(t->right);
            t->w = 1 + getW(t->left) + getW(t->right);
            t->min = min(t->value, min(getMin(t->left), getMin(t->right)));
        }
    }

    int getW(Node *x) {
        if (x == nullptr) {
            return 0;
        }
        return x->w;
    }

    int getMin(Node *x) {
        return x == nullptr ? INT32_MAX : x->min;
    }

    int getSum(Node *x) {
        return x == nullptr ? 0 : x->sum;
    }

    pair <Node *, Node *> splitTree(Node *t, int k) {
        if (t == nullptr) {
            return make_pair(nullptr, nullptr);
        }
        int w = getW(t->left);
        if (w < k) {
            pair <Node *, Node *> pr = splitTree(t->right, k - w - 1);
            t->right = pr.first;
            update(t);
            return make_pair(t, pr.second);
        } else {
            pair <Node *, Node *> pr = splitTree(t->left, k);
            t->left = pr.second;
            update(t);
            return make_pair(pr.first, t);
        }
    }

    Node *mergeTree(Node *t1, Node *t2) {
        if (t2 == nullptr) {
            return t1;
        }
        if (t1 == nullptr) {
            return t2;
        }
        if (t1->y > t2->y) {
            t1->right = mergeTree(t1->right, t2);
            update(t1);
            return t1;
        } else {
            t2->left = mergeTree(t1, t2->left);
            update(t2);
            return t2;
        }
    }

    Node *insert(Node *v, int pos, int value) {
        pair <Node *, Node *> pr = splitTree(v, pos - 1);
        pr.first = mergeTree(pr.first, new Node(value));
        v = mergeTree(pr.first, pr.second);
        return v;
    }

    void find(Node *v, int ind) {
        if (v == nullptr) {
            return;
        }
        if (v->value == 0) {
            minInd = min(minInd, ind + getW(v->left) + 1);
        }
        if (getMin(v->left) == 0) {
            find(v->left, ind);
        } else {
            find(v->right, ind + getW(v->left) + 1);
        }
    }

    Node *deleteNode(Node *v, int pos) {
        pair <Node *, Node *> pr = splitTree(v, pos - 1);
        pair <Node *, Node *> pr2 = splitTree(pr.second, 1);
        v = mergeTree(pr.first, pr2.second);
        update(v);
        return v;
    }

    void go(Node *v, int ind) {
        if (v == nullptr) {
            return;
        }
        go(v->left, ind);
        if (v->value != 0) {
            lastInd = ind + getW(v->left) + 1;
        }
        go(v->right, ind + getW(v->left) + 1);
    }

    void getArray(Node *v) {
        if (v == nullptr || a.size() > lastInd) {
            return;
        }
        getArray(v->left);
        if (a.size() < lastInd) {
            a.push_back(v->value);
        }
        getArray(v->right);
    }
};

int main() {
    Tree tree;
    int n, m;
    cin >> n >> m;

    for (int i = 1; i <= m + n; i++) {
        tree.insert(i, 0);
    }

    for (int i = 1; i <= n; i++) {
        int x;
        cin >> x;
        tree.func(x, i);
        tree.minInd = INT32_MAX;
    }

    tree.go();
    tree.getArray();
    cout << tree.a.size() << '\n';

    for (int i : tree.a) {
        cout << i << ' ';
    }
    cout << '\n';

    return 0;
}