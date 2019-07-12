package expression;

import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Main {

    public static void main(String[] args) throws ParsingException {
        String source = "pow2";//"1000000*x*x*x*x*x/(x-1)";
        ExpressionParser parser = new ExpressionParser();
        TripleExpression result = parser.parse(source);
        System.out.println(result.evaluate(0, 0, 0));
        System.out.println(result.evaluate(1, 0, 0));
        System.out.println(result.evaluate(2, 0, 0));
        System.out.println(result.evaluate(3, 0, 0));
        System.out.println(result.evaluate(4, 0, 0));
        System.out.println(result.evaluate(5, 0, 0));
        System.out.println(result.evaluate(6, 0, 0));
        System.out.println(result.evaluate(7, 0, 0));
        System.out.println(result.evaluate(8, 0, 0));
        System.out.println(result.evaluate(9, 0, 0));
        System.out.println(result.evaluate(10, 0, 0));
    }
}
