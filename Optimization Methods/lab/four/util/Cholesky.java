package lab.four.util;

public class Cholesky {
    private final double[][] a;
    private final int n;

    public Cholesky(double[][] a) {
        this.a = a;
        this.n = a.length;
    }

    public double[][] decompose() {
        double[][] res = new double[n][n];

        res[0][0] = Math.sqrt(a[0][0]);

        for (int j = 1; j < n; j++) {
            res[j][0] = a[j][0] / res[0][0];
        }

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int p = 0; p < i - 1; p++) {
                sum += res[i][p] * res[i][p];
            }
            res[i][i] = Math.sqrt(a[i][i] - sum);
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                double sum = 0;
                if (j == i) {
                    for (int p = 0; p < i - 1; p++) {
                        sum += res[i][p] * res[i][p];
                    }
                    res[i][i] = Math.sqrt(a[i][i] - sum);
                    continue;
                }
                for (int p = 0; p < i; p++) {
                    sum += res[i][p] * res[j][p];
                }
                res[j][i] = (a[j][i] - sum) / res[i][i];
            }
        }
        return res;
    }

}
