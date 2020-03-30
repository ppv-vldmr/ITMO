package jstest.functional;

import jstest.ArithmeticTests;
import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalIffAbsTest extends FunctionalExpressionTest {
    public static class IffAbsTests extends ArithmeticTests {{
        unary("abs", Math::abs);
        any("iff", 3, args -> {
            final double[] values = args.stream().mapToDouble(a -> a).toArray();
            return values[0] >= 0 ? values[1] : values[2];
        });
        tests(
                f("abs", f("-", vx, vy)),
                f("iff", f("-", vx, vy), vz, c(5))
        );
    }}

    protected FunctionalIffAbsTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalIffAbsTest.class, FunctionalIffAbsTest::new, args, new IffAbsTests());
    }
}
