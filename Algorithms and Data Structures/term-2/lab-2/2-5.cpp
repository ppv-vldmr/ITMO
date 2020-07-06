#include <iostream>
#include <fstream>
#include <string>

using namespace std;

struct Node {
    long key, height, size;
    Node *left_child, *right_child;
    Node(long key): key(key), height(1),size(0),left_child(nullptr), right_child(nullptr) {}
};

int size(Node *p) {
    return (p == nullptr) ? -1 : p->size;
}

void correct_size(Node *p) {
    p->size = size(p->left_child) + size(p->right_child) + 2;
}

int height(Node *p) {
    return (p == nullptr) ? 0 : p->height;
}

void correct_height(Node *p) {
    p->height = ((height(p->left_child) > height(p->right_child)) ? height(p->left_child) : height(p->right_child)) + 1;
}

int balance_differents(Node *p) {
    return height(p->right_child) - height(p->left_child);
}

Node* rotate_left(Node *p) {
    Node *right = p->right_child;
    p->right_child = right->left_child;
    right->left_child = p;
    correct_size(p);
    correct_size(right);
    correct_height(p);
    correct_height(right);
    return right;
}

Node* rotate_right(Node *p) {
    Node *left = p->left_child;
    p->left_child = left->right_child;
    left->right_child = p;
    correct_size(p);
    correct_size(left);
    correct_height(p);
    correct_height(left);
    return left;
}

Node* balance(Node *p) {
    correct_size(p);
    correct_height(p);
    if (balance_differents(p) == 2) {
        if (balance_differents(p->right_child) < 0) {
            p->right_child = rotate_right(p->right_child);
        }
        return rotate_left(p);
    }
    if (balance_differents(p) == -2) {
        if (balance_differents(p->left_child) > 0) {
            p->left_child = rotate_left(p->left_child);
        }
        return rotate_right(p);
    }
    return p;
}

Node* find_k(Node *p, int k) {
    if (p->right_child == nullptr) return (k == 1) ? p : find_k(p->left_child, k - 1);
    if (k == 2 + size(p->right_child)) return p;
    if (k > 2 + size(p->right_child)) return find_k(p->left_child, k - 2 - size(p->right_child));
    return find_k(p->right_child, k);
}

Node* insert(Node *p, long key) {
    if (p == nullptr) 	return new Node(key);
    if (p->key > key) {
        p->left_child = insert(p->left_child, key);
    } else {
        p->right_child = insert(p->right_child, key);
    }
    correct_size(p);
    correct_height(p);
    return balance(p);
}

Node* find_min(Node *p) {
    return (p->left_child == nullptr) ? p : find_min(p->left_child);
}

Node* remove_min(Node *p) {
    if (p->left_child == nullptr) return p->right_child;
    p->left_child = remove_min(p->left_child);
    correct_size(p);
    correct_height(p);
    return balance(p);
}

Node* remove(Node *p, long key) {
    if (p == nullptr) return nullptr;
    if (key < p->key) {
        p->left_child = remove(p->left_child, key);
        if (p->left_child != nullptr) {
            correct_size(p->left_child);
            correct_height(p->left_child);
        }
    } else if (key > p->key) {
        p->right_child = remove(p->right_child, key);
        if (p->right_child != nullptr) {
            correct_height(p->right_child);
            correct_size(p->right_child);
        }
    } else {
        Node *left_child = p->left_child;
        Node *right_child = p->right_child;
        if (right_child == nullptr) return left_child;
        Node *min = find_min(right_child);
        min->right_child = remove_min(right_child);
        min->left_child = left_child;
        correct_size(min);
        correct_height(min);
        return balance(min);
    }
    return balance(p);
}

int main() {
    ios_base::sync_with_stdio(false);
    long x, n, op;
    Node *tree = nullptr;
    cin >> n;
    for (size_t i = 0; i < n; ++i)
    {
        cin >> op >> x;
        if (op == 1) {
            tree = insert(tree, x);
        }
        if (op == -1) {
            tree = remove(tree, x);
        }
        if (op == 0) {
            cout << find_k(tree, x)->key << '\n';
        }
    }
    return 0;
}