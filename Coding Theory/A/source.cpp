#include <vector>
#include <iostream>
#include <algorithm>
#include <chrono>
#include <random>

// @author: Danielto1404

#define c_boost std::ios_base::sync_with_stdio(false); std::cin.tie(nullptr)

struct Edge;
struct Layer;

const double NEGATIVE_INFINITY = -1000000;
using uint = unsigned int;
using Bit = unsigned char;
using BitMatrix = std::vector<std::vector<Bit>>;
using BitVector = std::vector<Bit>;
using UIntVector = std::vector<uint>;
using EdgeVector = std::vector<Edge>;
using SignalVector = std::vector<double>;
using Trellis = std::vector<Layer>;


struct Span {
    const int head;
    const int tail;
};

struct Edge {
    uint node;
    Bit bit;

    Edge &operator=(const Edge &other) = default;
};

namespace Bits {
    uint indexStateTransform(uint mask, uint active, bool toIndex) {
        /*
         * Base function which walks through active bits and either finds
         *      state from index or
         *      index from state
         */
        uint shift = 0;
        uint value = 0;
        while (active != 0) {
            bool isActive = active & 1u;
            if (isActive) {
                uint bit = mask & 1u;
                value |= (bit << shift);
                toIndex ? ++shift : mask >>= 1u;
            }
            toIndex ? mask >>= 1u : ++shift;
            active >>= 1u;
        }
        return value;
    }

    uint toIndex(uint state, uint active) {
        /*
         * state  - state of active bits
         * active - layer active rows bitmask
         *
         * converts state of length n to the index i according to the active bits length.
         * returns: index i in [0 .. 2 ^ len(active bits) - 1]
         *
         * Example for active = (1 0 1 0 1)
         *              state = (0 1 1 0 0)
         *              index = (0 _ 1 _ 0), i.e 2
         */
        return indexStateTransform(state, active, true);
    }

    uint toState(uint index, uint active) {
        /*
         * index  - state represented as an index
         * active - layer active rows bitmask
         *
         * converts index state to the original state according to the layer active bits.
         * returns: state of size n
         * Example for index = (_ 1 _ 0 1), i.e 5
         *            active = (0 1 0 1 1)
         *             state = (0 1 0 0 1), i.e 9
         */
        return indexStateTransform(index, active, false);
    }

    uint hammingWeight(const uint x) {
        /*
         * returns amount of nonzero bits.
         */
        return __builtin_popcount(x);
    }
}


class Layer {
public:
    explicit Layer(uint activeRowsBitmask = 0) {
        activeBits = activeRowsBitmask;
        edges = std::vector<EdgeVector>(complexity(), EdgeVector());
    }

    void connect(uint nextLayerActiveBits, uint column) {
        uint shared = activeBits | nextLayerActiveBits;
        uint states = 1u << Bits::hammingWeight(shared);

        for (uint s = 0; s < states; ++s) {
            uint transition = Bits::toState(s, shared);
            uint in = Bits::toIndex(transition, activeBits);
            uint to = Bits::toIndex(transition, nextLayerActiveBits);
            Bit bit = Bits::hammingWeight(transition & column) & 1u;
            edges[in].push_back(Edge{.node=to, .bit=bit});
        }
    }

    uint complexity() const {
        return 1u << Bits::hammingWeight(activeBits);
    }

    uint activeBits;
    std::vector<EdgeVector> edges;
};

class DummyLayer : public Layer {
};

struct SpanForm {
    BitMatrix matrix;
    UIntVector heads;
    UIntVector tails;
};

class ViterbiDecoder {
public:
    explicit ViterbiDecoder(const SpanForm &spanForm) {
        this->trellis = buildTrellis(spanForm);
    }

    BitVector decode(const SignalVector &signal) const {
        BitVector decoded = BitVector();

        std::vector<std::vector<double>> cost = std::vector<std::vector<double>>(trellis.size());
        cost.front() = std::vector<double>(trellis.front().complexity(), 0);

        std::vector<EdgeVector> path = std::vector<EdgeVector>(trellis.size());

        for (int i = 1; i < trellis.size(); ++i) {
            double noisyBit = signal[i - 1];
            cost[i] = std::vector<double>(trellis[i].complexity(), NEGATIVE_INFINITY);
            path[i] = EdgeVector(trellis[i].complexity());
            for (uint from = 0; from < trellis[i - 1].complexity(); ++from) {
                for (const Edge &e: trellis[i - 1].edges[from]) {
                    double weight = e.bit ? -noisyBit : noisyBit;
                    if (cost[i - 1][from] + weight > cost[i][e.node]) {
                        cost[i][e.node] = cost[i - 1][from] + weight;
                        path[i][e.node] = Edge{.node=from, .bit=e.bit};
                    }
                }
            }
        }
        uint state = 0;
        for (uint i = path.size() - 1; i != 0; --i) {
            uint predecessor = path[i][state].node;
            Bit bit = path[i][state].bit;
            decoded.push_back(bit);
            state = predecessor;
        }
        std::reverse(decoded.begin(), decoded.end());
        return decoded;
    }

    Trellis trellis;
private:
    static Trellis buildTrellis(const SpanForm &spanForm) {
        int k = spanForm.matrix.size();
        int n = spanForm.matrix[0].size();
        Trellis trellis = Trellis();
        trellis.push_back(DummyLayer());
        for (int j = 0; j < n; ++j) {
            uint active = 0;
            uint column = 0;
            for (int i = 0; i < k; ++i) {
                column |= (spanForm.matrix[i][j] << i);
                if (spanForm.heads[i] <= j && j < spanForm.tails[i] ||
                    spanForm.heads[i] == j && j == spanForm.tails[i]) {
                    active |= (1u << i);
                }
            }
            trellis.back().connect(active, column);
            Layer layer = Layer(active);
            trellis.push_back(layer);
        }
        return trellis;
    }
};

namespace Random {
    double nextGaussian(double mean, double sigma) {
        long seed = std::chrono::system_clock::now().time_since_epoch().count();
        static std::default_random_engine generator(seed);
        std::normal_distribution<double> normalDistribution(mean, sigma);
        return normalDistribution(generator);
    }

    Bit bit() {
        static std::random_device rd;
        static std::uniform_int_distribution<Bit> uniform(0, 1);
        return uniform(rd);
    }

    void fill(BitVector &vector) {
        for (Bit &bit :vector) { bit = Random::bit(); }
    }
}

namespace LinearBlockCode {
    /*
     * Linear Block Code utils
     */

    BitVector encode(const BitVector &vector, const BitMatrix &matrix) {
        int k = matrix.size();
        int n = matrix[0].size();
        BitVector multiplication = BitVector(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                multiplication[i] ^= vector[j] & matrix[j][i];
                multiplication[i] &= 1u;
            }
        }
        return multiplication;
    }

    void _modifySpan(Bit bit, int index, int &head, int &tail) {
        if (bit) {
            tail = index;
            if (head == -1) {
                head = index;
            }
        }
    }

    Span span(const BitVector &vector) {
        int head = -1, tail;
        for (int i = 0; i < vector.size(); i++) {
            _modifySpan(vector[i], i, head, tail);
        }
        return Span{.head=head, .tail=tail};
    }

    Span sumUpdateSpan(BitVector &to, const BitVector &add) {
        /*
         * Adds second row to the first and finds new `Span` of the first row.
         */
        int head = -1, tail;
        for (int i = 0; i < to.size(); ++i) {
            to[i] ^= add[i];
            to[i] &= 1;
            _modifySpan(to[i], i, head, tail);
        }
        return Span{.head=head, .tail=tail};
    }

    SpanForm toSpanForm(const BitMatrix &matrix) {
        /*
         * Converts matrix to the Minimal Span Form.
         *
         * Source: http://www.ece.ualberta.ca/~hcdc/Library/ErrorContrClass/Chap5new.pdf | page 11.
         * Algorithm:
         * Step 1: Find a pair of rows x(i) and x(j) in the generator matrix G,
         *      such that L(x(i)) = L(x(j)) and R(x(i)) ≤ R(x(j)), or R(x(i)) = R(x(j)) and L(x(i)) ≤ L(x(j)).
         * Step 2: If Step 1 fails and no such pair can be found go to Step 4.
         * Step 3: Let x(i) = x(i) + x(j), i.e., replace the row x(i) by the sum of the two rows. Go to Step 1.
         * Step 4: Output G, which is now a Minimal Span Form.
         */

        BitMatrix msf = matrix;
        int k = msf.size();

        UIntVector heads = UIntVector(k);
        UIntVector tails = UIntVector(k);

        for (int i = 0; i < k; ++i) {
            Span span = LinearBlockCode::span(msf[i]);
            heads[i] = span.head;
            tails[i] = span.tail;
        }

        while (true) {
            bool correct = true;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (i == j) continue;
                    if (heads[i] == heads[j] && tails[i] <= tails[j] ||
                        heads[i] <= heads[j] && tails[i] == tails[j]) {
                        Span span = LinearBlockCode::sumUpdateSpan(msf[i], msf[j]);
                        heads[i] = span.head;
                        tails[i] = span.tail;
                        correct = false;
                    }
                }
            }
            if (correct) {
                return SpanForm{.matrix=msf, .heads=heads, .tails=tails};
            }
        }
    }
}

namespace GaussianChannel {
    SignalVector sendSignal(const BitVector &vector, double noiseLevel, int n, int k) {
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
        SignalVector signal = SignalVector(vector.size());
        double decibel = pow(10, -noiseLevel / 10);
        double deviation = (0.5 * n / k) * decibel;
        double sigma = sqrt(deviation);

        for (int i = 0; i < signal.size(); ++i) {
            double bit = vector[i] ? -1.0 : 1.0;
            signal[i] = bit + Random::nextGaussian(0.0, sigma);
        }
        return signal;
    }

    double simulate(const ViterbiDecoder &decoder, const BitMatrix &matrix,
                    int k, int n, double noiseLevel, int maxNumberOfIterations, int maxErrors) {
        int mistakes = 0;
        int iterations = 0;
        BitVector message = BitVector(k);
        for (int i = 0; i < maxNumberOfIterations; ++i) {
            iterations += 1;
            Random::fill(message);
            BitVector encoded = LinearBlockCode::encode(message, matrix);
            SignalVector signal = GaussianChannel::sendSignal(encoded, noiseLevel, n, k);
            BitVector decoded = decoder.decode(signal);
            if (decoded != encoded) {
                mistakes += 1;
            }
            if (mistakes == maxErrors) {
                break;
            }
        }
        return double(mistakes) / iterations;
    }
}

namespace Utils {
    template<class T>
    std::string toString(const std::vector<T> &vector) {
        std::string output = std::string();
        for (const T &e: vector) {
            output += std::to_string(e) + " ";
        }
        output.pop_back();
        return output;
    }
}

int main() {
    c_boost;
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    uint n, k, maxNumberOfIterations, maxErrors;
    double noiseLevel;
    std::string command;

    std::cin >> n >> k;

    BitMatrix matrix = BitMatrix(k, BitVector(n));
    BitVector input = BitVector(k);
    SignalVector signal = SignalVector(n);

    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < n; ++j) {
            std::cin >> matrix[i][j];
        }
    }

    SpanForm spanForm = LinearBlockCode::toSpanForm(matrix);
    ViterbiDecoder decoder = ViterbiDecoder(spanForm);

    UIntVector complexity = UIntVector();
    for (const Layer &layer : decoder.trellis) {
        complexity.push_back(layer.complexity());
    }
    std::cout << Utils::toString(complexity);

    while (std::cin >> command) {
        std::cout << '\n';
        if (command == "Encode") {
            for (Bit &x: input)
                std::cin >> x;
            BitVector encoded = LinearBlockCode::encode(input, matrix);
            std::cout << Utils::toString(encoded);
        }
        if (command == "Decode") {
            for (double &x: signal)
                std::cin >> x;
            BitVector decoded = decoder.decode(signal);
            std::cout << Utils::toString(decoded);
        }
        if (command == "Simulate") {
            std::cin >> noiseLevel >> maxNumberOfIterations >> maxErrors;
            double errorsFrequency = GaussianChannel::simulate(
                    decoder, matrix, k, n, noiseLevel, maxNumberOfIterations, maxErrors
            );
            std::cout << errorsFrequency;
        }
    }
}