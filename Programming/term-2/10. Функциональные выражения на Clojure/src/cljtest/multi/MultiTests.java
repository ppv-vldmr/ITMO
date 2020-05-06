package cljtest.multi;

import jstest.ArithmeticTests;
import jstest.VariablesTests;

import java.util.Random;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MultiTests extends VariablesTests {
    private static final Random RANDOM = new Random(343243543059325L);

    public MultiTests(final boolean testMulti) {
        arith(testMulti, "+", 0, Double::sum);
        arith(testMulti, "-", 0, (a, b) -> a - b);
        arith(testMulti, "*", 1, (a, b) -> a * b);
        binary("/", (a, b) -> a / b);
        tests.addAll(new ArithmeticTests().tests);
    }

    private void arith(final boolean testMulti, final String name, final int zero, final DoubleBinaryOperator op) {
        if (testMulti) {
            any(name, -1, vars -> {
                switch (vars.size()) {
                    case 0:
                        return (double) zero;
                    case 1:
                        return op.applyAsDouble(zero, vars.get(0));
                    default:
                        return vars.subList(1, vars.size()).stream().mapToDouble(a -> a).reduce(vars.get(0), op);
                }
            });
        } else {
            binary(name, op);
        }
    }

    @SafeVarargs
    private static <T> T random(final T... values) {
        return values[RANDOM.nextInt(values.length)];
    }


    protected void randomMulti(final boolean testMulti, final String... functions) {
        final Supplier<AbstractExpression> generator = () -> random(vx, vy, vz, c(RANDOM.nextInt(10)));
        for (int i = 1; i < 10; i++) {
            final AbstractExpression[] args = Stream.generate(generator).limit(testMulti ? i : 2).toArray(AbstractExpression[]::new);
            for (final String function : functions) {
                tests(f(function, args));
            }
        }
    }
}
