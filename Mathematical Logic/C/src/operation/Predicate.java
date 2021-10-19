package operation;

import java.util.Set;

import parser.Expression;
import proof.SubstitutionInfo;
import proof.SubstitutionStatus;
import token.Variable;

public class Predicate extends ModusPonensInfo implements Expression {

    private final String capitalVariable;
    private final Expression leftTerm;
    private final Expression rightTerm;

    public Predicate(String name) {
        this.capitalVariable = name;
        this.leftTerm = null;
        this.rightTerm = null;
    }

    public Predicate(Expression left, Expression right) {
        this.capitalVariable = null;
        this.leftTerm = left;
        this.rightTerm = right;
    }

    @Override
    public String toStringPrefix() {
        if (capitalVariable == null) {
            return "(" + this.leftTerm.toStringPrefix() + "=" + this.rightTerm.toStringPrefix() + ")";
        }
        return capitalVariable;
    }

    @Override
    public String toStringInfix() {
        if (capitalVariable == null) {
            return "(" + this.leftTerm.toStringInfix() + "=" + this.rightTerm.toStringInfix() + ")";
        }
        return capitalVariable;
    }

    @Override
    public boolean equals(Expression expression) {
        try {
            Predicate p = (Predicate) expression;
            if (capitalVariable == null) {
                if (p.capitalVariable == null) {
                    return this.leftTerm.equals(p.leftTerm) && this.rightTerm.equals(p.rightTerm);
                }
            } else {
                if (p.capitalVariable != null) {
                    return this.capitalVariable.equals(p.capitalVariable);
                }
            }
        } catch (ClassCastException e) {
            // ignore
        }
        return false;
    }

    @Override
    public void substitution(Expression compareWith, SubstitutionInfo info) {
        if (capitalVariable == null) {
            try {
                Predicate compareWithP = (Predicate) compareWith;
                if (compareWithP.capitalVariable == null) {
                    this.leftTerm.substitution(compareWithP.leftTerm, info);
                    if (info.status == SubstitutionStatus.BRUH) {
                        return;
                    }
                    this.rightTerm.substitution(compareWithP.rightTerm, info);
                } else {
                    info.status = SubstitutionStatus.BRUH;
                }
            } catch (Exception e) {
                info.status = SubstitutionStatus.BRUH;
            }
        } else {
            try {
                Predicate compareWithP = (Predicate) compareWith;
                if (compareWithP.capitalVariable != null) {
                    if (!this.capitalVariable.equals(compareWithP.getCapitalVariable())) {
                        info.status = SubstitutionStatus.BRUH;
                    }
                } else {
                    info.status = SubstitutionStatus.BRUH;
                }
            } catch (Exception e) {
                info.status = SubstitutionStatus.BRUH;
            }
        }
    }

    @Override
    public boolean isFree(Set<String> bound) {
        if (this.capitalVariable == null) {
            return this.leftTerm.isFree(bound) && this.rightTerm.isFree(bound);
        } else {
            return false;
        }
    }

    @Override
    public boolean isFreeForV(Variable v) {
        if (this.capitalVariable == null) {
             return this.leftTerm.isFreeForV(v) && this.rightTerm.isFreeForV(v);
        } else {
            return false;
        }
    }

    @Override
    public boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info) {
        Predicate pr;
        try {
            pr = (Predicate) expr;
        } catch (Exception e) {
            return false;
        }
        if (this.capitalVariable == null) {
            if (pr.capitalVariable != null) {
                return false;
            }
            return this.leftTerm.correctSubstIn(pr.leftTerm, x, info) && this.rightTerm.correctSubstIn(pr.rightTerm, x, info);
        } else {
            return this.capitalVariable.equals(pr.capitalVariable);
        }
    }

    public Expression getLeftTerm() {
        return leftTerm;
    }

    public Expression getRightTerm() {
        return rightTerm;
    }

    public String getCapitalVariable() {
        return capitalVariable;
    }
}
