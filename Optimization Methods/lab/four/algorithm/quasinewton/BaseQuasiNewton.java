package lab.four.algorithm.quasinewton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.LinearUtils;
import lab.four.util.MultiOptimizationMethod;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuasiNewton extends MultiOptimizationMethod {
    protected double[] deltaX;
    protected double[] p;
    protected double[] w;
    protected double[] deltaW;
    protected double[][] gMatrix;

    public BaseQuasiNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
        this.gMatrix = LinearUtils.I(x.length);
    }

    @Override
    protected boolean done() {
        return LinearUtils.norm(deltaX) < EPS;
    }

}
