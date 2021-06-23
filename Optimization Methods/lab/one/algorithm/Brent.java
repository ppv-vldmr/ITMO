package lab.one.algorithm;

import lab.one.util.Segment;
import java.util.function.DoubleFunction;

public class Brent extends Algorithm {
    private static final double COEFF = (3 - Math.sqrt(5)) / 2;

    private boolean differ(double a, double b, double c) {
        return a != b && a != c && b != c;
    }

    private boolean explicitlyDone = false;
    private double a, c, x, w, v, fx, fw, fv, d, e, g, u, fu;

    @Override
    protected Segment step(Segment segment) {
        g = e;
        e = d;
        double t = e * Math.abs(x) + eps / 10;
        if (Math.abs(x - (a + c) / 2) + (c - a) / 2 <= 2 * t) {
            explicitlyDone = true;
            return segment;
        }
        if (differ(x, w, v) && differ(fx, fw, fv)) {
            u = segment.computeX((x1, x2) -> parabola(x, w, v, fx, fw, fv));
            if (u >= a && u <= c && Math.abs(u - x) < g / 2) {
                if ((u - a) < 2 * t || (c - u) < 2 * t) {
                    u = segment.computeX((x1, x2) -> x - t * Math.signum(x - (x1 + x2) / 2));
                }
            }
        } else {
            if (x < (a + c) / 2) {
                u = segment.computeX((x1, x2) -> x + COEFF * (x2 - x));
                e = c - x;
            } else {
                u = segment.computeX((x1, x2) -> x - COEFF * (x - x1));
                e = x - a;
            }
        }
        if (Math.abs(u - x) < t) {
            u = x + t * Math.signum(u - x);
        }
        d = Math.abs(u - x);
        fu = func.apply(u);
        segment.computedF(fu);
        if (fu <= fx) {
            if (u >= x) {
                a = x;
            } else {
                c = x;
            }
            v = w;
            w = x;
            x = u;
            fv = fw;
            fw = fx;
            fx = fu;
        } else {
            if (u >= x) {
                c = u;
            } else {
                a = u;
            }
            if (fu <= fw || w == x) {
                v = w;
                w = u;
                fv = fw;
                fw = fu;
            } else if (fu <= fv || v == x || v == w) {
                v = u;
                fv = fu;
            }
        }
        return new Segment(a, c);
    }

    @Override
    protected void init(Segment segment) {
        a = segment.from();
        c = segment.to();
        x = w = v = a + COEFF * segment.length();
        fx = fw = fv = func.apply(x);
        d = e = c - a;
    }

    public Brent(DoubleFunction<Double> func, double eps) {
        super(func, eps);
    }

    public Brent(DoubleFunction<Double> func) {
        super(func, PRECISENESS);
    }
    private double parabola(double x1, double x2, double x3, double f1, double f2, double f3) {
        return x2 - 0.5 * (Math.pow(x2 - x1, 2) * (f2 - f3) - Math.pow(x2 - x3, 2) * (f2 - f1)) /
                ((x2 - x1) * (f2 - f3) - (x2 - x3) * (f2 - f1));
    }

    @Override
    protected boolean done(Segment segment) {
        return segment.length() <= eps || explicitlyDone;
    }

    @Override
    protected double getMinX(Segment segment) {
        return x;
    }
}
