package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class BinaryOperation implements Expression {
    private final String sign;
    private final Expression left;
    private final Expression right;

    public BinaryOperation(String sign, Expression left, Expression right) {
        this.sign = sign;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toStringPrefix() {
        return "(" + this.sign + "," + left.toStringPrefix() + "," + right.toStringPrefix() + ")";
    }

    @Override
    public String toStringInfix() {
        return "(" + left.toStringInfix() + this.sign + right.toStringInfix() + ")";
    }
}
