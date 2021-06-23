package lab.four.util;

public class RosenbrockUtil {
    private RosenbrockUtil() {}

    public static final DoubleMultiFunction function = (x) -> {
        double sum = 0;
        for (int i = 0; i < x.length - 2; i++) {
            sum += 100 * (x[i + 1] - x[i] * x[i]) + (1 - x[i]) * (1 - x[i]);
        }
        return sum;
    };

    public static double[] generateInput(int dim, boolean random) {
        double[] x = new double[dim];
        if (random) {
            for (int i = 0; i < dim; i++) {
                x[i] = Math.random();
            }
        }
        return x;
    }

    public static double[] generateInput(int dim) {
        return generateInput(dim, false);
    }

    public static double[] generateInput() {
        return generateInput(100, false);
    }

}
