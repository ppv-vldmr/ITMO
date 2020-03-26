package expression.operation;

import expression.TripleExpression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class BinaryOperation implements TripleExpression {
    private TripleExpression left;
    private TripleExpression right;

    public BinaryOperation(TripleExpression left, TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract int evaluateImpl(int left, int right);

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateImpl(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

}
