package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

public class GoldenRatio extends Algorithm {

    private static final double SQRT_FIVE = Math.sqrt(5);
    private static final double K = (3 - SQRT_FIVE) / 2;

    public GoldenRatio(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    public GoldenRatio(DoubleFunction<Double> func) {
        this(func, PRECISENESS);
    }

    private double x1, x2, f1, f2;
    private static final DoubleBinaryOperator x1Form = (x, y) -> x + K * (y - x);
    private static final DoubleBinaryOperator x2Form = (x, y) -> y - K * (y - x);

    @Override
    protected Segment step(Segment segment) {
        Segment updatedSegment;

        if (f1 <= f2) {
            double updatedFrom = segment.from();
            double updatedTo = x2;
            updatedSegment = new Segment(updatedFrom, updatedTo);

            x2 = x1;
            f2 = f1;
            x1 = updatedSegment.computeX(x1Form);
            f1 = func.apply(x1);
            segment.computedF(f1);
            return updatedSegment;
        }

        double updatedFrom = x1;
        double updatedTo = segment.to();

        x1 = x2;
        f1 = f2;
        updatedSegment = new Segment(updatedFrom, updatedTo);
        x2 = updatedSegment.computeX(x2Form);
        f2 = func.apply(x2);
        segment.computedF(f2);
        return updatedSegment;

    }

    @Override
    protected boolean done(Segment segment) {
        return K * segment.length() <= eps;
    }

    @Override
    protected void init(Segment segment) {
        x1 = segment.computeX(x1Form);
        x2 = segment.computeX(x2Form);
        f1 = func.apply(x1);
        f2 = func.apply(x2);
    }
}
