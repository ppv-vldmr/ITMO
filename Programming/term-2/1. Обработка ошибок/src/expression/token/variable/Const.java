package expression.token.variable;

import expression.TripleExpression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Const implements TripleExpression {
    public static final Const MINUS_ONE = new Const(-1);
    public static final Const ZERO = new Const(0);

    private int val;

    public Const(int val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val;
    }
}
