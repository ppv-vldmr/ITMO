#include <iostream>
#include <algorithm>

using namespace std;

//#define int int64_t

struct Node {
    int child;
    int x;
    int y;
    Node *right;
    Node *left;
    Node (int k, int key) {
        child = 1;
        x = k;
        y = key;
        left = right = nullptr;
    }
};

Node *root = nullptr;

int get_children(Node *v) {
    if (!v)
        return 0;
    else
        return v->child;
}

void children(Node *v) {
    v->child = get_children(v->right) + get_children(v->left) + 1;
}

Node* merge_trees (Node *a, Node *b) {
    if (!a) {
        return b;
    }
    if (!b) {
        return a;
    }

    if (a->y > b->y) {
        a->right = merge_trees(a->right, b);
        children(a);
        return a;
    }
    else {
        b->left = merge_trees(a, b->left);
        children(b);
        return b;
    }
}

pair <Node*, Node*> split (Node *a, int key) {
    if (!a) {
        return {nullptr, nullptr};
    }
    if (key > get_children(a->left)) {
        pair<Node *, Node *> tmp;
        tmp = split(a->right, key - get_children(a->left) - 1);
        a->right = tmp.first;
        children(a);
        return pair<Node*, Node*>(a, tmp.second);
    } else {
        pair<Node *, Node *> tmp;
        tmp = split(a->left, key);
        a->left = tmp.second;
        children(a);
        return pair<Node*, Node*>(tmp.first, a);
    }

}

Node* insert(Node *v, int x, int ind) {
    pair<Node*, Node*> l = split(v, ind);
    Node *tmp = new Node(x, rand());
    Node *res = merge_trees(merge_trees(l.first, tmp), l.second);
    return res;
}

Node* _delete(Node *v, int x) {
    pair<Node*, Node*> l = split(v, x);
    pair<Node* , Node*> tmp = split(l.second, 1);
    Node *res = merge_trees(l.first, tmp.second);
    return res;
}

void print(Node *v) {
    if (v->left) {
        print(v->left);
    }
    cout << v->x << " ";
    if (v->right) {
        print(v->right);
    }
}

signed main() {
    ios::sync_with_stdio(false);

    int n0, m;
    cin >> n0 >> m;
    for (int i = 0; i < n0; i++) {
        int x;
        cin >> x;
        root = insert(root, x, i + 1);
    }

    string x;
    for (int i = 0; i < m; i++) {
        cin >> x;
        if (x == "add") {
            int y, j;
            cin >> y >> j;
            root = insert(root, j, y);
        }
        else {
            int y;
            cin >> y;
            root = _delete(root, y - 1);
        }
    }
    if (root) {
        cout << root->child << '\n';
        print(root);
    }
    else {
        cout << 0;
    }
    return 0;
}