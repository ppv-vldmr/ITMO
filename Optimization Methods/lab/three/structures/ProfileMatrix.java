package lab.three.structures;

import java.util.stream.IntStream;

public class ProfileMatrix {
    private final double[] d;
    private final double[] al;
    private final double[] au;
    private final int[] ia;
    private boolean convertedToLu = false;

    public ProfileMatrix(double[] d, double[] al, double[] au, int[] ia) {
        this.d = d;
        this.al = al;
        this.au = au;
        this.ia = ia;
    }

    public void convertToLU() {
        int n = d.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                setToL(i, j, getFromL(i, j) - multiplyReducedLU(j, i, j));
            }
            for (int j = 0; j < i; j++) {
                setToU(j, i, (getFromU(j, i) - multiplyReducedLU(j, j, i)) / getFromL(j, j));
            }
            setToL(i, i, getFromL(i, i) - multiplyReducedLU(i, i, i));
        }
        convertedToLu = true;
    }

    public boolean isConvertedToLu() {
        return convertedToLu;
    }

    public double getFromL(int i, int j) {
        if (i == j) {
            return d[i];
        }
        int index = getIndex(i, j);
        if (index < 0) {
            return 0;
        }
        return al[index];
    }

    public double getFromU(int i, int j) {
        if (i == j) {
            return 1;
        }
        int index = getIndex(j, i);
        if (index < 0) {
            return 0;
        }
        return au[index];
    }

    public void setToL(int i, int j, double value) {
        if (i == j) {
            d[i] = value;
            return;
        }
        int index = getIndex(i, j);
        if (index >= 0) {
            al[index] = value;
        }
    }

    private void setToU(int i, int j, double value) {
        if (i == j) {
            return;
        }
        int index = getIndex(j, i);
        if (index >= 0) {
            au[index] = value;
        }
    }

    public double multiplyReducedLU(final int range, final int i, final int j) {
        return IntStream.range(0, range).mapToDouble(k -> getFromL(i, k) * getFromU(k, j)).sum();
    }

    private int getIndex(int i, int j) {
        j -= (i - (ia[i + 1] - ia[i]));
        if (j < 0) {
            return -1;
        }
        return ia[i] - 1 + j;
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
    private double getFromA(int i, int j, boolean l) {
        j -= (i - (ia[i + 1] - ia[i]));
        if (j < 0) {
            return 0;
        }
        if (l) {
            return al[ia[i] - 1 + j];
        }
        return au[ia[i] - 1 + j];
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
}
