package lab.two.algorithm;

import java.util.Arrays;

public class ConjugateGradients extends Function {
    private static double[] startPoint;
    private static double eps;

    @Override
    protected void init(double[] startPoint, double eps) {
        ConjugateGradients.startPoint = startPoint;
        ConjugateGradients.eps = eps;
    }

    private double GoldenRatio(double eps, double[] b, double ... a) {
        double left = 0;
        double right = 1e5;
        double x0 = left + 0.5 * (3 - Math.sqrt(5)) * (right - left);
        double x1 = right - x0 + left;

        while (Math.abs(right - left) > eps) {
            double xL = a[0] + x0 * b[0];
            double yL = a[1] + x0 * b[1];

            double xR = a[0] + x1 * b[0];
            double yR = a[1] + x1 * b[1];

            if (func(xL, yL) < func(xR, yR)) {
                right = x1;
            } else {
                left = x0;
            }
            x1 = x0;
            x0 = right + left - x1;
        }
        return (left + right) / 2;
    }

    private double innerproduct(double[] a, double[] b) {
        double ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += a[i] * b[i];
        } return ans;
    }

    private double[] calculateConjugateGradients(double eps, double ... args) {
        boolean stop = false;
        int iter = 0;

        double[] p = gradient(args);
        for (int i = 0; i < p.length; i++) p[i] = -p[i];
        double[] grad = Arrays.copyOf(p, p.length);

        while (!stop) {
            iter += 1;

            double alpha = GoldenRatio(eps, p, args);
            for (int i = 0; i < args.length; i++) {
                args[i] += alpha * p[i];
            }

            double[] grad1 = gradient(args);
            for (int i = 0; i < grad1.length; i++) grad1[i] = -grad1[i];

            double beta = iter % 2 == 1 ? innerproduct(grad1, grad1) / innerproduct(grad, grad) : 0;
            for (int i = 0; i < p.length; i++) {
                p[i] = grad1[i] + beta * p[i];
            }

            grad = Arrays.copyOf(grad1, grad1.length);
            if (innerproduct(grad, grad) <= eps) {
                stop = true;
            }
        }

        double[] ans = new double[args.length + 1];
        System.arraycopy(args, 0, ans, 0, args.length);
        ans[ans.length - 1] = iter;
        return ans;
    }

    @Override
    public double[] returnAns() {
        return calculateConjugateGradients(eps, startPoint);
    }
}
