package lab.four.util;

import java.util.Arrays;

public class LinearUtils {
    private LinearUtils() {}

    public static double[] mul(double[] s, double x) {
        double[] res = Arrays.copyOf(s, s.length);
        for (int i = 0; i < s.length; i++) {
            res[i] *= x;
        }
        return res;
    }

    public static double[][] mul(double[][] m, double x) {
        double[][] res = new double[m.length][];
        for (int i = 0; i < m.length; i++) {
            res[i] = mul(m[i], x);
        }
        return res;
    }

    public static double[] sum(double[] a, double[] b) {
        double[] res = Arrays.copyOf(a, a.length);
        for (int i = 0; i < a.length; i++) {
            res[i] += b[i];
        }
        return res;
    }

    public static double[][] sum(double[][] a, double[][] b) {
        double[][] res = new double[a.length][0];
        for (int i = 0; i < a.length; i++) {
            res[i] = sum(a[i], b[i]);
        }
        return res;
    }

    public static double[] mulMatrixVector(double[][] a, double[] s) {
        double[] ans = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
               ans[i] += s[j] * a[i][j];
            }
        }
        return ans;
    }

    public static double[][] mulMatrixMatrix(double[][] a, double[][] b) {
        double[][] ans = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    public static double[] negate(double[] a) {
        double[] b = Arrays.copyOf(a, a.length);
        for (int i = 0; i < b.length; i++) {
            b[i] *= -1;
        }
        return b;
    }

    public static double[] sub(double[] a, double[] b) {
        double[] res = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[i] - b[i];
        }
        return res;
    }

    public static double[][] sub(double[][] a, double[][] b) {
        double[][] res = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            res[i] = sub(a[i], b[i]);
        }
        return res;
    }

    public static double norm(double[] s) {
        double sum = 0;
        for (double c : s) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public static double[][] transpose(double[][] m) {
        double[][] t = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                t[i][j] = m[j][i];
            }
        }
        return t;
    }

    public static double scalar(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public static double[][] I(int n) {
        double[][] m = new double[n][n];
        for (int i = 0; i < n; i++) {
            m[i][i] = 1;
        }
        return m;
    }

    public static double[][] I(int n, double x) {
        double[][] m = new double[n][n];
        for (int i = 0; i < n; i++) {
            m[i][i] = x;
        }
        return m;
    }

    public static double[][] wrap(double[] a) {
        return new double[][]{Arrays.copyOf(a, a.length)};
    }

    public static double[][] wrapEach(double[] a) {
        double[][] ans = new double[a.length][1];
        for (int i = 0; i < a.length; i++) {
            ans[i][0] = a[i];
        }
        return ans;
    }

    public static boolean equal(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
