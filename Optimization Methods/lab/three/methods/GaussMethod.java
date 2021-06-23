package lab.three.methods;

public class GaussMethod {
    private static final double EPS = 10E-16;

    public static double[] solve(double[][] A, double[] B) {
        int N = A.length;
        //MatrixUtil.printMatrix(A, B);
        for (int k = 0; k < N; k++) {
            int max = getMax(A, k, N);

            swap(A, B, k, max);

            for (int i = k + 1; i < N; i++) {
                double t = A[i][k] / A[k][k];
                B[i] -= t * B[k];
                for (int j = k; j < N; j++) {
                    A[i][j] -= t * A[k][j];
                }
            }
        }

        double[] X = new double[N];
        for (int k = N - 1; k >= 0; k--) {
            double sum = 0;
            for (int j = k + 1; j < N; j++) {
                sum += A[k][j] * X[j];
            }
            X[k] = (B[k] - sum) / A[k][k];
        }
        return X;
    }

    public static int getMax(double[][] A, int k, int N) {
        int max = k;
        for (int i = k + 1; i < N; i++) {
            if (Math.abs(A[i][k]) > Math.abs(A[max][k])) {
                max = i;
            }
        }
        return max;
    }

    public static void swap(double[][] A, double[] B, int k, int max) {
        double[] temp = A[k];
        A[k] = A[max];
        A[max] = temp;

        double t = B[k];
        B[k] = B[max];
        B[max] = t;
    }
}