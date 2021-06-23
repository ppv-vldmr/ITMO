package lab.four.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MultiOptimizationMethod {
    public static final double EPS = 0.000001;

    protected final DoubleMultiFunction function;
    protected double[] x;

    protected final int n;
    protected int iteration = 0;

    protected List<double[]> computedXs = new ArrayList<>();

    protected abstract void step();
    protected abstract void firstStep();
    protected abstract boolean done();
    protected abstract void updateXInner();

    protected void updateX() {
        updateXInner();
        computedXs.add(x);
    }

    public double[] optimize() {
        firstStep();
        do {
            step();
            iteration++;
            System.out.println("\\hline");
            System.out.println(iteration + " & " + Arrays.toString(computedXs.get(computedXs.size() - 1)) + " \\\\");
        } while (!done());
        System.out.println("\\hline");
        return x;
    }

    public MultiOptimizationMethod(DoubleMultiFunction function, double[] x) {
        this.function = function;
        this.x = Arrays.copyOf(x, x.length);
        this.n = x.length;
    }

    public int getIterations() {
        return iteration;
    }
}
