package expression.exceptions;

import expression.parser.ParserTest;

import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ExceptionsPowLog2Test extends ExceptionsTest {
    private static final ParserTest.Reason NEG_LOG = new ParserTest.Reason("Logarithm of negative value");

    protected ExceptionsPowLog2Test() {
        unary.add(op("log2", ExceptionsPowLog2Test::log2));
        unary.add(op("pow2", ExceptionsPowLog2Test::pow2));

        tests.addAll(List.of(
                op("log2 10", (x, y, z) -> 3),
                op("log2 -4", (x, y, z) -> ParserTest.error(NEG_LOG)),
                op("log2-5", (x, y, z) -> ParserTest.error(NEG_LOG)),
                op("pow2 4", (x, y, z) -> 16),
                op("pow2 8", (x, y, z) -> 256),
                op("pow2 x * y * z", (x, y, z) -> pow2(x) * y * z),
                op("pow2(x * y * z)", (x, y, z) -> pow2(x * y * z))
        ));
        parsingTest.addAll(List.of(
                expression.exceptions.ExceptionsTest.parseExample("hello"),
                expression.exceptions.ExceptionsTest.parseExample("log2()"),
                expression.exceptions.ExceptionsTest.parseExample("log2(1, 2)"),
                expression.exceptions.ExceptionsTest.parseExample("lgg 1"),
                expression.exceptions.ExceptionsTest.parseExample("log2 *"),
                expression.exceptions.ExceptionsTest.parseExample("log2x")
        ));
    }

    private static long pow2(final long a) {
        return 0 <= a && a <= 31 ? (long) Math.pow(2, a) : ParserTest.error(expression.exceptions.ExceptionsTest.OVERFLOW);
    }

    private static long log2(final long a) {
        return a <= 0 ? ParserTest.error(NEG_LOG) : (long) (Math.log(a) / Math.log(2));
    }

    public static void main(final String[] args) {
        new ExceptionsPowLog2Test().run();
    }
}
