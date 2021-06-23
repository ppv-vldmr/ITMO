package lab.four.util;

import java.util.Arrays;

public class FunctionUtils {
    private FunctionUtils() {}
    private static final double GRADIENT_STEP = 0.00001;

    public static double[] gradient(DoubleMultiFunction function, double[] vals) {
        double[] g = new double[vals.length];
        double[] plus = Arrays.copyOf(vals, vals.length);
        double[] minus = Arrays.copyOf(vals, vals.length);

        for (int i = 0; i < vals.length; i++) {
            plus[i] += GRADIENT_STEP;
            minus[i] -= GRADIENT_STEP;
            g[i] = (function.apply(plus) - function.apply(minus)) / (2 * GRADIENT_STEP);
            plus[i] -= GRADIENT_STEP;
            minus[i] += GRADIENT_STEP;
        }
        return g;
    }

    public static double[][] hessian(DoubleMultiFunction function, double[] x) {
        double[] vals = Arrays.copyOf(x, x.length);

        double[][] hesse = new double[vals.length][vals.length];

        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals.length; j++) {
                vals[i] += GRADIENT_STEP;
                vals[j] += GRADIENT_STEP;
                double x0 = function.apply(vals); // f(x + h, y + h)
                vals[j] -= 2 * GRADIENT_STEP;
                double x1 = function.apply(vals); // f(x + h, y - h)
                vals[i] -= 2 * GRADIENT_STEP;
                double x2 = function.apply(vals); // f(x - h, y - h)
                vals[j] += 2 * GRADIENT_STEP;
                double x3 = function.apply(vals); // f(x - h, y + h);
                vals[j] -= GRADIENT_STEP;
                vals[i] += GRADIENT_STEP;
                hesse[i][j] = (x0 - x1 + x2 - x3) / (4 * GRADIENT_STEP * GRADIENT_STEP);
            }
        }
        return hesse;
    }
}
