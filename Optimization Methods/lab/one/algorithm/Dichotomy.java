package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleFunction;

public class Dichotomy extends Algorithm {
    private final double delta;

    public Dichotomy(DoubleFunction<Double> func, double eps) {
        super(func, eps);
        this.delta = eps / 4;
    }

    public Dichotomy(DoubleFunction<Double> func) {
        this(func, PRECISENESS);
    }

    @Override
    protected Segment step(Segment segment) {
        double x1 = segment.computeX((x, y) -> (x + y - this.delta) / 2);
        double x2 = segment.computeX((x, y) -> (x + y + this.delta) / 2);

        double f1 = func.apply(x1);
        double f2 = func.apply(x2);
        segment.computedF(x1);
        segment.computedF(x2);

        if (f1 <= f2) {
            return new Segment(segment.from(), x2);
        }
        return new Segment(x1, segment.to());
    }

    @Override
    protected boolean done(Segment segment) {
        return segment.length() / 2 <= this.eps;
    }

    @Override
    protected double getMinX(Segment segment) {
        return (segment.from() + segment.to()) / 2;
    }

    @Override
    protected void init(Segment segment) { }
}
