package prog.expression.operation.unchecked;

import prog.expression.TripleExpression;
import prog.expression.operation.BinaryOperation;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Multiply extends BinaryOperation {
    public Multiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected int evaluateImpl(int left, int right) {
        return left * right;
    }
}
