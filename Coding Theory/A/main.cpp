#include <iostream>
#include <vector>
#include <algorithm>
#include <chrono>
#include <random>

using namespace std;

struct BitsUtils {
    //Function which walks through active bits and either finds state from index or index from state
    static unsigned int index_state_transform(unsigned int mask, unsigned int active, bool to_index) {
        unsigned int shift = 0;
        unsigned int value = 0;
        while (active != 0) {
            if (active & 1u) {
                value |= ((mask & 1u) << shift);
                if (!to_index) {
                    mask >>= 1u;
                } else {
                    shift++;
                }
            }
            active >>= 1u;
            if (to_index) {
                mask >>= 1u;
            } else {
                shift++;
            }
        }
        return value;
    }

    //Function which converts state of length n to the index i according to the active bits' length
    //Where:
    //  state - state of active bits
    //  active - layer active rows bitmask
    //Returns: index i in [0 .. 2 ^ len(active bits) - 1]
    /*
     * Example for active = (1 0 1 0 1)
     *              state = (0 1 1 0 0)
     *              index = (0 _ 1 _ 0), i.e 2
     */
    static unsigned int to_index(unsigned int state, unsigned int active) {
        return index_state_transform(state, active, true);
    }

    //Function which converts index state to the original state according to the layer active bits.
    //Where:
    //  index - state represented as an index
    //  active - layer active rows bitmask
    //Returns: state of size n
    /*
     * Example for index = (_ 1 _ 0 1), i.e 5
     *            active = (0 1 0 1 1)
     *             state = (0 1 0 0 1), i.e 9
     */
    static unsigned int to_state(unsigned int index, unsigned int active) {
        return index_state_transform(index, active, false);
    }

    //Function which finds amount of nonzero bits
    static unsigned int hammingWeight(const unsigned int x) {
        return __builtin_popcount(x);
    }
};

struct Randomizer {
    static double nextGaussian(double mean, double sigma) {
        long seed = chrono::system_clock::now().time_since_epoch().count();
        static default_random_engine generator(seed);
        normal_distribution<double> normalDistribution(mean, sigma);
        return normalDistribution(generator);
    }

    static unsigned char bit() {
        static random_device rd;
        static uniform_int_distribution<unsigned char> uniform(0, 1);
        return uniform(rd);
    }

    static void fill(vector<unsigned char> &vector) {
        for (unsigned char &bit :vector) {
            bit = Randomizer::bit();
        }
    }
};

struct Edge {
    unsigned int node;
    unsigned char bit;
};

//Struct which describes every layer of grid.
struct Layer {
    unsigned int activeBits;
    vector<vector<Edge>> edges;

    explicit Layer(unsigned int activeRowsBitmask = 0) {
        activeBits = activeRowsBitmask;
        edges = vector<vector<Edge>>(complexity(), vector<Edge>());
    }

    void connect(unsigned int nextLayerActiveBits, unsigned int column) {
        unsigned int shared = activeBits | nextLayerActiveBits;
        unsigned int states = 1u << BitsUtils::hammingWeight(shared);

        for (unsigned int s = 0; s < states; ++s) {
            unsigned int transition = BitsUtils::to_state(s, shared);
            unsigned int in = BitsUtils::to_index(transition, activeBits);
            unsigned int to = BitsUtils::to_index(transition, nextLayerActiveBits);
            unsigned char bit = BitsUtils::hammingWeight(transition & column) & 1u;
            edges[in].push_back(Edge{.node=to, .bit=bit});
        }
    }

    [[nodiscard]] unsigned int complexity() const {
        return 1u << BitsUtils::hammingWeight(activeBits);
    }
};

struct SpanForm {
    vector<vector<unsigned char>> matrix;
    vector<unsigned int> heads;
    vector<unsigned int> tails;
};

struct Decoder {
    vector<Layer> grid;

    explicit Decoder(const SpanForm &spanForm) {
        this->grid = buildGrid(spanForm);
    }

    [[nodiscard]] vector<unsigned char> decode(const vector<double> &signal) const {
        vector<unsigned char> decoded = vector<unsigned char>();

        vector<vector<double>> cost = vector<vector<double>>(grid.size());
        cost.front() = vector<double>(grid.front().complexity(), 0);

        vector<vector<Edge>> path = vector<vector<Edge>>(grid.size());

        for (int i = 1; i < grid.size(); ++i) {
            cost[i] = vector<double>(grid[i].complexity(), -1000000); //like negative infinity
            path[i] = vector<Edge>(grid[i].complexity());
            for (unsigned int from = 0; from < grid[i - 1].complexity(); ++from) {
                for (int q = 0; q < grid[i - 1].edges[from].size(); q++) {
                    Edge e = grid[i - 1].edges[from][q];
                    double weight;
                    if (e.bit) {
                        weight = -signal[i - 1];
                    } else {
                        weight = signal[i - 1];
                    }
                    if (cost[i][e.node] < cost[i - 1][from] + weight) {
                        Edge updatedEdge = Edge();
                        updatedEdge.node = from;
                        updatedEdge.bit = e.bit;
                        path[i][e.node] = updatedEdge;
                        cost[i][e.node] = cost[i - 1][from] + weight;
                    }
                }
            }
        }
        unsigned int state = 0;
        for (unsigned int i = path.size() - 1; i != 0; --i) {
            unsigned int predecessor = path[i][state].node;
            unsigned char bit = path[i][state].bit;
            decoded.push_back(bit);
            state = predecessor;
        }
        reverse(decoded.begin(), decoded.end());
        return decoded;
    }

    static vector<Layer> buildGrid(const SpanForm &spanForm) {
        int k = spanForm.matrix.size();
        int n = spanForm.matrix[0].size();
        vector<Layer> grid = vector<Layer>();
        grid.emplace_back(); //add empty layer
        for (int j = 0; j < n; ++j) {

            unsigned int column = 0;
            for (int i = 0; i < k; ++i) {
                column |= (spanForm.matrix[i][j] << i);
            }

            unsigned int active = 0;
            for (int i = 0; i < k; ++i) {
                bool isActive1 = spanForm.heads[i] <= j && j < spanForm.tails[i];
                bool isActive2 = spanForm.tails[i] == j && spanForm.heads[i] == j;
                if (isActive1 || isActive2) {
                    active |= (1u << i);
                }
            }

            grid.back().connect(active, column);
            grid.emplace_back(active);
        }
        return grid;
    }
};

struct SpanPair {
    int head;
    int tail;
};

//Utils for work with Linear Block Code
struct LinearBlockCodeUtils {

    static vector<unsigned char> encode(
            const vector<unsigned char> &vector,
            const std::vector<std::vector<unsigned char>> &matrix
    ) {
        std::vector<unsigned char> multiplication = std::vector<unsigned char>(matrix[0].size());

        for (int i = 0; i < matrix[0].size(); ++i) {
            for (int j = 0; j < matrix.size(); ++j) {
                multiplication[i] = (multiplication[i] ^ (vector[j] & matrix[j][i])) & 1u;
            }
        }
        return multiplication;
    }

    static void _modifySpan(unsigned char bit, int index, int &head, int &tail) {
        if (bit) {
            tail = index;
            if (head == -1) {
                head = index;
            }
        }
    }

    //Function which converts matrix to the Minimal SpanPair Form.
    //
    //Source: http://www.ece.ualberta.ca/~hcdc/Library/ErrorContrClass/Chap5new.pdf | page 11.
    //Algorithm:
    //Step 1: Find a pair of rows x(i) and x(j) in the generator matrix G,
    //     such that L(x(i)) = L(x(j)) and R(x(i)) ≤ R(x(j)), or R(x(i)) = R(x(j)) and L(x(i)) ≤ L(x(j)).
    //Step 2: If Step 1 fails and no such pair can be found go to Step 4.
    //Step 3: Let x(i) = x(i) + x(j), i.e., replace the row x(i) by the sum of the two rows. Go to Step 1.
    //Step 4: Output G, which is now a Minimal SpanPair Form.
    static SpanForm toMSF(const vector<vector<unsigned char>> &matrix) {
        vector<vector<unsigned char>> msf = matrix;
        int k = msf.size();

        vector<unsigned int> heads = vector<unsigned int>(k);
        vector<unsigned int> tails = vector<unsigned int>(k);

        int q = 0;
        while (q < k) {
            int head = -1;
            int tail;
            int q1 = 0;
            while (q1 < msf[q].size()) {
                _modifySpan(msf[q][q1], q1, head, tail);
                q1++;
            }
            heads[q] = head;
            tails[q] = tail;
            q++;
        }

        while (true) {
            bool correct = true;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (i != j) {
                        bool heads_equals = heads[i] == heads[j] && tails[i] <= tails[j];
                        bool tails_equals = heads[i] <= heads[j] && tails[i] == tails[j];
                        if (heads_equals || tails_equals) {
                            correct = false;

                            // Add second row to the first and find new `SpanPair` of the first row
                            int head = -1, tail;
                            for (int ind = 0; ind < msf[i].size(); ++ind) {
                                msf[i][ind] = (msf[i][ind] ^ msf[j][ind]) & 1;
                                _modifySpan(msf[i][ind], ind, head, tail);
                            }

                            heads[i] = head;
                            tails[i] = tail;
                        }
                    }
                }
            }
            if (correct) {
                SpanForm form = SpanForm();
                form.matrix = msf;
                form.tails = tails;
                form.heads = heads;
                return form;
            }
        }
    }
};

struct GaussianChannel {
    static vector<double> sendSignal(const vector<unsigned char> &vector, double noiseLevel, int n, int k) {
        /*
         * Es         - signal power = a ^ 2, a = 1
         * sigma ^ 2  - noise power = N0 / 2
         * Es / N0    - ratio signal to noise per symbol
         * Eb / N0    - ratio signal to noise per bit = Es / (N0 * R)
         * R          - information bits per symbol = k / n
         * noiseLevel - decibel ratio = 10 * log10 ( Eb / N0 )
         *
         * Gaussian noise ~ N(0, sigma ^ 2)
         *
         * -- How to find sigma ^ 2 --
         *
         * noiseLevel            = 10 * log10 ( Eb / N0 )
         * log10( Eb / N0 )      = noiseLevel / 10
         * log10 (Es / (R * N0)) = noiseLevel / 10
         * log10 (1 / (R * N0)   = noiseLevel / 10
         * 1 / (R * NO)          = 10 ^ (noiseLevel / 10)
         * 1 / ((k / n) * N0)    = 10 ^ (noiseLevel / 10)
         * 1 / N0                = 10 ^ (noiseLevel / 10) * (k / n)
         * N0                    = 10 ^ (-noiseLevel / 10) * (n / k)
         * sigma ^ 2             = N0 / 2
         * sigma ^ 2             = 1 / 2 * 10 ^ (-noiseLevel / 10) * (n / k)
         *
         */
        std::vector<double> signal = std::vector<double>(vector.size());

        for (int i = 0; i < signal.size(); ++i) {
            signal[i] = Randomizer::nextGaussian(
                    0.0,
                    sqrt((0.5 * n / k) * pow(10, -noiseLevel / 10)) //from zero to calculated sigma
            );
            if (vector[i]) {
                signal[i] -= 1;
            } else {
                signal[i] += 1;
            }
        }
        return signal;
    }

    static double simulate(
            const Decoder &decoder,
            const vector<vector<unsigned char>> &matrix,
            int k,
            int n,
            double noiseLevel,
            int maxNumberOfIterations,
            int maxErrors
    ) {
        int mistakes = 0;
        int iterations = 0;
        vector<unsigned char> message = vector<unsigned char>(k);
        for (int i = 0; i < maxNumberOfIterations; ++i) {
            iterations += 1;
            Randomizer::fill(message);
            vector<unsigned char> encoded = LinearBlockCodeUtils::encode(message, matrix);
            vector<double> signal = GaussianChannel::sendSignal(encoded, noiseLevel, n, k);
            vector<unsigned char> decoded = decoder.decode(signal);
            if (decoded != encoded) {
                mistakes += 1;
            }
            if (mistakes == maxErrors) {
                break;
            }
        }
        return double(mistakes) / iterations;
    }
};

string print_vector_unsigned_char(const vector<unsigned char> &vector) {
    string output = string();
    for (const unsigned char &e: vector) {
        output += to_string(e) + " ";
    }
    output.pop_back();
    return output;
}

string print_vector_unsigned_int(const vector<unsigned int> &vector) {
    string output = string();
    for (const unsigned int &e: vector) {
        output += to_string(e) + " ";
    }
    output.pop_back();
    return output;
}

void speed_up() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
}

void connect_to_files() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
}

void runEncode(vector<unsigned char> &input, vector<vector<unsigned char>> &matrix) {
    for (unsigned char &x: input) {
        cin >> x;
    }
    vector<unsigned char> encoded = LinearBlockCodeUtils::encode(input, matrix);
    cout << print_vector_unsigned_char(encoded);
}

void runDecode(vector<double> &signal, Decoder &decoder) {
    for (double &x: signal) {
        cin >> x;
    }
    vector<unsigned char> decoded = decoder.decode(signal);
    cout << print_vector_unsigned_char(decoded);
}

int main() {
    speed_up();
    connect_to_files();

    //Common parameters
    unsigned int n, k;

    //Parameters for "Simulate" command
    unsigned int maxNumberOfIterations, maxErrors;
    double noiseLevel;

    string command;

    cin >> n >> k;

    vector<unsigned char> input = vector<unsigned char>(k);
    vector<double> signal = vector<double>(n);

    //Read matrix values
    vector<vector<unsigned char>> matrix = vector<vector<unsigned char>>(k, vector<unsigned char>(n));
    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> matrix[i][j];
        }
    }

    SpanForm spanForm = LinearBlockCodeUtils::toMSF(matrix);
    Decoder decoder = Decoder(spanForm);

    vector<unsigned int> complexity = vector<unsigned int>();
    for (const Layer &layer : decoder.grid) {
        complexity.push_back(layer.complexity());
    }
    cout << print_vector_unsigned_int(complexity);

    while (cin >> command) {
        cout << '\n';
        if (command == "Encode") {
            runEncode(input, matrix);
        }
        if (command == "Decode") {
            runDecode(signal, decoder);
        }
        if (command == "Simulate") {
            cin >> noiseLevel >> maxNumberOfIterations >> maxErrors;
            double errorsFrequency = GaussianChannel::simulate(
                    decoder, matrix, k, n, noiseLevel, maxNumberOfIterations, maxErrors
            );
            cout << errorsFrequency;
        }
    }
}