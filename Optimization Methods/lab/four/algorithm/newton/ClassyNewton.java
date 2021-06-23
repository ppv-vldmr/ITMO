package lab.four.algorithm.newton;

import lab.four.util.*;
import java.util.ArrayList;
import java.util.List;

public class ClassyNewton extends MultiOptimizationMethod {
    protected double[] grad;
    protected double[] p;
    protected List<double[]> computedX = new ArrayList<>();

    public ClassyNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    @Override
    protected void updateXInner() {
        for (int i = 0; i < p.length; i++) {
            x[i] += p[i];
        }
    }

    @Override
    protected boolean done() {
        return LinearUtils.norm(p) < EPS;
    }

    @Override
    protected void firstStep() {}

    @Override
    protected void step() {
        grad = FunctionUtils.gradient(function, x);
        double[][] h = FunctionUtils.hessian(function, x);
        p = new LES(h, LinearUtils.negate(grad)).solve();
        updateX();
        computedX.add(x);
    }

}
