package lab.three.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MatrixUtils {
    public static double[] copy(double[] other){
        return Arrays.copyOf(other, other.length);
    }
    public static int[] copy(int[] other){
        return Arrays.copyOf(other, other.length);
    }
    public static void printM(double[][] a) {
        for (double[] doubles : a) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(double[][] A, double[] B) {
        int N = B.length;
        System.out.println("MATRIX :");
        IntStream.range(0, N).forEach(i -> {
            Arrays.stream(A[i]).forEach(aj -> System.out.print(aj + " "));
            System.out.println("   " + B[i]);
        });
        System.out.println();
    }
    public static void printSolution(double[] X) {
        System.out.println("SOLUTION :");
        Arrays.stream(X).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static double[] add(double[] x, double[] y) {
        int N = x.length;
        double[] ans = new double[N];
        for (int i = 0; i < N; i++) {
            ans[i] = x[i] + y[i];
        }
        return ans;
    }

    public static double[] subtract(double[] x, double[] y) {
        int N = x.length;
        double[] ans = new double[N];
        for (int i = 0; i < N; i++) {
            ans[i] = x[i] - y[i];
        }
        return ans;
    }

    public static double[] multiply(double[] x, double k) {
        int N = x.length;
        double[] ans = new double[N];
        for (int i = 0; i < N; i++) {
            ans[i] = x[i] * k;
        }
        return ans;
    }

    @SuppressWarnings("all")
    public static double module(double[] x) {
        return Math.sqrt(scalar(x, x));
    }

    public static double[] getAx(double[][] A, double[] x) {
        int N = x.length;
        double[] ans = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i] += A[i][j] * x[j];
            }
        }
        return ans;
    }

    public static double scalar(double[] x, double[] y) {
        int N = x.length;
        double ans = 0;
        for (int i = 0; i < N; i++) {
            ans += x[i] * y[i];
        }
        return ans;
    }
}
