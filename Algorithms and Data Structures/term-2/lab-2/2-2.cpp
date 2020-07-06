#include <iostream>
#include <fstream>
#include <string>
 
using namespace std;
 
class Node 
{
public:
	int key;
	unsigned char height;
	Node* left;
	Node* right;
	Node(int k) : key(k), left(0), right(0), height(1) {}
};
 
unsigned char height(Node* p)
{
	if (p != nullptr) return p->height;
	return 0;
}
 
int bfactor(Node* p)
{
	return height(p->right) - height(p->left);
}
 
void fixheight(Node* p)
{
	unsigned char hl = height(p->left);
	unsigned char hr = height(p->right);
	if (hl > hr)
		p->height = hl;
	else
		p->height = hr;
	++p->height;
}
 
Node* rotateright(Node* p)
{
	Node* q = p->left;
	p->left = q->right;
	q->right = p;
	fixheight(p);
	fixheight(q);
	return q;
}
 
Node* rotateleft(Node* q)
{
	Node* p = q->right;
	q->right = p->left;
	p->left = q;
	fixheight(q);
	fixheight(p);
	return p;
}
 
Node* balance(Node* p) 
{
	fixheight(p);
	unsigned int tmp = bfactor(p);
	if (tmp == 2)
	{
		if (bfactor(p->right) < 0)
			p->right = rotateright(p->right);
		return rotateleft(p);
	}
	if (tmp == -2)
	{
		if (bfactor(p->left) > 0)
			p->left = rotateleft(p->left);
		return rotateright(p);
	}
	return p; 
}
 
Node* insert(Node* p, int k) 
{
	if (!p) return new Node(k);
	if (k < p->key)
		p->left = insert(p->left, k);
	else if (k > p->key)
		p->right = insert(p->right, k);
	if (k == p->key) return p;
	return balance(p);
}
 
Node* findmin(Node* p) 
{
	return p->left ? findmin(p->left) : p;
}
 
Node* removemin(Node* p) 
{
	if (p->left == 0)
		return p->right;
	p->left = removemin(p->left);
	return balance(p);
}
 
Node* remove(Node* p, int k) 
{
	if (!p) return 0;
	if (k < p->key)
		p->left = remove(p->left, k);
	else if (k > p->key)
		p->right = remove(p->right, k);
	else //  k == p->key 
	{
		Node* q = p->left;
		Node* r = p->right;
		delete p;
		if (!r) return q;
		Node* min = findmin(r);
		min->right = removemin(r);
		min->left = q;
		return balance(min);
	}
	return balance(p);
}
 
bool exists(Node* p, int k) {
	if (!p) return false;
	if (p->key == k) return true;
	if (k < p->key)
		return exists(p->left, k);
	else
		return exists(p->right, k);
}
 
Node* next_el(Node* p, int k) {
	Node *current = p, *successor = nullptr;
	while (current != nullptr) {
		if (current->key > k) {
			successor = current;
			current = current->left;
		}
		else {
			current = current->right;
		}
	}
	return successor;
}
 
Node* prev_el(Node* p, int k) {
	Node *current = p, *successor = nullptr;
	while (current != nullptr) {
		if (current->key < k) {
			successor = current;
			current = current->right;
		}
		else {
			current = current->left;
		}
	}
	return successor;
}
 
int main() {
	string str = "";
	int x;
	Node *tmp;
	Node *tree = nullptr;
	while (cin >> str >> x)
	{
		if (str == "insert") {
			tree = insert(tree, x);
		}
		if (str == "exists") {
			cout << ((exists(tree, x) == 1) ? "true" : "false") << '\n';
		}
		if (str == "next") {
			tmp = next_el(tree, x);
			if (tmp == nullptr)
				cout << "none\n";
			else
				cout << tmp->key << '\n';
		}
		if (str == "prev") {
			tmp = prev_el(tree, x);
			if (tmp == nullptr)
				cout << "none\n";
			else
				cout << tmp->key << '\n';
		}
		if (str == "delete") {
			tree = remove(tree, x);
		}
		str = "";
	}
	return 0;
}