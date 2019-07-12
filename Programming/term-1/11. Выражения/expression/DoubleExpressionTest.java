package expression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class DoubleExpressionTest extends ExpressionTest {
    public DoubleExpressionTest(final boolean checkMini) {
        super(checkMini);
    }

    @Override
    protected void test() {
        super.test();

        handmade();
        generated();
    }

    private void handmade() {
        testExpression("10", "10", new Const(10), x -> 10);
        testExpression("x", "x", new Variable("x"), x -> x);
        testExpression("(x + 2)", "x + 2", new Add(new Variable("x"), new Const(2)), x -> x + 2);
        testExpression("(2 - x)", "2 - x", new Subtract(new Const(2), new Variable("x")), x -> 2 - x);
        testExpression("(3 * x)", "3 * x", new Multiply(new Const(3), new Variable("x")), x -> 3*x);
        testExpression("(x + x)", "x + x", new Add(new Variable("x"), new Variable("x")), x -> x + x);
        testExpression("(x / -2)", "x / -2", new Divide(new Variable("x"), new Const(-2)), x -> -x / 2);
        testExpression("(x + x)", "x + x", new Add(new Variable("x"), new Variable("x")), x -> x + x);
        testExpression("(2 + x)", "2 + x", new Add(new Const(2), new Variable("x")), x -> 2 + x);
        testExpression("(x + 2)", "x + 2", new Add(new Variable("x"), new Const(2)), x -> x + 2);
        testExpression("((1 + 2) + 3)", "1 + 2 + 3", new Add(new Add(new Const(1), new Const(2)), new Const(3)), x -> 6);
        testExpression("(1 + (2 + 3))", "1 + 2 + 3", new Add(new Const(1), new Add(new Const(2), new Const(3))), x -> 6);
        testExpression("((1 - 2) - 3)", "1 - 2 - 3", new Subtract(new Subtract(new Const(1), new Const(2)), new Const(3)), x -> -4);
        testExpression("(1 - (2 - 3))", "1 - (2 - 3)", new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))), x -> 2);
        testExpression("((1 * 2) * 3)", "1 * 2 * 3", new Multiply(new Multiply(new Const(1), new Const(2)), new Const(3)), x -> 6);
        testExpression("(1 * (2 * 3))", "1 * 2 * 3", new Multiply(new Const(1), new Multiply(new Const(2), new Const(3))), x -> 6);
        testExpression("((10 / 2) / 3)", "10 / 2 / 3", new Divide(new Divide(new Const(10), new Const(2)), new Const(3)), x -> 5.0 / 3);
        testExpression("(10 / (3 / 2))", "10 / (3 / 2)", new Divide(new Const(10), new Divide(new Const(3), new Const(2))), x -> 20.0 / 3);
        testExpression(
                "((x * x) + ((x - 1) / 10))",
                "x * x + (x - 1) / 10",
                new Add(
                        new Multiply(new Variable("x"), new Variable("x")),
                        new Divide(new Subtract(new Variable("x"), new Const(1)), new Const(10))
                ),
                x -> x * x + (x - 1) / 10
        );
        testExpression("(x * -1000000000)", "x * -1000000000", new Multiply(new Variable("x"), new Const(-1_000_000_000)), x -> x * -1_000_000_000);
        testExpression("(10 / x)", "10 / x", new Divide(new Const(10), new Variable("x")), x -> 10 / x);
        testExpression("(x / x)", "x / x", new Divide(new Variable("x"), new Variable("x")), x -> x / x);
    }

    private void generated() {
        final Variable vx = new Variable("x");
        final Const c1 = new Const(1.1);
        final Const c2 = new Const(2.1);

        testExpression("(x + x)", "x + x", new Add(vx, vx), x -> x + x);
        testExpression("(x - x)", "x - x", new Subtract(vx, vx), x -> x - x);
        testExpression("(1.1 * x)", "1.1 * x", new Multiply(c1, vx), x -> 1.1 * x);
        testExpression("(1.1 / 2.1)", "1.1 / 2.1", new Divide(c1, c2), x -> 1.1 / 2.1);
        testExpression("(1.1 + (1.1 / 2.1))", "1.1 + 1.1 / 2.1", new Add(c1, new Divide(c1, c2)), x -> 1.1 + 1.1 / 2.1);
        testExpression("(2.1 - (1.1 * x))", "2.1 - 1.1 * x", new Subtract(c2, new Multiply(c1, vx)), x -> 2.1 - 1.1 * x);
        testExpression("(2.1 * (x + x))", "2.1 * (x + x)", new Multiply(c2, new Add(vx, vx)), x -> 2.1 * (x + x));
        testExpression("(x / (x - x))", "x / (x - x)", new Divide(vx, new Subtract(vx, vx)), x -> x / (x - x));
        testExpression("((x - x) + 1.1)", "x - x + 1.1", new Add(new Subtract(vx, vx), c1), x -> x - x + 1.1);
        testExpression("((1.1 / 2.1) - x)", "1.1 / 2.1 - x", new Subtract(new Divide(c1, c2), vx), x -> 1.1 / 2.1 - x);
        testExpression("((1.1 / 2.1) * x)", "1.1 / 2.1 * x", new Multiply(new Divide(c1, c2), vx), x -> 1.1 / 2.1 * x);
        testExpression("((x - x) / 2.1)", "(x - x) / 2.1", new Divide(new Subtract(vx, vx), c2), x -> (x - x) / 2.1);
        testExpression("(x + ((1.1 / 2.1) * x))", "x + 1.1 / 2.1 * x", new Add(vx, new Multiply(new Divide(c1, c2), vx)), x -> x + 1.1 / 2.1 * x);
        testExpression("(2.1 - (2.1 - (1.1 * x)))", "2.1 - (2.1 - 1.1 * x)", new Subtract(c2, new Subtract(c2, new Multiply(c1, vx))), x -> 2.1 - (2.1 - 1.1 * x));
        testExpression("(1.1 * ((x - x) + 1.1))", "1.1 * (x - x + 1.1)", new Multiply(c1, new Add(new Subtract(vx, vx), c1)), x -> 1.1 * (x - x + 1.1));
        testExpression("(x / (2.1 - (1.1 * x)))", "x / (2.1 - 1.1 * x)", new Divide(vx, new Subtract(c2, new Multiply(c1, vx))), x -> x / (2.1 - 1.1 * x));
        testExpression("((1.1 * x) + (1.1 / 2.1))", "1.1 * x + 1.1 / 2.1", new Add(new Multiply(c1, vx), new Divide(c1, c2)), x -> 1.1 * x + 1.1 / 2.1);
        testExpression("((x + x) - (1.1 * x))", "x + x - 1.1 * x", new Subtract(new Add(vx, vx), new Multiply(c1, vx)), x -> x + x - 1.1 * x);
        testExpression("((1.1 * x) * (1.1 / 2.1))", "1.1 * x * (1.1 / 2.1)", new Multiply(new Multiply(c1, vx), new Divide(c1, c2)), x -> 1.1 * x * (1.1 / 2.1));
        testExpression("((1.1 * x) / (x + x))", "1.1 * x / (x + x)", new Divide(new Multiply(c1, vx), new Add(vx, vx)), x -> 1.1 * x / (x + x));
        testExpression("(((x - x) / 2.1) + 2.1)", "(x - x) / 2.1 + 2.1", new Add(new Divide(new Subtract(vx, vx), c2), c2), x -> (x - x) / 2.1 + 2.1);
        testExpression("((x / (x - x)) - 1.1)", "x / (x - x) - 1.1", new Subtract(new Divide(vx, new Subtract(vx, vx)), c1), x -> x / (x - x) - 1.1);
        testExpression("((2.1 - (1.1 * x)) * 1.1)", "(2.1 - 1.1 * x) * 1.1", new Multiply(new Subtract(c2, new Multiply(c1, vx)), c1), x -> (2.1 - 1.1 * x) * 1.1);
        testExpression("((x / (x - x)) / x)", "x / (x - x) / x", new Divide(new Divide(vx, new Subtract(vx, vx)), vx), x -> x / (x - x) / x);
    }

    private void testExpression(final String full, final String mini, final DoubleExpression actual, final DoubleExpression expected) {
        System.out.println("Testing " + mini);
        System.out.println(actual.evaluate(0) +  " " + expected.evaluate(0));
        for (int i = 0; i < 10; i++) {
            check(i, actual, expected);
            check(-i, actual, expected);
        }
        checkEqualsAndToString(full, mini, actual);
    }

    private static void check(final int x, final DoubleExpression actual, final DoubleExpression expected) {
        assertEquals(String.format("f(%d)", x), expected.evaluate(x), actual.evaluate(x));
    }

    public static void main(final String[] args) {
        new DoubleExpressionTest(mode(args)).run();
    }
}