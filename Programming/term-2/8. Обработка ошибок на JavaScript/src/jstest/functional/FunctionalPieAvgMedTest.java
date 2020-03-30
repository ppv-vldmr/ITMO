package jstest.functional;

import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalPieAvgMedTest extends FunctionalPieTest {
    public static class PieAvgMedTests extends PieTests {{
        any("avg5", 5, args -> args.stream().mapToDouble(a -> a).summaryStatistics().getAverage());
        any("med3", 3, args -> args.stream().mapToDouble(a -> a).sorted().skip(1).findFirst().orElse(0));
        tests(
                f("med3", vx, vy, vz),
                f("avg5", vx, vy, vz, c(3), c(5))
        );
    }}

    protected FunctionalPieAvgMedTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalPieAvgMedTest.class, FunctionalPieAvgMedTest::new, args, new PieAvgMedTests());
    }
}
