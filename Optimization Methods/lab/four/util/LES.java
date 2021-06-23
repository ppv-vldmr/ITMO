package lab.four.util;

public class LES {
    private final double[][] coeffs;
    private final double[] vals;
    private final int n;

    public LES(double[][] coeffs, double[] vals) {
        this.coeffs = coeffs;
        this.vals = vals;
        this.n = coeffs.length;
    }

    public double[] solve() {
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double t = coeffs[i][k] / coeffs[k][k];
                vals[i] = vals[i] - t * vals[k];
                for (int j = k + 1; j < n; j++) {
                    coeffs[i][j] = coeffs[i][j] - t * coeffs[k][j];
                }
            }
        }
        double[] x = new double[n];
        x[n - 1] = vals[n - 1] / coeffs[n - 1][n - 1];
        for (int k = n - 2; k >= 0; k--) {
            double sum = 0;
            for (int j = k + 1; j < n; j++) {
                sum += x[j] * coeffs[k][j];
            }
            x[k] = (vals[k] - sum) / coeffs[k][k];
        }
        return x;
    }
}
