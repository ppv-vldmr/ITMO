#include<iostream>
#include <vector>
#include <iomanip>
#include <map>

#define BASE 1000000000

using namespace std;

class big_integer {
private:
    int sign;
    static const int base = BASE;
    vector<long long> digits;
    static const int digits_width = 9;

    void trim() {
        while(!digits.empty() && ( digits.back()== 0)) {
            digits.pop_back();
        }
        if(digits.empty()) {
            sign = 1;
            digits.push_back(0);
        }
    }

public:
    big_integer() {
        sign = 1;
    }

    big_integer(const int &value) {
        *this = value;
    }

    big_integer(const long long &value) {
        *this = value;
    }

    big_integer(const string &value) {
        digits.clear();
        sign = 1;
        int pos = 0;
        while (pos < value.size() && (value[pos] == '-' || value[pos] == '+')) {
            if (value[pos] == '-') {
                sign *= -1;
            }
            ++pos;
        }
        for (int i = value.size() - 1; i >= pos; i -= digits_width) {
            int x = 0;
            for (int j = max(pos, i - digits_width + 1); j <= i; j++) {
                x *= 10;
                x += value[j] -'0';
            }
            digits.push_back(x);
        }
        trim();
    }

    void operator = (const big_integer &x) {
        sign = x.sign;
        digits = x.digits;
    }

    void operator = (const int value) {
        int x = value;
        if (x < 0) {
            sign = -1;
            x *= -1;
        } else {
            sign = 1;
        }
        while (x) {
            digits.push_back(x % base);
            x /= base;
        }
    }

    void operator = (const long long value) {
        long long x = value;
        if (x < 0) {
            sign = -1;
            x *= -1;
        } else {
            sign = 1;
        }
        while (x) {
            digits.push_back(x % base);
            x /= base;
        }
    }

    big_integer operator - () const {
        big_integer x = *this;
        x.sign = -sign;
        return (x);
    }

    bool operator > (const big_integer &value) const {
        if (sign != value.sign) {
            return sign > value.sign;
        }
        if (digits.size() != value.digits.size()) {
            return digits.size() > value.digits.size();
        }
        int max_size = digits.size();
        for (int i = max_size - 1; i >= 0; --i) {
            if(digits[i] != value.digits[i]) {
                return digits[i] > value.digits[i];
            }
        }
        return false;
    }

    bool operator < (const big_integer &value) const {
        return value > *this;
    }

    bool operator <= (const big_integer &value) const {
        return !(*this > value);
    }

    bool operator >= (const big_integer &value) const {
        return !(*this < value);
    }

    bool operator == (const big_integer &value) const {
        return !((*this < value) && (*this > value));
    }

    bool operator != (const big_integer &value) const {
        return !(*this == value);
    }

    big_integer abs() const {
        big_integer ans = *this;
        ans.sign = 1;
        return ans;
    }

    big_integer operator + (const big_integer &value) {
        if(value.sign * sign == -1) {
            return (*this) - (-value);
        } else {
            big_integer ans = value;
            int max_size = max(digits.size(), value.digits.size()), carry = 0;
            for (int i = 0; i < max_size || carry != 0; i++) {
                if(ans.digits.size() == i) {
                    ans.digits.push_back(0);
                }
                ans.digits[i] += carry + ((i < digits.size()) ? digits[i] : 0);
                if (ans.digits[i] < base) {
                    carry = 0;
                } else {
                    ans.digits[i] -= base;
                    carry = 1;
                }
            }
            return ans;
        }
    }

    void operator += (const big_integer &value) {
        *this = *this + value;
    }

    big_integer operator - (const big_integer &value) const {
        if (sign * value.sign == - 1) {
            return (-value) + *this;
        }
        if (abs() >= value.abs()) {
            big_integer ans = *this;
            int max_size = value.digits.size(), carry = 0;
            for (int i = 0; (i < max_size) || (carry != 0); ++i) {
                ans.digits[i] -= (carry + ((i < max_size) ? value.digits[i] : 0));
                if (ans.digits[i] < 0) {
                    ans.digits[i] += base;
                    carry = 1;
                }
                else carry = 0;
            }
            ans.trim();
            return ans;
        } else {
            return -(value - *this);
        }
    }

    void operator -= (const big_integer &value){
        *this = *this - value;
    }

    big_integer operator * (const int &value) const {
        int x = value;
        big_integer ans = *this;
        if (x < 0) {
            ans.sign *= -1;
            x = -x;
        }
        int carry = 0;
        for(int i = 0; (i < (int) ans.digits.size()) || (carry != 0); ++i) {
            if(i == ans.digits.size()) {
                ans.digits.push_back(0);
            }
            long long mul = ans.digits[i] * 1LL * x + carry;
            ans.digits[i] = (int) (mul % base);
            carry = (int) (mul / base);
        }
        ans.trim();
        return ans;
    }

    void operator *= (const int &value) {
        *this = *this * value;
    }

    big_integer operator * (const big_integer &value) const {
        big_integer a = *this;
        big_integer ans = 0;
        int p = 0;
        for (int i = 0; i < value.digits.size(); i++) {
            int temp = value.digits[i];
            big_integer temp_res = a * temp;
            for (int j = 0; j < p; j++) {
                temp_res = temp_res * BASE;
            }
            ans += temp_res;
            p++;
        }
        if (value.sign == -1) {
            ans = ans * -1;
        }
        return ans;
    }

    void operator *= (const big_integer &value) {
        *this = *this * value;
    }

    friend pair<big_integer, big_integer> big_integer_division_mode(const big_integer &x, const big_integer &y) {
        int base1 = BASE;
        int factor = base1 / y.digits.back() + 1;
        big_integer a = x * factor;
        big_integer b = y * factor;
        big_integer qut, rem;
        qut.digits.resize(a.digits.size());
        int size_dsr = b.digits.size();
        for(int i = a.digits.size() - 1; i >= 0; --i) {
            rem *= base1;
            rem += a.digits[i];
            int c1 = rem.digits.size() <= size_dsr ? 0 : rem.digits[size_dsr];
            int c2 = rem.digits.size() <= size_dsr - 1 ? 0 : rem.digits[size_dsr-1];
            int tmp = (base1 * 1LL * c1 + c2) / b.digits.back();
            rem -= b * tmp;
            while (rem < 0) {
                rem += b;
                --tmp;
            }
            qut.digits[i] = tmp;
        }
        if (x.sign == y.sign) {
            qut.sign = 1;
        } else {
            qut.sign = -1;
        }
        rem.sign = x.sign;
        qut.trim();
        rem.trim();
        return make_pair(qut, rem / factor);
    }

    big_integer operator % (const big_integer &value) const {
        return big_integer_division_mode(*this, value).second;
    }

    void operator %= (const big_integer &value) {
        *this = *this % value;
    }

    big_integer operator / (const big_integer &value) const {
        return big_integer_division_mode(*this, value).first;
    }

    void operator /= (const big_integer &value) {
        *this = *this / value;
    }

    friend pair<big_integer, int> int_division_mode(const big_integer &d, const int &value) {
        big_integer y = d;
        int x = value;
        if(x < 0) {
            y.sign *= -1;
            x = -x;
        }
        int max_size = y.digits.size(), remainder = 0;
        long long cur;
        for(int i = max_size - 1 ; i >= 0 ; --i) {
            cur = remainder * 1LL * base + y.digits[i];
            y.digits[i] = (int) (cur / x);
            remainder = (int) (cur % x);
        }
        y.trim();
        return make_pair(y , remainder);
    }

    big_integer operator / (const int &value) {
        return int_division_mode(*this, value).first;
    }

    void operator /= (const int &value) {
        *this = *this / value;
    }

    int operator % (const int &value) {
        int x = value;
        if(x < 0) {
            x *= -1;
        }
        int rem = 0;
        int max_size = digits.size();
        for(int i = max_size - 1; i >= 0; --i) {
            rem = (digits[i] + rem * 1LL * base) % x;
        }
        return rem * sign;
    }

    void operator %= (const int &value) {
        *this = *this % value;
    }

    friend big_integer sqrt(big_integer &value) {
        big_integer l = 0, r = value, mid;
        while (r >= l) {
            mid = (l + r) / 2;
            if (mid * mid <= value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    friend ostream& operator << (ostream &stream, const big_integer &value) {
        if (value.sign == -1) {
            stream << '-';
        }
        stream << (value.digits.empty() ? 0 : value.digits.back());
        int max_size = value.digits.size();
        for (int i = max_size - 2; i >= 0; --i)
            stream << setw(digits_width) << setfill('0') << value.digits[i];
        return stream;
    }

    friend istream& operator >> (istream &stream, big_integer &value) {
        string number;
        stream >> number;
        value = big_integer(number);
        return stream;
    }
};

int main(int argc, char** argv) {
    if (argc != 3) {
        printf("Wrong count of program arguments.\nExpected: 3, found: %d\n", argc);
        return 1;
    }
    FILE *input = freopen(argv[1], "r", stdin);
    if (input == NULL) {
        printf("An error has occurred with input file %s\n", argv[1]);
        return 1;
    }
    map<string, int> operations {
            {"+", 1},
            {"-", 2},
            {"*", 3},
            {"/", 4},
            {"%", 5},
            {"#", 6},
            {"<", 7},
            {">", 8},
            {"<=", 9},
            {">=", 10},
            {"!=", 11},
            {"==", 12}
    };
    big_integer a, b;
    string op;
    cin >> a >> op;
    FILE *output = freopen(argv[2], "w", stdout);
    if (output == NULL) {
        printf("An error has occurred with output file ", argv[2]);
        fclose(input);
        return 1;
    }
    switch (operations[op]) {
        case 1: {
            cin >> b;
            cout << a + b;
            break;
        }

        case 2: {
            cin >> b;
            cout << a - b;
            break;
        }

        case 3: {
            cin >> b;
            cout << a * b;
            break;
        }

        case 4: {
            cin >> b;
            if (b == 0) {
                cout << "NaN";
            } else {
                cout << a / b;
            }
            break;
        }

        case 5: {
            cin >> b;
            if (b == 0) {
                cout << "NaN";
            } else {
                cout << a % b;
            }
            break;
        }

        case 6: {
            if (a < 0) {
                cout << "NaN";
            } else {
                cout << sqrt(a);
            }
            break;
        }

        case 7: {
            cin >> b;
            bool res = a < b;
            cout << res;
            break;
        }

        case 8: {
            cin >> b;
            bool res = a > b;
            cout << res;
            break;
        }

        case 9: {
            cin >> b;
            bool res = a <= b;
            cout << res;
            break;
        }

        case 10: {
            cin >> b;
            bool res = a >= b;
            cout << res;
            break;
        }

        case 11: {
            cin >> b;
            bool res = a != b;
            cout << res;
            break;
        }

        case 12: {
            cin >> b;
            bool res = a == b;
            cout << res;
            break;
        }

        default: {
            cout << "Wrong operation entered.";
            fclose(input);
            fclose(output);
            return 1;
        }
    }
    fclose(input);
    fclose(output);
    return 0;
}