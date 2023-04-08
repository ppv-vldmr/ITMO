package prog.expression.operation.checked;

import prog.expression.TripleExpression;
import prog.expression.exceptions.OverflowException;
import prog.expression.operation.UnaryOperation;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedPow2 extends UnaryOperation {
    public CheckedPow2(TripleExpression left) {
        super(left);
    }

    @Override
    protected int evaluateImpl(int v) throws OverflowException {
        if (v < 0) {
            throw new IllegalArgumentException("pow2 " + v);
        }
        if (v > 31) {
            throw new OverflowException("pow2", v);
        }
        return 1 << v;
    }
}
