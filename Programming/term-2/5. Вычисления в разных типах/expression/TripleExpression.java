package expression;

import expression.type.Type;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T extends Number> {
    Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z);
}
