package expression.operation.checked;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.operation.UnaryOperation;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(TripleExpression left) {
        super(left);
    }

    @Override
    protected int evaluateImpl(int v) throws ArithmeticException {
        if (v == Integer.MIN_VALUE) {
            throw new OverflowException("-", v);
        }
        return -v;
    }
}
