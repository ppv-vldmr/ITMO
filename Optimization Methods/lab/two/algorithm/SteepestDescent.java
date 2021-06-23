package lab.two.algorithm;

import java.util.Arrays;

public class SteepestDescent extends Function {
    private static double[] startPoint;
    private static double eps;

    private double[] GoldenRatio(double eps, double[] a, double[] b) {
        double t1 = 0.381966, t2 = 1 - t1;

        double[] x1 = new double[]{
                a[0] + (b[0] - a[0]) * t1,
                a[1] + (b[1] - a[1]) * t1
        };
        double[] x2 = new double[]{
                a[0] + (b[0] - a[0]) * t2,
                a[1] + (b[1] - a[1]) * t2
        };

        double f1 = func(x1[0] - eps, x1[1] - eps);
        double f2 = func(x2[0] + eps, x2[1] + eps);

        while (Math.sqrt((b[0] - a[0]) * (b[0] - a[0]) + (b[1] - a[1]) * (b[1] - a[1])) > eps) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1[0] = a[0] + (b[0] - a[0]) * t1;
                x1[1] = a[1] + (b[1] - a[1]) * t1;
                f1 = func(x1[0], x1[1]);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2[0] = a[0] + (b[0] - a[0]) * t2;
                x2[1] = a[1] + (b[1] - a[1]) * t2;
                f2 = func(x2[0], x2[1]);
            }
        }
        return new double[]{(a[0] + b[0]) / 2, (a[1] + b[1]) / 2};
    }

    private double[] calculateSteepestDescent(double eps, double ... args) {
        boolean stop = false;
        int iter = 0;
        double[] args0 = Arrays.copyOf(args, args.length);

        while (!stop) {
            double[] grad = gradient(args0);
            double[] minusGrad = Arrays.copyOf(grad, grad.length);
            for (int i = 0; i < minusGrad.length; i++) {
                minusGrad[i] = -minusGrad[i];
            }
            double[] point = GoldenRatio(eps, args0, minusGrad);
            double[] args1 = Arrays.copyOf(point, point.length);
            double dist = 0;
            for (int i = 0; i < args0.length; i++) {
                dist += Math.pow((args1[i] - args0[i]), 2);
            }

            if (dist < eps * eps && Math.abs(func(args0) - func(args1)) < eps) {
                stop = true;
            }

            args0 = Arrays.copyOf(args1, args1.length);
            iter += 1;
        }
        double[] ans = new double[args0.length + 1];
        System.arraycopy(args0, 0, ans, 0, args0.length);
        ans[ans.length - 1] = iter;
        return ans;
    }


    @Override
    protected void init(double[] startPoint, double eps) {
        SteepestDescent.startPoint = startPoint;
        SteepestDescent.eps = eps;
    }

    @Override
    protected double[] returnAns() {
        return calculateSteepestDescent(eps, startPoint);
    }
}
