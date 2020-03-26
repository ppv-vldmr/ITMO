package jstest.functional;

import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalPieSinCosTest extends FunctionalPieTest {
    public static class SinCosTests extends PieTests {{
        unary("sin", Math::sin);
        unary("cos", Math::cos);
        tests(
                f("+", f("sin", vx), vy),
                f("cos", f("+", vx, vy))
        );
    }}

    protected FunctionalPieSinCosTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalPieSinCosTest.class, FunctionalPieSinCosTest::new, args, new SinCosTests());
    }
}