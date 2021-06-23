package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

public class Parabolic extends Algorithm {

    public Parabolic(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    public Parabolic(DoubleFunction<Double> func) {
        super(func, PRECISENESS);
    }

    private double x1, x2, x3, f1, f2, f3;

    private DoubleBinaryOperator xForm = (x, y) -> x2 - 0.5 * (Math.pow((x2 - x1), 2)
            * (f2 - f3) - Math.pow((x2 - x3), 2) * (f2 - f1)) / (
            (x2 - x1) * (f2 - f3) - (x2 - x3) * (f2 - f1));

    @Override
    protected Segment step(Segment segment) {
        double x = segment.computeX(xForm);
        double f = func.apply(x);
        segment.computedF(f);

        if (x < x2) {
            if (f < f2) {
              x3 = x2;
              f3 = f2;
              x2 = x;
              f2 = f;
            } else {
                x1 = x;
                f1 = f;
            }
        } else {
            if (f < f2) {
                x1 = x2;
                f1 = f2;
                x2 = x;
                f2 = f;
            } else {
                x3 = x;
                f3 = f;
            }
        }
        return new Segment(x1, x3);
    }

    @Override
    protected void init(Segment segment) {
        x1 = segment.from();
        x3 = segment.to();
        x2 = (x1 + x2) / 2;
        f1 = func.apply(x1);
        f2 = func.apply(x2);
        f3 = func.apply(x3);
    }
}
