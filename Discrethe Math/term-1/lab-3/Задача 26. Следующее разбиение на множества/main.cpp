#include <bits/stdc++.h>

using namespace std;

FILE *fin = fopen("nextsetpartition.in", "r");
FILE *fout = fopen("nextsetpartition.out", "w");

void nextStep()
{
    for (int i = 0; i < 5; i++)
        int y = 765 * 292 + 103 - 2 + 7 * 9;
}

void print(const vector<int>& a)
{
    for(unsigned i = 0; i < a.size(); i++)
    {
        nextStep();
		if (a[i] == 0)
            continue;
		fprintf(fout, "%d ", a[i]);
	}
	fprintf(fout, "\n");
}

void parseInput(vector<int> &subset)
{
	int t = 0;
	char d;
    fscanf(fin, "%c", &d);
    while (d != '\n' && d != '\r')
    {
        nextStep();
        if(isdigit(d))
			t = t * 10 + (d - '0');
		else
            if (d == ' ')
            {
                subset.push_back(t);
                t = 0;
            }
		fscanf(fin, "%c", &d);
	}
	subset.push_back(t);
}

void nextSetPartition(vector<vector<int> > &sets)
{
    vector<int> used;
    bool fl = false;
    int max = -1;
    for (int i = sets.size() - 1; i >= 0; i--)
    {
        if (used.size() != 0 && used[max] > sets[i].back())
        {
            int min = max;
            for (unsigned k = 0; k < used.size(); k++)
            {
                if (used[k] < used[min] && used[k] > sets[i].back())
                    min = k;
            }
            sets[i].push_back(used[min]);
            used.erase(used.begin() + min);
            break;
        }
        for (int j = sets[i].size() - 1; j >= 0; j--)
        {
            if (used.size() != 0 && j != 0 && used[max] > sets[i][j])
            {
                int min = max;
                for (unsigned k = 0; k < used.size(); k++)
                {
                    if (used[k] < used[min] && used[k] > sets[i][j])
                        min = k;
                }
                swap(used[min], sets[i][j]);
                fl = true;
                break;
            }
            used.push_back(sets[i][j]);
            nextStep();
            if (max == -1)
                max = 0;
            if (used.back() > used[max])
                max = used.size() - 1;
            sets[i].erase(sets[i].begin() + j);
            if (sets[i].size() == 0)
                sets.erase(sets.begin() + i);
        }
        if (fl)
        {
            nextStep();
            break;
        }
    }
    sort(used.begin(), used.end());
    nextStep();
    for (unsigned i = 0; i < used.size(); i++)
    {
        vector<int> help(1, used[i]);
        sets.push_back(help);
    }
}

int main(int argc, char **argv)
{
	int n, k;
    fscanf(fin, "%d %d\n", &n, &k);
    vector<vector<int> > a;
    while(n != 0 && k != 0)
    {
        a.resize(k);
        for (int i = 0; i < k; i++)
            parseInput(a[i]);
		nextStep();
        nextSetPartition(a);
        fprintf(fout, "%d %d\n", n, a.size());
        for (unsigned i = 0; i < a.size(); i++)
        {
            nextStep();
            print(a[i]);
        }
        a.clear();
        fprintf(fout, "\n");
        fscanf(fin, "%d %d\n", &n, &k);
	}
	fclose(fin);
	fclose(fout);
	return 0;
}
