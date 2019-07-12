package expression.parser;

import expression.TripleExpression;
import expression.generic.ParsingException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public interface Parser<T extends Number> {
    TripleExpression<T> parse(String expression) throws ParsingException;
}
