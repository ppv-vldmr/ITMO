package expression.generic;

import expression.TripleExpression;
import expression.parser.ExpressionParser;
import expression.type.Type;

import java.util.function.Function;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class TabulateExecutor<T extends Number> {
    private Function<String, Type<T>> parser;

    public TabulateExecutor(Function<String, Type<T>> parser) throws ParsingException{
        this.parser = parser;
    }

    public Object[][][] execute(String expr, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        TripleExpression<T> expression = new ExpressionParser<>(parser).parse(expr);
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int deltaZ = z2 - z1;
        Object[][][] table = new Object[deltaX + 1][deltaY + 1][deltaZ + 1];
        for (int x = 0; x <= deltaX; x++) {
            Type<T> first = parser.apply(String.valueOf(x + x1));
            for (int y = 0; y <= deltaY; y++) {
                Type<T> second = parser.apply(String.valueOf(y + y1));
                for (int z = 0; z <= deltaZ; z++) {
                    try {
                        Type<T> third = parser.apply(String.valueOf(z + z1));
                        table[x][y][z] = expression.evaluate(first, second, third).value();
                    } catch (ArithmeticException e) {
                        table[x][y][z] = null;
                    }
                }
            }
        }
        return table;
    }
}