package prog.expression.operation.checked;

import prog.expression.operation.BinaryOperation;
import prog.expression.TripleExpression;
import prog.expression.exceptions.OverflowException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws ArithmeticException {
        if (right < 0 && Integer.MAX_VALUE + right < left ||
                right > 0 && Integer.MIN_VALUE + right > left) {
            throw new OverflowException(left, "-", right);
        }
        return left - right ;
    }
}
