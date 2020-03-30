package jstest.functional;

import jstest.ArithmeticTests;
import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalCubeTest extends FunctionalExpressionTest {
    public static class CubeTests extends ArithmeticTests {{
        unary("cube", x -> x * x * x);
        unary("cuberoot", Math::cbrt);
        tests(
                f("+", f("cube", vx), vy),
                f("cuberoot", f("+", vx, vy))
        );
    }}

    protected FunctionalCubeTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalCubeTest.class, FunctionalCubeTest::new, args, new CubeTests());
    }
}
