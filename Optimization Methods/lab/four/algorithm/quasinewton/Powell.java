package lab.four.algorithm.quasinewton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.FunctionUtils;
import lab.four.util.LinearUtils;
import lab.one.algorithm.Dichotomy;
import lab.one.util.Segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleFunction;

public class Powell extends DFP {
    protected double alpha;
    protected double[] deltaXDot;
    protected final List<Double> alphas = new ArrayList<>();

    public Powell(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    protected void updateAlpha() {
        DoubleFunction<Double> func = (a) -> function.apply(LinearUtils.sum(x, LinearUtils.mul(p, a)));
        alpha = new Dichotomy(func).apply(new Segment(-1000, 1000));
        alphas.add(alpha);
    }

    @Override
    protected void firstStep() {
        w = LinearUtils.negate(FunctionUtils.gradient(function, x));
        p = Arrays.copyOf(w, w.length);
        deltaW = Arrays.copyOf(w, w.length);
        updateAlpha();
        updateX();
    }

    protected void updateXInner() {
        double[] prevX = Arrays.copyOf(x, x.length);
        x = LinearUtils.sum(x, LinearUtils.mul(p, alpha));
        deltaX = LinearUtils.sub(x, prevX);
        deltaXDot = LinearUtils.sum(deltaX, LinearUtils.mulMatrixVector(gMatrix, deltaW));
    }

    @Override
    protected void step() {
        double[] prevW = Arrays.copyOf(w, w.length);
        w = LinearUtils.negate(FunctionUtils.gradient(function, x));
        deltaW = LinearUtils.sub(w, prevW);
        double[] v = LinearUtils.mulMatrixVector(gMatrix, deltaW);

        updateAlpha();
        updateX();
        updateGMatrix(v);

        p = LinearUtils.mulMatrixVector(gMatrix, w);
    }

    @Override
    protected void updateGMatrix(double[] v) {
        double[][] s = LinearUtils.mul(
                LinearUtils.mulMatrixMatrix(
                        LinearUtils.wrapEach(deltaXDot), LinearUtils.wrap(deltaXDot)),
                1 / LinearUtils.scalar(deltaW, deltaXDot));
        gMatrix = LinearUtils.sub(gMatrix, s);
    }
}
