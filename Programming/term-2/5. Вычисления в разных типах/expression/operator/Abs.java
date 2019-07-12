package expression.operator;

import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Abs<T extends Number> extends UnaryOperator<T> {
    public Abs(TripleExpression<T> val) {
        super(val);
    }

    @Override
    protected Type<T> evaluateImpl(Type<T> val) {
        return val.abs();
    }
}
