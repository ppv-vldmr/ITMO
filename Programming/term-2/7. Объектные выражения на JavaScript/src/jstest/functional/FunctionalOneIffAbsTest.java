package jstest.functional;

import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalOneIffAbsTest extends FunctionalOneTwoTest {
    public static class IffAbsTests extends OneTwoTests {{
        unary("abs", Math::abs);
        any("iff", 3, args -> {
            final double[] values = args.stream().mapToDouble(a -> a).toArray();
            return values[0] >= 0 ? values[1] : values[2];
        });
        tests(
                f("abs", f("-", one, vy)),
                f("iff", f("-", vx, vy), vz, two)
        );
    }}

    protected FunctionalOneIffAbsTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalOneIffAbsTest.class, FunctionalOneIffAbsTest::new, args, new IffAbsTests());
    }
}
