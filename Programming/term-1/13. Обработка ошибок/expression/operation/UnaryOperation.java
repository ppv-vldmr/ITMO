package expression.operation;

import expression.TripleExpression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class UnaryOperation implements TripleExpression {
    private TripleExpression val;

    public UnaryOperation(TripleExpression val) {
        this.val = val;
    }

    protected abstract int evaluateImpl(int val);

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateImpl(val.evaluate(x, y, z));
    }
}
