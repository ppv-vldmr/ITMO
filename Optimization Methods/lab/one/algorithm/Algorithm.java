package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

public abstract class Algorithm {

    protected static final double PRECISENESS = 0.0001;
    protected int numUpdates;
    protected final double eps;
    protected final List<Segment> segments;
    protected final DoubleFunction<Double> func;

    protected abstract Segment step(Segment segment);
    protected abstract void init(Segment segment);

    protected double getMinX(Segment segment) {
        return (segment.from() + segment.to()) / 2;
    }

    protected boolean done(Segment segment) {
        return segment.length() <= eps || numUpdates >= 100;
    }

    public double apply(Segment segment) {
        Segment currentSegment = segment;
        init(segment);
        while (!done(currentSegment)) {
            Segment newSegment = step(currentSegment);
            segments.add(currentSegment);
            currentSegment = newSegment;
            numUpdates++;
        }
        return getMinX(currentSegment);
    }

    public Algorithm(DoubleFunction<Double> func, double eps) {
        this.eps = eps;
        this.numUpdates = 0;
        this.func = func;
        segments = new ArrayList<>();
    }

    public Algorithm(DoubleFunction<Double> func) {
        this(func, PRECISENESS);
    }

    public String getStats() {
        return String.format("Num updates:%d\nSegments:\n%s", numUpdates, segments.toString());
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public int getNumUpdates() {
        return numUpdates;
    }

}
