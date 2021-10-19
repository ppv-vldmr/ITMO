package operation;

import java.util.Set;

import javax.xml.validation.Validator;

import parser.Expression;
import proof.SubstitutionInfo;
import proof.SubstitutionStatus;
import token.Variable;

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
        if (sign.equals("'")) {
            return val.toStringPrefix() + sign;
        }
        return "(" + sign + val.toStringPrefix() + ")";
    }

    @Override
    public String toStringInfix() {
        if (sign.equals("'")) {
            return val.toStringInfix() + sign;
        }
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

    @Override
    public void substitution(Expression compareWith, SubstitutionInfo info) {
        try {
            UnaryOperation compareWithUO = (UnaryOperation) compareWith;
            if (this.sign.equals(compareWithUO.sign)) {
                this.val.substitution(compareWithUO.val, info);
            } else {
                info.status = SubstitutionStatus.BRUH;
            }
        } catch (Exception e) {
            info.status = SubstitutionStatus.BRUH;
        }
    }

    @Override
    public boolean isFree(Set<String> bound) {
        return this.val.isFree(bound);
    }

    @Override
    public boolean isFreeForV(Variable v) {
        return this.val.isFreeForV(v);
    }

    @Override
    public boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info) {
        UnaryOperation uo;
        try {
            uo = (UnaryOperation) expr;
        } catch (Exception e) {
            return false;
        }
        if (!this.sign.equals(uo.sign)) {
            return false;
        }
        return this.val.correctSubstIn(uo.val, x, info);
    }

    public String getSign() {
        return sign;
    }

    public Expression getVal() {
        return val;
    }
}
