package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class BinaryOperator<T extends Number> implements TripleExpression<T> {
    private TripleExpression<T> left;
    private TripleExpression<T> right;

    public BinaryOperator(TripleExpression<T> left, TripleExpression<T> right) {
        this.left = left;
        this.right = right;
    }

    protected abstract Type<T> evaluateImpl(Type<T> left, Type<T> right);

    @Override
    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

}
