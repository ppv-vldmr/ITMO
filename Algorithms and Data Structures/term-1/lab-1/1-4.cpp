#include <bits/stdc++.h>

using namespace std;

void SiftUp(int x, vector<int> &heap) {
    while (x > 0 && heap[x] > heap[(x - 1)/2]) {
        swap(heap[x], heap[(x - 1)/2]);
        x = (x - 1)/2;
    }
}

void SiftDown(vector<int> &heap) {
    int i = 0, j;
    while (true) {
        j = i;
        if (2*i + 1 < heap.size() && heap[2*i + 1] > heap[j])
            j = 2*i + 1;
        if (2*i + 2 < heap.size() && heap[2*i + 2] > heap[j])
            j = 2*i + 2;
        if (i == j) break;
        swap(heap[i], heap[j]);
        i = j;
    }
    /*for (int i = 0; i < heap.size(); ++i)
        cout << heap[i] << " ";
    cout << endl;*/
}

void Insert(int x, vector<int> &heap) {
    heap.push_back(x);
    SiftUp(heap.size() - 1, heap);
    /*for (int i = 0; i < heap.size(); ++i)
        cout << heap[i] << " ";
    cout << endl;*/
}

void Extract(vector<int> &heap) {
    cout << heap[0] << endl;
    swap(heap.front(), heap.back());
    heap.pop_back();
    /*for (int i = 0; i < heap.size(); i++)
        cout << heap[i] << " ";
    cout << endl;*/
    SiftDown(heap);
}

int main()
{
    int n;
    vector<int> heap;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int c, x;
        cin >> c;
        if (c == 0) {
            cin >> x;
            Insert(x, heap);
        } else Extract(heap);
    }
    return 0;
}
