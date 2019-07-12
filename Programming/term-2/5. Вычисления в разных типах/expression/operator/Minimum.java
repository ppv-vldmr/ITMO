package expression.operator;

import base.Triple;
import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Minimum<T extends Number> extends BinaryOperator<T> {
    public Minimum(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> left, Type<T> right) {
        return left.minimum(right);
    }
}
