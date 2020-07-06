#include <iostream>
#include <string>
#include <fstream>
 
using namespace std;
int n, m;
 
class Node
{
public:
	int y;
	int sons;
	int value;
	Node *left;
	Node *right;
 
	Node(int k, int pr) : 
		left(0),
		right(0),
		sons(1),
		value(k),
		y(pr){}
};
 
void print_tree(Node *p)
{
	if (p == nullptr)
		return;
	print_tree(p->left);
	cout << p->value << " ";
	print_tree(p->right);
}
 
void update(Node *p)
{
	if (p != nullptr) {
		if (p->left == nullptr && p->right == nullptr)
			p->sons = 1;
		else if (p->left == nullptr)
			p->sons = (p->right)->sons + 1;
		else if (p->right == nullptr)
			p->sons = (p->left)->sons + 1;
		else
			p->sons = (p->left)->sons + (p->right)->sons + 1;
	}
 
}
 
void merg(Node* &p, Node *p1, Node *p2)
{
	if (p1 == nullptr) {
		p = p2;
		return;
	}
	if (p2 == nullptr) {
		p = p1;
		return;
	}
	if (p1->y > p2->y) {
		merg(p1->right, p1->right, p2);
		p = p1;
	} else {
		merg(p2->left, p1, p2->left);
		p = p2;
	}
	update(p);
}
 
void split(Node *p, Node* &p1, Node* &p2, int key, int current)
{
	if (p == nullptr)
	{
		p1 = nullptr;
		p2 = nullptr;
		return;
	}
	int cur = current;
	if (p->left != nullptr)
	{
		cur += (p->left)->sons;
	}
	if (key <= cur)
	{
		split(p->left, p1, p->left, key, current);
		p2 = p;
	}
	else
	{
		if (p->left == nullptr)
		{
			split(p->right, p->right, p2, key, current + 1);
		}
		else
		{
			split(p->right, p->right, p2, key, current + (p->left)->sons + 1);
		}
		p1 = p;
	}
	update(p);
}
 
void insert(Node* &p, int k, int pr)
{
	Node *tmp = new Node(k, pr);
	merg(p, p, tmp);
}
 
int main()
{
	cin >> n >> m;
	Node *tree = nullptr, *t1m, *t2m, *t3m;
	for (int i = 0; i < n; i++)
		insert(tree, i + 1, rand() % 10000);
	for (int i = 0; i < m; i++)
	{
		int left, right;
		cin >> left >> right;
		split(tree, t1m, t2m, left - 1, 0);
		split(t2m, t2m, t3m, right - left + 1, 0);
		merg(tree, t2m, t1m);
		merg(tree, tree, t3m);
	}
	print_tree(tree);
	cin >> n; 
	return 0;
}