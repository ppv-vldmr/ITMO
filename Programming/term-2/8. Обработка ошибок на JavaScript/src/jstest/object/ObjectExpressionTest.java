package jstest.object;

import expression.BaseTest;
import jstest.*;

import java.util.List;
import java.util.function.BiFunction;

import static jstest.functional.FunctionalExpressionTest.POLISH;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ObjectExpressionTest extends BaseJavascriptTest<JSEngine> {
    public static final int MIN = 2;
    public static final double D = 1e-4;


    public static final Dialect PURE_DIALECT = dialect(
            "new Variable('%s')",
            "new Const(%s)",
            (op, args) -> "new " + op + "(" + String.join(", ", args) + ")"
    );
    public static final Dialect ARITHMETIC_DIALECT = PURE_DIALECT.copy()
            .rename("+", "Add")
            .rename("-", "Subtract")
            .rename("*", "Multiply")
            .rename("/", "Divide")
            .rename("negate", "Negate");

    final List<int[]> simplifications = list(
            new int[]{1, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 2, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, 10},
            new int[]{4, 1, 1},
            new int[]{21, 28, 28},
            new int[]{5, 5, 5},
            new int[]{5, 2, 21}
    );

    protected final boolean testSimplify;
    protected final boolean testDiff;

    protected ObjectExpressionTest(final int mode, final Language language) {
        super(new JSEngine("objectExpression.js", ".evaluate"), language, mode >= 1);
        testDiff = mode >= 2;
        testSimplify = mode >= 3;
    }

    @Override
    protected void test() {
        super.test();

        if (testDiff) {
            for (final Expr<TExpr> test : language.tests) {
                testDiff(test, test.parsed, null);
                testDiff(test, parse(test.unparsed), null);
            }
            if (testSimplify) {
                for (int i = 0; i < simplifications.size(); i++) {
                    final Expr<TExpr> test = language.tests.get(i);
                    testDiff(test, parse(test.unparsed), simplifications.get(i));
                }
            }
        }
    }

    private void testDiff(final Expr<TExpr> test, final String expression, final int[] simplifications) {
//        final int[] actual = new int[3];
        for (int variable = 0; variable < 3; variable++) {
            final String s = expression + ".diff('" + "xyz".charAt(variable) + "')";
            final String value = s + (simplifications != null ? ".simplify()" : "");
            System.out.println("Testing: " + value);
            engine.parse(value);
            if (simplifications != null) {
                final Engine.Result<String> result = engine.parsedToString();
                final int length = result.value.length();
                final int expected = simplifications[variable];
                assertTrue(
                        String.format(
                                "Simplified length too long: %d instead of %d%s",
                                length,
                                expected,
                                result.context
                        ),
                        length <= expected
                );
//                actual[variable] = length;
            }
            for (int i = MIN; i <= N; i += 1) {
                final double di = variable == 0 ? D : 0;
                for (int j = MIN; j <= N; j += 1) {
                    final double dj = variable == 1 ? D : 0;
                    for (int k = MIN; k <= N; k += 1) {
                        final double dk = variable == 2 ? D : 0;
                        final double expected = (
                                test.answer.evaluate(i + di, j + dj, k + dk) -
                                        test.answer.evaluate(i - di, j - dj, k - dk)) / D / 2;
                        evaluate(new double[]{i, j, k}, expected, EPS);
                    }
                }
            }
        }
//        System.out.format("new int[]{%s}%n", Arrays.stream(actual).mapToObj(Integer::toString).collect(Collectors.joining(", ")));
    }

    @Override
    protected String parse(final String expression) {
        return "parse('" + expression + "')";
    }

    @Override
    protected void test(final String parsed, final String unparsed) {
        counter.nextTest();
        testToString(parsed, unparsed);

        testToString(BaseJavascriptTest.addSpaces(parsed, random), unparsed);
        counter.passed();
    }

    private void testToString(final String expression, final String expected) {
        engine.parse(expression);
        final Engine.Result<String> result = engine.parsedToString();
        assertEquals(result.context, expected, result.value);
    }

    public static void main(final String... args) {
        test(ObjectExpressionTest.class, ObjectExpressionTest::new, new ArithmeticTests(), args, ARITHMETIC_DIALECT);
    }

    public static <T extends BaseTest> void test(final Class<T> type, final BiFunction<Integer, Language, T> cons, final AbstractTests tests, final String[] args, final Dialect parsed) {
        final int mode = mode(args, type, "easy", "", "hard", "bonus");
        cons.apply(mode, new Language(parsed, POLISH, tests)).run();
    }
}
