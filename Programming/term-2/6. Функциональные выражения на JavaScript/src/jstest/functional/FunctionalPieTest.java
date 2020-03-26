package jstest.functional;

import jstest.ArithmeticTests;
import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalPieTest extends FunctionalExpressionTest {
    public static class PieTests extends ArithmeticTests {
        protected final AbstractExpression pi = constant(Math.PI, "pi");
        protected final AbstractExpression e = constant(Math.E, "e");

        {
            tests(
                    f("+", pi, vx),
                    f("-", vy, e),
                    f("*", vx, pi),
                    f("/", e, vy)
            );
        }

        private AbstractExpression constant(final double value, final String name) {
            final TExpr expr = vars -> value;
            nullary(name, expr);
            return (parsed, unparsed) -> expr(name, name, expr);
       }
    }

    protected FunctionalPieTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalPieTest.class, FunctionalPieTest::new, args, new PieTests());
    }
}
