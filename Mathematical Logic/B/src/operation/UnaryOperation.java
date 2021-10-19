package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class UnaryOperation extends ModusPonensInfo implements Expression {
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

    @Override
    public boolean equals(Expression expression) {
        try {
            UnaryOperation uo = (UnaryOperation) expression;
            return this.sign.equals(uo.sign) && this.val.equals(uo.val);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public String getSign() {
        return sign;
    }

    public Expression getVal() {
        return val;
    }
}
