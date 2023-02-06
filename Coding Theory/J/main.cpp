#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <string>
#include <random>

using namespace std;

struct PolynomialUtils {
    static vector<int> alphas_count(int n, int p, vector<int> &a) {
        int mask = p;
        int bit = 1; //highest bit
        while (mask > 1) {
            mask >>= 1;
            bit <<= 1;
        }

        a[0] = 1;
        vector<int> pows = vector<int>();
        for (int i = 0; i < n + 1; i++) {
            pows.push_back(-1);
        }
        pows[1] = 0;

        int i = 1;
        while (i < n) {
            a[i] = a[i - 1] << 1;
            if (a[i - 1] << 1 & bit) {
                a[i] ^= p;
            }
            pows[a[i]] = i;
            i++;
        }

        return pows;
    }

    static void cycles_count(int n, vector<vector<int>> &cycles) {
        cycles.push_back(vector<int>{0});
        vector<bool> used = vector<bool>();
        for (int i = 0; i < n; i++) {
            used.push_back(false);
        }

        int i = 1;
        while (i < n) {
            if (used[i]) {
                i++;
                continue;
            }
            vector<int> current;
            current.push_back(i);
            used[i] = true;
            int iter = (i * 2) % n;

            while (current[0] != iter) {
                current.push_back(iter);
                used[iter] = true;
                iter = (iter * 2) % n;
            }
            cycles.push_back(current);
            i++;
        }
    }

    static void multiply(int n, vector<int> &a, vector<int> &b, vector<int> &alphas, vector<int> &pows) {
        vector<int> res(a.size() + b.size() - 1);
        int i = 0;
        while (i < a.size()) {
            int j = 0;
            while (j < b.size()) {
                if (a[i] != 0 && b[j] != 0) {
                    int index = i + j;
                    res[index] = (res[index] ^ alphas[(pows[a[index - j]] + pows[b[index - i]]) % n]);
                } else {
                    res[i + j] = res[i + j] ^ 0;
                }
                j++;
            }
            i++;
        }
        a = res;
    }

    static int multiply(int a, int b, vector<int> &alphas, vector<int> &pows, int n) {
        if (a != 0 && b != 0) {
            int index = pows[a] + pows[b];
            return alphas[index % n];
        } else {
            return 0;
        }
    }

    static void search_polynom(int n, int d, vector<int> &g, vector<vector<int>> &cycles, vector<int> &alphas, vector<int> &pows) {
        int i = 1;
        while (i < cycles.size()) {
            if (cycles[i][0] <= d - 1) {
                vector<int> cycle = cycles[i];
                vector<int> m = vector<int>(1, 1);
                int j = 0;
                while (j < cycle.size()) {
                    vector<int> tmp = {alphas[cycle[j]], 1};
                    PolynomialUtils::multiply(n, m, tmp, alphas, pows);
                    j++;
                }
                PolynomialUtils::multiply(n, g, m, alphas, pows);
            }
            i++;
        }
    }

    static vector<int> syndrome_count(int n, int d, vector<int> &input, vector<int> &alphas) {
        vector<int> syndrome(d);
        int j = 1;
        while (j < d) {
            int tmp = 0;
            int i = 0;
            while (i < input.size()) {
                if (input[i]) {
                    tmp = tmp xor alphas[(i * j) % n];
                }
                i++;
            }
            syndrome[j] = tmp;
            j++;
        }
        return syndrome;
    }
};

struct WorkflowMethods {
    static vector<int> encode(vector<int> &g, vector<int> &input) {
        vector<int> res = vector<int>();
        vector<int> tmp = vector<int>();
        for (int i = 0; i < (int) g.size() - 1; i++) {
            res.push_back(0);
            tmp.push_back(0);
        }
        for (int i = 0; i < input.size(); i++) {
            res.push_back(input[i]);
            tmp.push_back(input[i]);
        }

        int i = (int) res.size() - 1;
        while (i >= (int) g.size() - 1) {
            if (tmp[i]) {
                int j = 0;
                while (j < g.size()) {
                    int index = i - j;
                    tmp[index] = tmp[index] xor g[g.size() - 1 - j];
                    j++;
                }
            }
            i--;
        }
        i = 0;
        while (i < g.size() - 1) {
            res[i] = tmp[i];
            i++;
        }
        return res;
    }

    static vector<int> decode(int n, int d, vector<int> &input, vector<int> &alphas, vector<int> &pows) {
        vector<int> syndrome = PolynomialUtils::syndrome_count(n, d, input, alphas);

        vector<int> comp{1};
        vector<int> locator{1};
        int L = 0;

        int r = 1;
        while (r < d) {
            int delta = 0;
            int j = 0;
            while (j <= L) {
                int multiplied = PolynomialUtils::multiply(locator[j], syndrome[r - j], alphas, pows, n);
                delta = delta xor multiplied;
                j++;
            }

            comp.insert(comp.begin(), 0);

            if (delta != 0) {
                vector<int> tmp = vector<int>();
                for (int i = 0; i < max(comp.size(), locator.size()); i++) {
                    int left = 0;
                    if (locator.size() > i) {
                        left = locator[i];
                    }
                    int right;
                    if (comp.size() > i) {
                        right = PolynomialUtils::multiply(comp[i], delta, alphas, pows, n);
                    } else {
                        right = PolynomialUtils::multiply(0, delta, alphas, pows, n);
                    }

                    tmp.push_back(left xor right);
                }

                if (2 * L <= r - 1) {
                    comp.clear();
                    L = r - L;
                    int cur = alphas[(n - pows[delta]) % n];
                    int i = 0;
                    while (i < locator.size()) {
                        comp.push_back(PolynomialUtils::multiply(cur, locator[i], alphas, pows, n));
                        i++;
                    }
                }
                locator = tmp;
            }
            r++;
        }

        vector<int> res = input;
        int i = 1;
        while (i <= n) {
            int err = -1;
            int j = 0;
            while (j < locator.size()) {
                err ^= PolynomialUtils::multiply(locator[j], alphas[(pows[i] * j) % n], alphas, pows, n);
                j++;
            }
            if (err == -1) {
                int index = (n - pows[i]) % n;
                res[index] = !res[index];
            }
            i++;
        }
        return res;
    }
};

void speed_up() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
}

void work_with_files() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
}

void count_all(
        int n,
        int p,
        int d,
        vector<int> &alphas,
        vector<int> &pows,
        vector<vector<int>> &cycles,
        vector<int> &g
) {
    alphas = vector<int>(n);
    pows = PolynomialUtils::alphas_count(n, p, alphas);
    PolynomialUtils::cycles_count(n, cycles);
    g = {1};
    PolynomialUtils::search_polynom(n, d, g, cycles, alphas, pows);
}

struct CommandsExecutorIO {
    static void runEncode(int k, vector<int> &g) {
        int tmp;
        vector<int> input(k);
        int i = 0;
        while (i < k) {
            cin >> tmp;
            input[i] = tmp;
            i++;
        }
        vector<int> res = WorkflowMethods::encode(g, input);

        i = 0;
        while (i < res.size()) {
            cout << res[i] << " ";
            i++;
        }
        cout << '\n';
    }

    static void runDecode(int n, int d, vector<int> &alphas, vector<int> &pows) {
        vector<int> input;
        int i = 0;
        int tmp;
        while (i < n) {
            cin >> tmp;
            input.push_back(tmp);
            i++;
        }

        vector<int> decoded = WorkflowMethods::decode(n, d, input, alphas, pows);
        i = 0;
        while (i < decoded.size()) {
            cout << decoded[i] << " ";
            i++;
        }
        cout << "\n";
    }
};

int main() {
    speed_up();
    work_with_files();

    int n, p, d;
    cin >> n >> p >> d;

    //parameters for simulation
    double noise;
    int iterations, max_errors;

    vector<int> alphas;
    vector<int> pows;
    vector<vector<int>> cycles;
    vector<int> g;
    count_all(n, p, d, alphas, pows, cycles, g);

    int k = n - (g.size() - 1);
    cout << n - (g.size() - 1) << '\n';

    int i = 0;
    while (i < g.size()) {
        cout << g[i] << " ";
        i++;
    }
    cout << '\n';

    string command;
    while (cin >> command) {
        if (command == "Encode") {
            CommandsExecutorIO::runEncode(k, g);
        }
        if (command == "Decode") {
            CommandsExecutorIO::runDecode(n, d, alphas, pows);
        }
        if (command == "Simulate") {
            cin >> noise >> iterations >> max_errors;

            random_device device;
            auto uniform = uniform_int_distribution<>(0, 1);
            mt19937 gen{device()};
            auto real_distribution = uniform_real_distribution<>();

            int iteration, errors = 0;

            vector<int> noised = vector<int>();
            i = 0;
            while (i < n) {
                noised.push_back(0);
                i++;
            }

            vector<int> random_word = vector<int>();
            i = 0;
            while (i < k) {
                random_word.push_back(0);
                i++;
            }

            iteration = 0;
            while (iteration < iterations) {
                i = 0;
                while (i < k) {
                    random_word[i] = uniform(device);
                    i++;
                }

                vector<int> encoded = WorkflowMethods::encode(g, random_word);

                i = 0;
                while (i < n) {
                    if (real_distribution(gen) >= noise) {
                        noised[i] = encoded[i];
                    } else {
                        noised[i] = !encoded[i];
                    }
                    i++;
                }

                if (WorkflowMethods::decode(n, d, noised, alphas, pows) != encoded) {
                    errors++;
                }
                if (errors == max_errors) {
                    break;
                }
                iteration++;
            }
            cout << fixed << 1.0 * errors / iteration << '\n';
        }
    }
    return 0;
}