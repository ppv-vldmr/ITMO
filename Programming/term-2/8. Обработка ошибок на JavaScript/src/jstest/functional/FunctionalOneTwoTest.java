package jstest.functional;

import jstest.ArithmeticTests;
import jstest.Language;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class FunctionalOneTwoTest extends FunctionalExpressionTest {
    public static class OneTwoTests extends ArithmeticTests {
        protected final AbstractExpression one = constant(1, "one");
        protected final AbstractExpression two = constant(2, "two");

        {
            tests(
                    f("+", one, vx),
                    f("-", vy, two),
                    f("*", vx, one),
                    f("/", two, vy)
            );
        }

        private AbstractExpression constant(final double value, final String name) {
            final TExpr expr = vars -> value;
            nullary(name, expr);
            return (parsed, unparsed) -> expr(name, name, expr);
       }
    }

    protected FunctionalOneTwoTest(final Language language, final boolean testParsing) {
        super(language, testParsing);
    }

    public static void main(final String... args) {
        test(FunctionalOneTwoTest.class, FunctionalOneTwoTest::new, args, new OneTwoTests());
    }
}
