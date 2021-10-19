package operation;

import java.util.HashSet;
import java.util.Set;

import parser.Expression;
import proof.SubstitutionInfo;
import proof.SubstitutionStatus;
import token.Variable;

public class WithQuantifier extends ModusPonensInfo implements Expression {
    private final String quantifier;
    private final Variable var;
    private final Expression expr;

    public WithQuantifier(String quantifier, Variable var, Expression expr) {
        this.quantifier = quantifier;
        this.var = var;
        this.expr = expr;
    }

    @Override
    public String toStringPrefix() {
        return "(" + this.quantifier + this.var.toStringPrefix() + "." + this.expr.toStringPrefix() + ")";
    }

    @Override
    public String toStringInfix() {
        return "(" + this.quantifier + this.var.toStringInfix() + "." + this.expr.toStringInfix() + ")";
    }

    @Override
    public boolean equals(Expression expression) {
        try {
            WithQuantifier w = (WithQuantifier) expression;
            return this.quantifier.equals(w.quantifier) && this.var.equals(w.var) && this.expr.equals(w.expr);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public void substitution(Expression compareWith, SubstitutionInfo info) {
        try {
            WithQuantifier compareWithWQ = (WithQuantifier) compareWith;
            if (this.quantifier.equals(compareWithWQ.quantifier) && this.var.equals(compareWithWQ.var)) {
                if (this.var.getName().equals(info.var)) {
                    if (!this.expr.equals(compareWithWQ.expr)) {
                        info.status = SubstitutionStatus.BRUH;
                    }
                    return;
                }
                info.bound.add(this.var.getName());
                this.expr.substitution(compareWithWQ.expr, info);
                info.bound.remove(this.var.getName());
            } else {
                info.status = SubstitutionStatus.BRUH;
            }
        } catch (Exception e) {
            info.status = SubstitutionStatus.BRUH;
        }
    }

    @Override
    public boolean isFree(Set<String> bound) {
        bound.remove(this.var.getName());
        boolean res = bound.stream().noneMatch(var -> var.equals(this.var.getName())) && this.expr.isFree(bound);
        bound.add(this.var.getName());
        return res;
    }

    @Override
    public boolean isFreeForV(Variable v) {
        return !this.var.equals(v) && this.expr.isFreeForV(v);
    }

    @Override
    public boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info) {
        WithQuantifier quantifier;
        try {
            quantifier = (WithQuantifier) expr;
        } catch (Exception e) {
            return false;
        }
        if (!this.quantifier.equals(quantifier.quantifier)
                || !this.var.equals(quantifier.var)) { // сравнение что выражения одинаковые
            return false;
        }

        if (this.var.equals(x)) { // дальше не могли ничего поменять
            return this.expr.equals(quantifier.expr);
        }
        return this.getExpr().correctSubstIn(quantifier.getExpr(), x, info);
    }

    public Set<Expression> getBoundedVars() {
        Set<Expression> result = new HashSet<>();
        result.add(var);
        try {
            WithQuantifier w = (WithQuantifier) this.expr;
            result.addAll(w.getBoundedVars());
        } catch (Exception ignored) {}
        return result;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public Variable getVar() {
        return var;
    }

    public Expression getExpr() {
        return expr;
    }
}
