package prog.expression.operation.checked;

import prog.expression.TripleExpression;
import prog.expression.exceptions.OverflowException;
import prog.expression.operation.UnaryOperation;

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
