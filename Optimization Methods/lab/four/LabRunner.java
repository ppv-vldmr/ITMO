package lab.four;

import lab.four.algorithm.newton.ClassyNewton;
import lab.four.algorithm.newton.DescentDirectionNewton;
import lab.four.algorithm.newton.UnarySearchNewton;
import lab.four.algorithm.quasinewton.DFP;
import lab.four.algorithm.quasinewton.Powell;
import lab.four.marquardt.Marquardt;
import lab.four.marquardt.ModifiedMarquardt;
import lab.four.util.DoubleMultiFunction;
import lab.four.util.RosenbrockUtil;

import java.util.Arrays;

public class LabRunner {
    static final DoubleMultiFunction f1 = (x) -> x[0] * x[0] + x[1] * x[1] - 1.2 * x[1] * x[0];
    static final double[] x1 = new double[]{4, 1};

    static final DoubleMultiFunction f2 = (x) -> 100 * Math.pow((x[1] - x[0] * x[0]), 2) + Math.pow((1 - x[0] * x[0]), 2);
    static final double[] x2 = new double[]{-1.2, 1};

    private static void testClassyNewton(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing Newton====");
        ClassyNewton newton = new ClassyNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static void testUnarySearchNewton(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing USN====");
        UnarySearchNewton newton = new UnarySearchNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static void testDescentDirectionNewton(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing DDN====");
        DescentDirectionNewton newton = new DescentDirectionNewton(f, x);
        double[] ans = newton.optimize();
        System.out.println(Arrays.toString(ans));
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + newton.getIterations());
    }

    private static DoubleMultiFunction f4 = (x) ->
            Math.pow(x[0] * x[0] + x[1] - 11, 2) + Math.pow(x[0] + x[1] * x[1] - 7, 2);
    private static DoubleMultiFunction f5 = (x) -> Math.pow(x[0] + 10 * x[1], 2) + 5 * Math.pow(x[2] - x[3], 2)
            + Math.pow(x[1] - 2 * x[2], 4) + 10 * Math.pow(x[0] - x[3], 4);
    private static DoubleMultiFunction f6 = (x) -> 100 - 2 / (1 + Math.pow((x[0] - 1) / 2, 2) + Math.pow((x[1] - 2) / 2, 2))
            - 2 / (1 + Math.pow((x[0] - 2) / 2, 2) + Math.pow((x[1] - 1) / 3, 2));



    private static void testDFP(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing DFP====");
        DFP dfp = new DFP(f, x);
        double[] ans = dfp.optimize();
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println(Arrays.toString(ans));
        System.out.println("Num iterations: " + dfp.getIterations());
    }

    private static void testPowell(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing Powell====");
        Powell p = new Powell(f, x);
        double[] ans = p.optimize();
        System.out.println("Ans: " + Arrays.toString(ans));
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + p.getIterations());
        System.out.println("Preciseness: " + p.EPS);
        System.out.println();
    }

    private static void testMarquardt(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing Marquardt====");
        Marquardt m = new Marquardt(f, x, 1);
        double[] ans = m.optimize();
        System.out.println("Ans: " + Arrays.toString(ans));
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println("\\hline");
//            System.out.println(i + 1 + " & " + round(ans[i], 4) + " \\\\");
//        }
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + m.getIterations());
        System.out.println("Preciseness: " + m.EPS);
        System.out.println("Taus: " + m.getTauValues());
        System.out.println();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static void testModifiedMarquardt(DoubleMultiFunction f, double[] x) {
        System.out.println("=====Testing Modified Marquardt====");
        ModifiedMarquardt m = new ModifiedMarquardt(f, x);
        double[] ans = m.optimize();
        System.out.println("Ans: " + Arrays.toString(ans));
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println("\\hline");
//            System.out.println(i + " & " + round(ans[i], 4) + " \\\\");
//        }
        System.out.println("f(x*): " + f.apply(ans));
        System.out.println("Num iterations: " + m.getIterations());
        System.out.println("Preciseness: " + m.EPS);
        System.out.println("Taus: " + m.getTauValues());
        System.out.println("Cholesky: " + m.getCholeskyApproximations());
        System.out.println();
    }

    private static DoubleMultiFunction f11 = (x) -> x[0] * x[0] + x[1] * x[1] + x[0];
    private static double[] x110 = new double[]{1.2, 5};
    private static double[] x111 = new double[]{100, 805.6};
    private static double[] x112 = new double[]{-103, -856};

    private static DoubleMultiFunction f12 = (x) -> 25 / 8 * Math.pow(x[0], 2)
            + Math.pow(x[0], 4) + 3 * Math.pow(x[1], 4) + x[1] + 14 * x[0];
    private static double[] x120 = new double[]{0.1, -0.15};
    private static double[] x121 = new double[]{-1.8, -0.8};
    private static double[] x122 = new double[]{-108, 8};

    public static void main(String[] args) {
        testClassyNewton(f1, x1);
        testUnarySearchNewton(f1, x1);
        testDescentDirectionNewton(f1, x1);
    }
}
