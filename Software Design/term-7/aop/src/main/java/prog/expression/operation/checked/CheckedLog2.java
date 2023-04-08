package prog.expression.operation.checked;

import prog.expression.TripleExpression;
import prog.expression.operation.UnaryOperation;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedLog2 extends UnaryOperation {
    public CheckedLog2(TripleExpression val) {
        super(val);
    }

    @Override
    protected int evaluateImpl(int val) {
        if (val <= 0) {
            throw new IllegalArgumentException("log2 " + val);
        }
        int result = -1;
        while (val > 0) {
            val /= 2;
            result++;
        }
        return result;
    }
}
