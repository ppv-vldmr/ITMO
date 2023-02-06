#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <string>
#include <random>

using namespace std;

vector<int> count_alphas(int n, int p, vector<int> &a) {
    int bit = 1; //highest bit
    int mask = p;
    while (mask > 1) {
        bit <<= 1;
        mask >>= 1;
    }

    a[0] = 1;
    vector<int> pows(n + 1, -1);
    pows[1] = 0;
    for (int i = 1; i < n; i++) {
        a[i] = a[i - 1] << 1;
        if (a[i] & bit) {
            a[i] = a[i] ^ p;
        }
        pows[a[i]] = i;
    }

//    cout << "alphas: ";
//    for (int i: a) {
//        cout << i << " ";
//    }
//    cout << endl;

//    cout << "pows: ";
//    for (int i: pows) {
//        cout << i << " ";
//    }
//    cout << pows[63];
//    cout << endl;

    return pows;
}

void count_cycles(int n, vector<vector<int>> &cycles) {
    cycles.push_back(vector<int>{0});
    vector<bool> used(n, false);

    for (int i = 1; i < n; i++) {
        if (!used[i]) {
            used[i] = true;
            vector<int> current;
            current.push_back(i);
            int iter = (i * 2) % n;

            while (current[0] != iter) {
                used[iter] = true;
                current.push_back(iter);
                iter *= 2;
                iter %= n;
            }
            cycles.push_back(current);
        }
    }

//    cout << "cycles: " << endl;
//    for (auto &cycle: cycles) {
//        for (int j: cycle) {
//            cout << j << ' ';
//        }
//        cout << endl;
//    }
}

void multi(vector<int> &a, vector<int> &b, vector<int> &alphas, int n, vector<int> &pows) {
    vector<int> res(a.size() + b.size() - 1);
    for (int i = 0; i < a.size(); i++) {
        for (int j = 0; j < b.size(); j++) {
            if (a[i] == 0 || b[j] == 0) {
                res[i + j] = res[i + j] ^ 0;
            } else {
                res[i + j] = (res[i + j] ^ alphas[(pows[a[i]] + pows[b[j]]) % n]);
            }
        }
    }
    a = res;
}

void
find_polynomial(vector<int> &g, int n, vector<vector<int>> &cycles, vector<int> &alphas, vector<int> &pows, int d) {
    for (int i = 1; i < cycles.size(); i++) {
        if (cycles[i][0] > d - 1) {
            continue;
        }
        auto cycle = cycles[i];
        vector<int> m{1};
        for (int element: cycle) {
            vector<int> tmp = {alphas[element], 1};
            multi(m, tmp, alphas, n, pows);
        }

//        cout << "Mi" << endl;
//        for (int re: m) {
//            cout << re << " ";
//        }
//        cout << endl;

        multi(g, m, alphas, n, pows);
    }
}

vector<int> count_syndrome(vector<int> &input, int d, int n, vector<int> &alphas) {
    vector<int> syndrome(d);
    for (int j = 1; j < d; j++) {
        int tmp = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input[i]) tmp ^= alphas[(i * j) % n];
        }
        syndrome[j] = tmp;
    }
    return syndrome;
}

vector<int> encode(vector<int> &g, vector<int> &input) {
    vector<int> res((int) g.size() - 1, 0), tmp((int) g.size() - 1, 0);
    res.insert(res.end(), input.cbegin(), input.cend());
    tmp.insert(tmp.end(), input.cbegin(), input.cend());

    for (int i = (int) res.size() - 1; i >= (int) g.size() - 1; i--) {
        if (tmp[i]) {
            for (int j = 0; j < g.size(); j++) {
                tmp[i - j] ^= g[g.size() - 1 - j];
            }
        }
    }
    for (int i = 0; i < g.size() - 1; i++) res[i] = tmp[i];
    return res;
}

int multiply(int a, int b, vector<int> &alphas, vector<int> &pows, int n) {
    return a == 0 || b == 0 ? 0 : alphas[(pows[a] + pows[b]) % n];
}

vector<int> decode(vector<int> &input, int d, int n, vector<int> &alphas, vector<int> &pows) {
    vector<int> syndrome = count_syndrome(input, d, n, alphas);

//    cout << "syndrome: ";
//    for (int i: syndrome) {
//        cout << i << " ";
//    }
//    cout << endl;

    int L = 0;
    vector<int> locator(1, 1);
    vector<int> comp(1, 1);

    for (int r = 1; r < d; r++) {
        int delta = 0;
        for (int j = 0; j <= L; j++) {
            delta = delta ^ multiply(locator[j], syndrome[r - j], alphas, pows, n);
        }
//        cout << delta << " ";

        comp.insert(comp.begin(), 0);

        if (delta != 0) {
            vector<int> tmp(max(comp.size(), locator.size()));
            for (int i = 0; i < tmp.size(); i++) {
                tmp[i] = (locator.size() > i ? locator[i] : 0) ^
                         multiply((comp.size() > i ? comp[i] : 0), delta, alphas, pows, n);
            }

            if (r - 1 >= 2 * L) {
                L = r - L;
                comp.clear();
                int cur = alphas[(n - pows[delta]) % n];
                for (int i: locator) {
                    comp.push_back(multiply(cur, i, alphas, pows, n));
                }
            }
            locator = tmp;
        }
    }
//    cout << "locators: ";
//    for (int i: locator) {
//        cout << i << " ";
//    }
//    cout << endl;

    vector<int> res = input;
    for (int i = 1; i <= n; i++) {
        int err = -1;
        for (int j = 0; j < locator.size(); j++) {
            err = err ^ multiply(locator[j], alphas[(pows[i] * j) % n], alphas, pows, n);
        }
        if (err == -1) {
            res[(n - pows[i]) % n] = !res[(n - pows[i]) % n];
        }
    }
    return res;
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, p, d;

    cin >> n >> p >> d;

    vector<int> alphas(n);
    vector<int> pows = count_alphas(n, p, alphas);

    vector<vector<int>> cycles;
    count_cycles(n, cycles);

    vector<int> g{1};
    find_polynomial(g, n, cycles, alphas, pows, d);

    int k = n - (g.size() - 1);

    cout << k << '\n';

    for (int i: g) {
        cout << i << " ";
    }
    cout << '\n';

    string command;
    while (cin >> command) {
        if (command == "Encode") {
            vector<int> input;
            int tmp;
            for (int i = 0; i < k; i++) {
                cin >> tmp;
                input.push_back(tmp);
            }
            vector<int> res = encode(g, input);

            for (auto &&re: res) {
                cout << re << ' ';
            }
            cout << '\n';
        }

        if (command == "Decode") {
            vector<int> input(n);
            for (int i = 0; i < n; i++) {
                cin >> input[i];
            }

            for (int i: decode(input, d, n, alphas, pows)) {
                cout << i << " ";
            }
            cout << "\n";
        }

        if (command == "Simulate") {
            double noise;
            int iterations, max_errors;
            cin >> noise >> iterations >> max_errors;

            random_device device;
            auto uniform = uniform_int_distribution<>(0, 1);
            mt19937 gen{device()};
            auto real_distribution = uniform_real_distribution<>();

            int iteration, errors = 0;

            vector<int> noisssyyy(n);
            vector<int> random_word(k);

            for (iteration = 0; iteration < iterations; iteration++) {
                for (int i = 0; i < k; i++) {
                    random_word[i] = uniform(device);
                }

                vector<int> encoded = encode(g, random_word);

                for (int i = 0; i < n; i++) {
                    if (real_distribution(gen) < noise) {
                        noisssyyy[i] = !encoded[i];
                    } else {
                        noisssyyy[i] = encoded[i];
                    }
                }

                vector<int> res = decode(noisssyyy, d, n, alphas, pows);

                if (res != encoded) {
                    errors++;
                    if (errors == max_errors) {
                        break;
                    }
                }
            }
            cout << fixed << 1. * errors / iteration << '\n';
        }
    }
    return 0;
}