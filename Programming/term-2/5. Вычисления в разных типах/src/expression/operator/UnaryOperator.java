package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class UnaryOperator<T extends Number> implements TripleExpression<T> {
    private TripleExpression<T> val;

    public UnaryOperator(TripleExpression<T> val) {
        this.val = val;
    }

    protected abstract Type<T> evaluateImpl(Type<T> val);

    @Override
    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        return evaluateImpl(val.evaluate(x, y, z));
    }
}
