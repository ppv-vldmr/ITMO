package lab.two.algorithm;

public abstract class Function {
    protected abstract void init(double[] startPoint, double eps);
    protected abstract double[] returnAns();

    protected final double func(final double ... args) {
        //return 10 * Math.pow(args[0], 2) + Math.pow(args[1], 2);
        return Math.pow(args[0], 2) / 15 + Math.pow(args[1], 2) / 2;
    }

    protected final double[] gradient(final double ... args) {
        final double d = 0.000000001;
        double[] ans = new double[args.length];
        for (int i = 0; i < ans.length; i++) {
            args[i] += d;
            ans[i] = func(args);
            args[i] -= d;
            ans[i] = (ans[i] - func(args)) / d;
        }
        return ans;
    }

    public double[] apply(double[] startPoint, double eps) {
        init(startPoint, eps);
        return returnAns();
    }
}
