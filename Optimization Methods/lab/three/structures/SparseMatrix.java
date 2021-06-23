package lab.three.structures;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrix {
    private final double[] d;
    private final double[] al;
    private final double[] au;
    private final int[] ia;
    private final int[] ja;

    public SparseMatrix(double[] d, double[] al, double[] au, int[] ia, int[] ja) {
        this.d = d;
        this.al = al;
        this.au = au;
        this.ia = ia;
        this.ja = ja;
    }

    public double[] getAx(double[] x) {
        int N = x.length;
        double[] ans = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i] += get(i, j) * x[j];
            }
        }
        return ans;
    }

    public double get(int i, int j) {
        if (i == j) {
            return d[i];
        }
        if (j < i) {
            return getFromA(i, j, true);
        }
        return getFromA(j, i, false);
    }

    public double getFromA(int i, int j, boolean l) {
        List<Integer> columns = new ArrayList<>();
        for (int k = ia[i]; k < ia[i] + (ia[i + 1] - ia[i]); k++) {
            columns.add(ja[k]);
        }
        if (columns.contains(j)) {
            if (l) {
                return al[ia[i] + columns.indexOf(j) - 1];
            } else {
                return au[ia[i] + columns.indexOf(j) - 1];
            }
        } else {
            return 0;
        }
    }

    public double[][] convertToNormalMatrix() {
        int n = d.length;
        double[][] res = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = this.get(i, j);
            }
        }
        return res;
    }

    public double[] getD() {
        return d;
    }

    public double[] getAl() {
        return al;
    }

    public double[] getAu() {
        return au;
    }

    public int[] getIa() {
        return ia;
    }

    public int[] getJa() { return ja; }
}
