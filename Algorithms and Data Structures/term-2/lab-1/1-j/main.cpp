#include <iostream>
#include <algorithm>
#include <string>

using namespace std;
int netr_max = INT32_MAX;

class Wall
{
    public:
        int value = 0;
        bool flag = false;
};

class WeakWall
{
    public:
        int minimal_hight, index;

        WeakWall()
        {
            this -> minimal_hight = netr_max;
            this -> index = -100;
        }

        WeakWall(int mini, int index)
        {
            this->minimal_hight = mini;
            this->index = index;
        }
};
WeakWall netr;

class Tree {
public:
    int begin, end;
    Tree *left_child = nullptr;
    Tree *right_child = nullptr;
    Wall wall;

    Tree(int begin, int end) {
        this->begin = begin;
        this->end = end;
        build();
    }

    int build() {
        if (end - begin > 1) {
            int mid = (begin + end) / 2;
            left_child = new Tree(begin, mid);
            right_child = new Tree(mid, end);
            wall.value = min(left_child->wall.value, right_child->wall.value);
        }
        return wall.value;
    }

    void propagate() {
        if (wall.flag == true) {
            left_child->wall.flag = true;
            right_child->wall.flag = true;
            left_child->wall.value = max(left_child->wall.value, wall.value);
            right_child->wall.value = max(right_child->wall.value, wall.value);
            wall.flag = false;
        }
    }

    int update(int begin, int end, int new_value) {
        if (begin >= end) {
            return wall.value;
        } else if (begin == this->begin && end == this->end) {
            wall.flag = true;
            wall.value = max(wall.value, new_value);
            return wall.value;
        } else {
            propagate();
            wall.value = min(left_child->update(begin, min(end, left_child->end), new_value), right_child->update(max(begin, right_child->begin), end, new_value));
            return wall.value;
        }
    }

    WeakWall min_wall(int begin, int end) {
        if (begin >= end) {
            return netr;
        } else if (begin == this->begin && end == this->end) {
            if (end - begin > 1) {
                propagate();
                if (left_child->wall.value == wall.value)
                    return left_child->min_wall(begin, min(end, left_child->end));
                else
                    return right_child->min_wall(max(begin, right_child->begin), end);
            } else {
                return WeakWall(wall.value, begin);
            }
        } else {
            propagate();
            WeakWall left = left_child->min_wall(begin, min(end, left_child->end));
            WeakWall right = right_child->min_wall(max(begin, right_child->begin), end);
            if (left.minimal_hight > right.minimal_hight) {
                return right;
            } else if (left.minimal_hight < right.minimal_hight) {
                return left;
            } else {
                int ind = max(left.index, right.index);
                return WeakWall(left.minimal_hight, ind);
            }
        }
    }
};

int main() {
    int n, m, tmp1, tmp2, tmp3;
    string str;
    //ios::sync_with_stdio(false);
    cin >> n >> m;
    Tree tree(0, n);
    for (int i = 0; i < m; i++) {
        cin >> str >> tmp1 >> tmp2;
        tmp1--;
        if (str[0] == 'd')
        {
            cin >> tmp3;
            tree.update(tmp1, tmp2, tmp3);
        }
        else
        {
            WeakWall res = tree.min_wall(tmp1, tmp2);
            cout << res.minimal_hight << ' ' << res.index + 1 << '\n';
        }
    }
    return 0;
}