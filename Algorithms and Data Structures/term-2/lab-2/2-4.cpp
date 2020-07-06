#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <vector>
 
#define LONG_MAX 9223372036854775807
#define LONG_MIN -9223372036854775808
 
using namespace std;
 
struct Node {
	long long key, height, size, sum, min, max;
	Node *left_child, *right_child;
	Node(long long key) : key(key), height(1), size(0), sum(0), min(key), max(key), left_child(nullptr), right_child(nullptr) {}
};
 
long long get_min(Node* p) {
	return (p == nullptr) ? LONG_MAX : p->min;
}
 
long long get_max(Node* p) {
	return (p == nullptr) ? LONG_MIN : p->max;
}
 
long long get_sum(Node* p) {
	return (p == nullptr) ? 0 : p->sum;
}
 
long long size(Node *p) {
	return (p == nullptr) ? -1 : p->size;
}
 
void correct_size(Node *p) {
	p->size = size(p->left_child) + size(p->right_child) + 2;
}
 
long long height(Node *p) {
	return (p == nullptr) ? 0 : p->height;
}
 
void correct_height(Node *p) {
	p->height = ((height(p->left_child) > height(p->right_child)) ? height(p->left_child) : height(p->right_child)) + 1;
}
 
long long recover_sum(Node* p) {
	return (p == nullptr) ? 0 : p->sum = recover_sum(p->left_child) + recover_sum(p->right_child) + p->key;
}
 
void correct_min_max(Node* p) {
	p->max = max(p->key, max(get_max(p->left_child), get_max(p->right_child)));
	p->min = min(p->key, min(get_min(p->left_child), get_min(p->right_child)));
}
 
void correct_sum(Node* p) {
	p->sum = get_sum(p->left_child) + get_sum(p->right_child);
	if (p->left_child != nullptr)
		p->sum += p->left_child->key;
	if (p->right_child != nullptr)
		p->sum += p->right_child->key;
}
 
long long balance_differents(Node *p) {
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
	correct_min_max(p);
	correct_min_max(right);
	correct_sum(p);
	correct_sum(right);
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
	correct_min_max(p);
	correct_min_max(left);
	correct_sum(p);
	correct_sum(left);
	return left;
}
 
Node* balance(Node *p) {
	correct_size(p);
	correct_height(p);
	correct_min_max(p);
	correct_sum(p);
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
 
Node* find_k(Node *p, long long k) {
	if (p->right_child == nullptr) return (k == 1) ? p : find_k(p->left_child, k - 1);
	if (k == 2 + size(p->right_child)) return p;
	if (k > 2 + size(p->right_child)) return find_k(p->left_child, k - 2 - size(p->right_child));
	return find_k(p->right_child, k);
}
 
Node* insert(Node *p, long long key) {
	if (p == nullptr) 	return new Node(key);
	if (p->key > key) {
		p->left_child = insert(p->left_child, key);
	}
	else {
		p->right_child = insert(p->right_child, key);
	}
	correct_size(p);
	correct_height(p);
	correct_min_max(p);
	correct_sum(p);
	return balance(p);
}
 
Node* find_min(Node *p) {
	return (p->left_child == nullptr) ? p : find_min(p->left_child);
}
 
Node* find_max(Node *p) {
	return (p->right_child == nullptr) ? p : find_max(p->right_child);
}
 
Node* remove_min(Node *p) {
	if (p->left_child == nullptr) return p->right_child;
	p->left_child = remove_min(p->left_child);
	correct_size(p);
	correct_height(p);
	correct_min_max(p);
	correct_sum(p);
	return balance(p);
}
 
Node* remove(Node *p, long long key) {
	if (p == nullptr) return nullptr;
	if (key < p->key) {
		p->left_child = remove(p->left_child, key);
		if (p->left_child != nullptr) {
			correct_size(p->left_child);
			correct_height(p->left_child);
			correct_min_max(p->left_child);
			correct_sum(p->left_child);
		}
	}
	else if (key > p->key) {
		p->right_child = remove(p->right_child, key);
		if (p->right_child != nullptr) {
			correct_height(p->right_child);
			correct_size(p->right_child);
			correct_min_max(p->right_child);
			correct_sum(p->right_child);
		}
	}
	else {
		Node *left_child = p->left_child;
		Node *right_child = p->right_child;
		if (right_child == nullptr) return left_child;
		Node *min = find_min(right_child);
		min->right_child = remove_min(right_child);
		min->left_child = left_child;
		correct_size(min);
		correct_height(min);
		correct_min_max(min);
		correct_sum(min);
		return balance(min);
	}
	return balance(p);
}
 
bool check(Node *p, long long key) {
	if (p == nullptr) return 0;
	if (key < p->key) return check(p->left_child, key);
	if (key > p->key) return check(p->right_child, key);
	if (key == p->key) return 1;
}
 
long long rec_sum(Node* p) {
	return (p == nullptr) ? 0 : rec_sum(p->left_child) + p->key + rec_sum(p->right_child);
}
 
long long sum(Node* p, long long l, long long r, long long left_gr, long long right_gr) {
	if (p == nullptr) return 0;
	if (l > r || left_gr > right_gr || right_gr < l || left_gr > r) return 0;
	//if (l <= left_gr && right_gr <= r) return rec_sum(p);
	if (l <= left_gr && right_gr <= r) return p->sum;
	long long l1 = (p->left_child != nullptr) ? find_max(p->left_child)->key : -1000000000, r1 = (p->right_child != nullptr) ? find_min(p->right_child)->key : 1000000000;
	long long sum_tre = sum(p->left_child, l, min(r,l1), left_gr, l1) + sum(p->right_child, max(l,r1), r, r1, right_gr);
	if (l <= p->key && p->key <= r) sum_tre += p->key;
	return sum_tre;
}
 
long long sum(Node* p, long long left, long long right) {
	if (p == nullptr) 
		return 0;
	if (p->key > right) 
		return sum(p->left_child, left, right);
	if (p->key < left) 
		return sum(p->right_child, left, right);
	if (p->left_child == nullptr && p->right_child == nullptr) 
		return p->key;
	if (get_min(p) >= left && get_max(p) <= right) 
		return get_sum(p) + p->key;
	return sum(p->left_child, left, right) + sum(p->right_child, left, right) + p->key;
}
 
Node* next(Node* p, long long key) {
	Node* current = p;
	Node* child = nullptr;
	if (p == nullptr) {
		return nullptr;
	}
	while (true) {
		if (current == nullptr) {
			break;
		}
		if (current->key > key) {
			child = current;
			current = current->left_child;
		} else {
			current = current->right_child;
		}
	}
	return child;
}
 
 
int main() {
	ios_base::sync_with_stdio(false);
	long long x, n, tmp_l, tmp_r, y = 0;
	char op;
	Node *tree = nullptr;
	cin >> n;
	for (size_t i = 0; i < n; ++i)
	{
		cin >> op;
		if (op == '+') {
			cin >> x;
			x = (x + y) % 1000000000;
			y = 0;
			if (!check(tree, x))
			{
				tree = insert(tree, x);
			}	
		} else {
			cin >> tmp_l >> tmp_r;
			y = sum(tree, tmp_l, tmp_r);
			cout << y << '\n';
		}
	}
	return 0;
}