package lab.one.util;

import lab.one.algorithm.*;

import java.util.List;
import java.util.function.DoubleFunction;

public class Task {
    public static final DoubleFunction<Double> FUNC =
            x -> -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * x * x - 2 * x + 1;
    private static final double FROM = -0.5;
    private static final double TO = 0.5;

    private final Algorithm algorithm;

    public Task(AlgoName algoName) {
        switch (algoName) {
            case BRENT:
                this.algorithm = new Brent(FUNC);
                break;
            case FIBONACCI:
                this.algorithm = new Fibonacci(FUNC);
                break;
            case DICHOTOMY:
                this.algorithm = new Dichotomy(FUNC);
                break;
            case PARABOLIC:
                this.algorithm = new Parabolic(FUNC);
                break;
            case GOLDEN_RATIO:
                this.algorithm = new GoldenRatio(FUNC);
                break;
            default:
                throw new AssertionError();
        }
    }

    public double f(double x) {
        return FUNC.apply(x);
    }

    public double runAlgorithm() {
        return algorithm.apply(new Segment(FROM, TO));
    }

    public List<Segment> getStats() {
        return algorithm.getSegments();
    }

}
