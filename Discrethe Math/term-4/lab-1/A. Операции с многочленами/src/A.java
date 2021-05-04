import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A {
    private static final int MOD = 998_244_353;

    private static List<Integer> dopolnenie(List<Integer> p, List<Integer> q) {
        if (p.size() < q.size()) {
            return q.subList(p.size(), q.size());
        } else {
            return p.subList(q.size(), p.size());
        }
    }

    private static void output(List<? extends Number> a) {
        int zero = a.size() - 1;
        while (zero != 0 && a.get(zero).longValue() == 0) {
            zero--;
        }
        System.out.println(zero);
        if (zero == 0) {
            System.out.println(0);
        } else {
            for (int i = 0; i <= zero; i++) {
                System.out.printf("%d ", a.get(i).longValue());
            }
        }
        System.out.println();
    }

    private static void sum(List<Integer> p, List<Integer> q, List<Integer> dop) {
        List<Integer> sum = Stream.concat(IntStream.range(0, Math.min(p.size(), q.size()))
                .mapToObj(i -> ((p.get(i) + q.get(i)) % MOD)), dop.stream())
                .collect(Collectors.toList());
        output(sum);
    }

    private static void multiply(List<Integer> p, List<Integer> q) {
        List<Long> multiply = new ArrayList<>();
        for (int i = 0; i < p.size() + q.size() - 1; i++) {
            long res = 0;
            for (int j = 0; j <= i; j++) {
                long temp1 = 0;
                if (i - j < q.size()) {
                    temp1 = q.get(i - j);
                }
                long temp2 = 0;
                if (j < p.size()) {
                    temp2 = p.get(j);
                }
                res = (res + (temp2 * temp1) % MOD) % MOD;
            }
            if (res < 0) {
                res += MOD;
            }
            multiply.add(res);
        }
        output(multiply);
    }

    private static void divide(List<Integer> p, List<Integer> q) {
        List<Long> divide = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            long res = 0;
            for (int j = 0; j < i; j++) {
                long temp1 = 0;
                if (j < divide.size()) {
                    temp1 = divide.get(j);
                }
                long temp2 = 0;
                if (i - j < q.size()) {
                    temp2 = q.get(i - j);
                }
                res = (res + temp1 * temp2) % MOD;
            }
            long temp = 0;
            if (i < p.size()) {
                temp = p.get(i);
            }
            divide.add((temp - res) % MOD);
        }
        for (long i : divide) {
            if (i < 0) {
                System.out.printf("%d ", i + MOD);
            } else {
                System.out.printf("%d ", i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        List<Integer> p = Arrays.stream(
                reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> q = Arrays.stream(
                reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> dop = dopolnenie(p, q);
        sum(p, q, dop);
        multiply(p, q);
        divide(p, q);
    }
}
