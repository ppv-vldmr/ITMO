#include <iostream>
#include <vector>
#include <deque>
#include <string>
#include <functional>
#include <chrono>
#include <atomic>
#include "omp.h"

//DEFINE:

using vec_t = std::vector<int>;
using vec_vec_t = std::vector<std::vector<int>>;
using fIntBool_t = std::function<bool(int)>;
using fVoidVoid_t = std::function<void(void)>;

//CONSTANTS:

static constexpr int BLOCK_FOR = (int) 1e3;
static constexpr int BLOCK_SCAN = (int) 1e6;
static constexpr int MAX_SIZE = (int) 500 * 500 * 500;
static constexpr int ITERATIONS = 5;

//UTILS:

std::ostream &operator<<(std::ostream &out, const vec_t &name) {
    for (auto elem: name) {
        out << elem << " ";
    }
    return out;
}

vec_vec_t getCubicGraph(int sideSize) {
    auto check = [](int x, int size) {
        return (0 <= x && x < size);
    };
    auto getNum = [](int i, int j, int k, int base) {
        return ((i * base) + j) * base + k;
    };
    auto addEdge = [](int u, int v, vec_vec_t &graph, int &cnt) {
        graph[v].push_back(u);
        graph[u].push_back(v);
        cnt++;
    };

    int n = sideSize * sideSize * sideSize;
    int m = n * 3 - (sideSize * sideSize) * 3;

    vec_vec_t graph(n);

    int base = sideSize;
    int cnt = 0;
    for (int i = 0; i < sideSize; i++) {
        for (int j = 0; j < sideSize; j++) {
            for (int k = 0; k < sideSize; k++) {
                int v = getNum(i, j, k, base);
                if (check(i + 1, sideSize)) {
                    int u = getNum(i + 1, j, k, base);
                    addEdge(u, v, graph, cnt);
                }
                if (check(j + 1, sideSize)) {
                    int u = getNum(i, j + 1, k, base);
                    addEdge(u, v, graph, cnt);
                }
                if (check(k + 1, sideSize)) {
                    int u = getNum(i, j, k + 1, base);
                    addEdge(u, v, graph, cnt);
                }
            }
        }
    }

    if (cnt != m) {
        throw std::runtime_error("count of edges is wrong!");
    }

    return graph;
}

//PARALLEL UTILS:

struct ParallelUtils {
    static void parallelScanUp(
            int l, int r, int u, const vec_t &data, vec_t &tree
    );

    static void parallelScanDown(
            int delta, int l, int r, int u, const vec_t &data, vec_t &res,
            vec_t &tree
    );

    static void parallelScan(
            const vec_t &data, vec_t &res, int size, vec_t &tree
    );

    static int parallelFilter(
            const vec_t &data, vec_t &res, int size, const fIntBool_t &p,
            vec_t &mask, vec_t &tree
    );
};

void ParallelUtils::parallelScanUp(int l, int r, int u, const vec_t &data, vec_t &tree) {
    if (r - l <= BLOCK_SCAN) {
        int sum = 0;
        for (int i = l; i < r; i++) {
            sum += data[i];
        }
        tree[u] = sum;
    } else {
        int m = (l + r) / 2;
#pragma omp parallel sections default(none) shared(l, m, r, u, data, tree)
        {
#pragma omp section
            {
                parallelScanUp(l, m, 2 * u + 1, data, tree);
            }
#pragma omp section
            {
                parallelScanUp(m, r, 2 * u + 2, data, tree);
            }
        }
        tree[u] = tree[2 * u + 1] + tree[2 * u + 2];
    }
}

void ParallelUtils::parallelScanDown(
        int delta, int l, int r, int u, const vec_t &data, vec_t &res,
        vec_t &tree
) {
    if (r - l <= BLOCK_SCAN) {
        int sum = delta;
        for (int i = l; i < r; i++) {
            sum += data[i];
            res[i] = sum;
        }
    } else {
        int m = (l + r) / 2;
#pragma omp parallel sections default(none) shared(delta, l, m, r, u, data, res, tree)
        {
#pragma omp section
            {
                parallelScanDown(
                        delta, l, m, 2 * u + 1, data, res, tree
                );
            }
#pragma omp section
            {
                parallelScanDown(
                        delta + tree[2 * u + 1], m, r, 2 * u + 2, data, res, tree
                );
            }
        }
    }
}

void ParallelUtils::parallelScan(
        const vec_t &data, vec_t &res, int size, vec_t &tree
) {
    parallelScanUp(0, size, 0, data, tree);
    parallelScanDown(0, 0, size, 0, data, res, tree);
}

int ParallelUtils::parallelFilter(
        const vec_t &data, vec_t &res, int size, const fIntBool_t &p,
        vec_t &mask, vec_t &tree
) {
#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(size, p, data, mask, BLOCK_FOR)
    for (int i = 0; i < size; i++) {
        mask[i] = p(data[i]) ? 1 : 0;
    }
    parallelScan(mask, mask, size, tree);
#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(size, p, data, res, mask, BLOCK_FOR)
    for (int i = 0; i < size; i++) {
        if (p(data[i])) {
            res[mask[i] - 1] = data[i];
        }
    }
    return mask[size - 1];
}

//SIMPLE BFS:

struct SimpleBFS {
    vec_vec_t graph;
    std::vector<std::atomic_int> dist;
    int start;

    SimpleBFS(
            vec_vec_t &graph,
            int start
    );

    vec_t getAnswer() const;
    void run();
};

SimpleBFS::SimpleBFS(vec_vec_t &graph, int start) : graph(graph), start(start) {
    if (graph.size() > MAX_SIZE) {
        throw std::runtime_error("incorrect graph size!");
    }
    dist = std::vector<std::atomic_int>(MAX_SIZE);
}

vec_t SimpleBFS::getAnswer() const {
    vec_t result(graph.size());
    for (int i = 0; i < graph.size(); i++) {
        result[i] = dist[i].load();
    }
    return result;
}

void SimpleBFS::run() {
    std::deque<int> q;
    q.push_back(start);

    int expect = 0;
    dist[start].compare_exchange_strong(expect, 1);

    while (true) {
        int v = q.front();
        q.pop_front();

        for (int i = 0; i < graph[v].size(); i++) {
            int u = graph[v][i];

            expect = 0;
            if (dist[u].compare_exchange_strong(expect, dist[v].load() + 1)) {
                q.push_back(u);
            }
        }

        if (q.empty()) {
            return;
        }
    }
}

//PARALLEL BFS:

struct ParallelBFS {
    vec_vec_t graph;
    std::vector<std::atomic_int> dist;
    int start;

    int maxSize;
    vec_t front0;
    vec_t front1;
    vec_t degrees;
    vec_t mask;
    vec_t tree;

    ParallelBFS(
            vec_vec_t &graph,
            int start,
            int maxSize,
            vec_t &front0,
            vec_t &front1,
            vec_t &degrees,
            vec_t &mask,
            vec_t &tree
    );

    vec_t getAnswer() const;

    void run();
};

ParallelBFS::ParallelBFS(
        vec_vec_t &graph, int start, int maxSize, vec_t &front0, vec_t &front1, vec_t &degrees,
        vec_t &mask, vec_t &tree
) : graph(graph),
    start(start),
    maxSize(maxSize),
    front0(front0),
    front1(front1),
    degrees(degrees),
    mask(mask),
    tree(tree) {
    if (graph.size() > MAX_SIZE) {
        throw std::runtime_error("incorrect graph size!");
    }
    dist = std::vector<std::atomic_int>(MAX_SIZE);
}

vec_t ParallelBFS::getAnswer() const {
    vec_t result(graph.size());
    for (int i = 0; i < graph.size(); i++) {
        result[i] = dist[i].load();
    }
    return result;
}

void ParallelBFS::run() {
#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(BLOCK_FOR)
    for (int i = 0; i < maxSize; i++) {
        front0[i] = -1;
        front1[i] = -1;
    }

    int size = 1;

    front0[0] = start;

    int expect = 0;
    dist[start].compare_exchange_strong(expect, 1);

    while (true) {

#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(size, BLOCK_FOR)
        for (int i = 0; i < size; i++) {
            int v = front0[i];
            degrees[i] = (int) graph[v].size();
        }

        ParallelUtils::parallelScan(degrees, degrees, size, tree);

        std::atomic_int sizeNextFront{0};

#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(size, sizeNextFront, BLOCK_FOR)
        for (int i = 0; i < size; i++) {
            int v = front0[i];

            for (int j = 0; j < graph[v].size(); j++) {
                int u = graph[v][j];
                int ind = (i == 0) ? j : degrees[i - 1] + j;

                int ex = 0;
                int to = dist[v].load() + 1;
                if (dist[u].compare_exchange_strong(ex, to)) {
                    front1[ind] = u;
                    int old = sizeNextFront.load();
                    int nev = ind + 1;
                    while (true) {
                        if (old < nev) {
                            if (sizeNextFront.compare_exchange_weak(old, nev)) break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        if (sizeNextFront == 0) {
            return;
        }

        int len = ParallelUtils::parallelFilter(
                front1,
                front0,
                sizeNextFront,
                [](int x) { return x >= 0; },
                mask,
                tree
        );

#pragma omp parallel for default(none) schedule(static, BLOCK_FOR) shared(sizeNextFront, BLOCK_FOR)
        for (int i = 0; i < sizeNextFront; i++) {
            front1[i] = -1;
        }

        size = len;
    }

}

//BENCHMARK:

double runBenchmark(const fVoidVoid_t &f, int iter) {
    auto t1 = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < iter; i++) {
        f();
    }
    auto t2 = std::chrono::high_resolution_clock::now();
    auto time = std::chrono::duration_cast<std::chrono::milliseconds>(t2 - t1);
    return (double) time.count() / (double) iter;
}

//TESTING:

struct Testing {
    struct BFS_ENTITY {
        vec_t front0;
        vec_t front1;
        vec_t degrees;
        vec_t mask;
        vec_t tree;

        vec_vec_t graph;

        std::string mode;

        SimpleBFS simpleBfs;
        ParallelBFS parallelBfs;

        explicit BFS_ENTITY(int maxSize, vec_vec_t &graph);
    };

    static double launch(int arg, const std::string& test, int iter);
};

Testing::BFS_ENTITY::BFS_ENTITY(int maxSize, vec_vec_t &graph) : graph(graph),
    front0(vec_t(maxSize)),
    front1(vec_t(maxSize)),
    degrees(vec_t(maxSize)),
    mask(vec_t(maxSize)),
    tree(vec_t(4 * maxSize)),
    simpleBfs(SimpleBFS(graph, 0)),
    parallelBfs(ParallelBFS(graph, 0, maxSize, front0, front1, degrees, mask, tree)
) {
    // nothing
}

double Testing::launch(int arg, const std::string& test, int iter) {
    int sideSize = arg;
    int maxSize = 500 * sideSize * sideSize;
    vec_vec_t graph = getCubicGraph(sideSize);
    BFS_ENTITY dto(maxSize, graph);
    double ans;
    if (test == "SIMPLE_BFS_TEST")
        ans = runBenchmark([&dto]() {
            dto.mode = "simple";
            dto.simpleBfs.run();
        }, iter);
    if (test == "PARALLEL_BFS_TEST")
        ans = runBenchmark([&dto]() {
            dto.mode = "parallel";
            dto.parallelBfs.run();
        }, iter);
    return ans;
}

int main() {
    omp_set_dynamic(0);
    omp_set_num_threads(4);

    std::cout << "test: SIMPLE_BFS_TEST; arg: " << 500 << "; ";
    std::cout << Testing::launch(500, "SIMPLE_BFS_TEST", ITERATIONS) << " ms\n";

    std::cout << "test: PARALLEL_BFS_TEST; arg: " << 500 << "; ";
    std::cout << Testing::launch(500, "PARALLEL_BFS_TEST", ITERATIONS) << " ms\n";

    return 0;
}
