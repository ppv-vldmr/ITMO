package cljtest.multi;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MultiMinMaxTests extends MultiTests {
    public MultiMinMaxTests(final boolean testMulti) {
        super(testMulti);
        final int arity = testMulti ? -1 : 2;
        any("min", arity, xs -> xs.stream().mapToDouble(a -> a).min().getAsDouble());
        any("max", arity, xs -> xs.stream().mapToDouble(a -> a).max().getAsDouble());
        tests(
                f("min", vx, vy),
                f("max", vx, vy),
                f("min", vx, c(3)),
                f("max", vx, c(3)),
                f("max", vx, f("min", vy, vz)),
                f("min", vx, f("max", vy, vz)),
                f("/", vz, f("max", vx, vy)),
                f("+", f("min", f("+", vx, c(10)), f("*", vz, f("*", vy, c(0)))), c(2))
        );
        randomMulti(testMulti, "min", "max");
    }
}
