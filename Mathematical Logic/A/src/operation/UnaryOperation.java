package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class UnaryOperation implements Expression {
    private final String sign;
    private final Expression val;

    public UnaryOperation(String sign, Expression val) {
        this.sign = sign;
        this.val = val;
    }

    @Override
    public String toStringPrefix() {
        return "(" + sign + val.toStringPrefix() + ")";
    }

    @Override
    public String toStringInfix() {
        return "(" + sign + val.toStringInfix() + ")";
    }
}
