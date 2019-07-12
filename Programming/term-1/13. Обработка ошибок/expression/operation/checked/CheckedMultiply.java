package expression.operation.checked;

import expression.operation.BinaryOperation;
import expression.TripleExpression;
import expression.exceptions.OverflowException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws ArithmeticException {
        if (left > 0 && right > 0 && Integer.MAX_VALUE / right < left ||
                left > 0 && right < 0 && Integer.MIN_VALUE / left > right ||
                left < 0 && right > 0 && Integer.MIN_VALUE / right > left ||
                left < 0 && right < 0 && Integer.MAX_VALUE / right > left) {
            throw new OverflowException(left, "*", right);
        }
        return left * right ;
    }
}
