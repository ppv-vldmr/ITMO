package lab.four.algorithm.newton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.LinearUtils;
import lab.one.algorithm.Dichotomy;
import lab.one.util.Segment;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public class UnarySearchNewton extends ClassyNewton {
    protected List<Double> alphas = new ArrayList<>();

    public UnarySearchNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    @Override
    protected void updateXInner() {
        DoubleFunction<Double> func = (a) -> function.apply(LinearUtils.sum(x, LinearUtils.mul(p, a)));
        double alpha = new Dichotomy(func).apply(new Segment(-100, 100));
        alphas.add(alpha);
        double[] res = LinearUtils.mul(p, alpha);
        for (int i = 0; i < x.length; i++) {
            x[i] += res[i];
        }
    }

    public List<Double> getAlphas() {
        return alphas;
    }

}
