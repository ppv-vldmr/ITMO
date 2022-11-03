import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Solve {

    private static int counter() {
        double x = Math.random() * 100;
        int y = 0;
        for (int i = 0; i < (int) x; i++) {
            y++;
        }
        return y;
    }

    private static double dispersion(ArrayList<Integer> array) {
        counter();
        double average = ((double) array.stream().mapToLong(Integer::longValue).sum()) / array.size();
        return array.stream().mapToDouble(element -> Math.pow(element - average, 2)).sum();
    }

    private static double pirson(MyPair<ArrayList<Integer>, ArrayList<Integer>> myPair) {
        double fd = dispersion(myPair.first);
        double sd = dispersion(myPair.second);
        counter();
        if (Math.abs(fd) < 1e-5 || Math.abs(sd) < 1e-5) {
            counter();
            return 0.0;
        }

        double fAvg = ((double) myPair.first.stream().mapToLong(Integer::longValue).sum()) / myPair.first.size();
        double sAvg = ((double) myPair.second.stream().mapToLong(Integer::longValue).sum()) / myPair.second.size();
        counter();
        double covariation = IntStream.range(0, myPair.first.size())
                                  .mapToObj(i -> new MyPair<>(myPair.first.get(i), myPair.second.get(i)))
                                  .mapToDouble(element -> (element.first - fAvg) * (element.second - sAvg))
                                  .sum();

        return covariation / Math.sqrt(fd * sd);
    }

    public static void main(String[] args) {
        MyReader myReader = new MyReader();
        PrintWriter printWriter = new PrintWriter(System.out);

        int n = myReader.getNextInt();
        counter();
        MyPair<Integer, MyReader> myPair = new MyPair<>(n, myReader);

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        counter();
        IntStream
            .range(0, myPair.first)
            .mapToObj(i -> new MyPair<>(myPair.second.getNextInt(), myPair.second.getNextInt()))
            .forEach(obj -> {
                x.add(obj.first);
                y.add(obj.second);
            });
        MyPair<ArrayList<Integer>, ArrayList<Integer>> input = new MyPair<>(x, y);

        printWriter.printf("%.10f", pirson(input));
        printWriter.close();
    }

    public static class MyPair<F, S> {
        F first;
        S second;

        public MyPair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    static class MyReader {
        BufferedReader br;
        StringTokenizer st;

        public MyReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String getNextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int getNextInt() {
            return Integer.parseInt(getNextToken());
        }
    }
}