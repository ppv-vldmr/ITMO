package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Multiply<T extends Number> extends BinaryOperator<T> {
    public Multiply(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> left, Type<T> right) {
        return left.multiply(right);
    }
}
