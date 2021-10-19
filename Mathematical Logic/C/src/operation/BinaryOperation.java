package operation;

import java.util.HashSet;
import java.util.Set;

import javax.xml.validation.Validator;

import parser.Expression;
import proof.SubstitutionInfo;
import proof.SubstitutionStatus;
import token.Variable;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class BinaryOperation extends ModusPonensInfo implements Expression {
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

    @Override
    public boolean equals(Expression expression) {
        try {
            BinaryOperation bo = (BinaryOperation) expression;
            return this.sign.equals(bo.sign) && this.left.equals(bo.left) && this.right.equals(bo.right);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public void substitution(Expression compareWith, SubstitutionInfo info) {
        try {
            BinaryOperation compareWithBO = (BinaryOperation) compareWith;
            if (this.sign.equals(compareWithBO.sign)) {
                this.left.substitution(compareWithBO.left, info);
                if (info.status == SubstitutionStatus.BRUH) {
                    return;
                }
                this.right.substitution(compareWithBO.right, info);
            } else {
                info.status = SubstitutionStatus.BRUH;
            }
        } catch (Exception e) {
            info.status = SubstitutionStatus.BRUH;
        }
    }

    @Override
    public boolean isFree(Set<String> bound) {
        return this.left.isFree(bound) && this.right.isFree(bound);
    }

    @Override
    public boolean isFreeForV(Variable v) {
        return this.left.isFreeForV(v) && this.right.isFreeForV(v);
    }

    @Override
    public boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info) {
        BinaryOperation bo;
        try {
            bo = (BinaryOperation) expr;
        } catch (Exception e) {
            return false;
        }
        if (!this.sign.equals(bo.sign)) {
            return false;
        }
        return this.left.correctSubstIn(bo.left, x, info) && this.right.correctSubstIn(bo.right, x, info);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getSign() {
        return sign;
    }
}
