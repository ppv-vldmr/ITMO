import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solve {

    private static int foo() {
        double x = Math.random() * 100;
        int y = 0;
        for (int i = 0; i < (int) x; i++) {
            y++;
        }
        return y;
    }

    public static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    private static Pair<Integer, Integer> readPair(FastReader fastReader) {
        return new Pair<>(fastReader.nextInt(), fastReader.nextInt());
    }

    private static ArrayList<Pair<Integer, Integer>> readArrayList(Pair<Integer, FastReader> pair) {
        ArrayList<Pair<Integer, Integer>> returned = new ArrayList<>();
        foo();
        IntStream.range(0, pair.first).mapToObj(i -> readPair(pair.second)).forEach(returned::add);
        return returned;
    }

    private static ArrayList<Pair<Double, Double>> expect(Pair<Integer, ArrayList<Pair<Integer, Integer>>> complication) {
        int k = complication.first;
        foo();
        ArrayList<Pair<Integer, Integer>> array = complication.second;
        ArrayList<Pair<Double, Double>> answer = IntStream.range(0, k)
                                                     .mapToObj(i -> new Pair<>(0.0, 0.0))
                                                     .collect(Collectors.toCollection(ArrayList::new));
        array.forEach(pair -> {
            foo();
            Pair<Double, Double> h = answer.get(pair.first - 1);
            h.first += ((double) pair.second) / array.size();
            h.second += 1.0 / array.size();
        });
        return answer;
    }

    public static void main(String[] args) {
        foo();
        FastReader fastReader = new FastReader();
        PrintWriter printWriter = new PrintWriter(System.out);

        int k = fastReader.nextInt();
        int n = fastReader.nextInt();
        foo();
        ArrayList<Pair<Integer, Integer>> array = readArrayList(new Pair<>(n, fastReader));
        foo();
        double dispersion = array.stream().mapToDouble(pair -> Math.pow(pair.second, 2) / array.size()).sum();
        double conditional = expect(new Pair<>(k, array)).stream().mapToDouble(pair -> {
            foo();
            return pair.second == 0 ? 0 : Math.pow(pair.first, 2) / pair.second;
        }).sum();
        printWriter.printf("%.10f", dispersion - conditional);
        printWriter.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
