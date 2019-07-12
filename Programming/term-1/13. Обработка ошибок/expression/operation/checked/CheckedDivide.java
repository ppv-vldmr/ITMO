package expression.operation.checked;

import expression.operation.BinaryOperation;
import expression.TripleExpression;
import expression.exceptions.DivideByZeroException;
import expression.exceptions.OverflowException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) throws ArithmeticException {
        if (right == 0) {
            throw new DivideByZeroException(left);
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException(left, "/", right);
        }
        return left / right;
    }
}
