package cljtest.multi;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MultiExpLnTests extends MultiTests {
    public MultiExpLnTests(final boolean testMulti) {
        super(testMulti);
        unary("exp", Math::exp);
        unary("ln", x -> Math.log(Math.abs(x)));
        tests(
                f("exp", f("-", vx, vy)),
                f("ln", f("+", vx, vy)),
                f("ln", f("/", f("exp", vz), f("+", vx, vy))),
                f("+", f("ln", f("exp", f("+", vx, c(10)))), f("*", vz, f("*", vy, f("ln", c(0)))))
        );
    }
}
