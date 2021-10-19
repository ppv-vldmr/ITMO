package token;

import java.util.HashSet;
import java.util.Set;

import operation.BinaryOperation;
import operation.ModusPonensInfo;
import parser.Expression;
import proof.SubstitutionInfo;
import proof.SubstitutionStatus;

public class Variable extends ModusPonensInfo implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toStringPrefix() {
        return name;
    }

    @Override
    public String toStringInfix() {
        return name;
    }

    @Override
    public boolean equals(Expression expression) {
        try {
            Variable v = (Variable) expression;
            return this.name.equals(v.name);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public void substitution(Expression compareWith, SubstitutionInfo info) {  // почти ok
        try {
            Variable compareWithV = (Variable) compareWith;
            if (this.name.equals(compareWithV.name)) {
                if (this.name.equals(info.var)) {
                    info.status = SubstitutionStatus.BRUH;
                }
                return;
            }
        } catch (Exception e) {
            // ignore
        }

        if (info.bound.stream().anyMatch(variable -> variable.equals(this.getName()))) {
            info.status = SubstitutionStatus.BRUH;
            return;
        }

        // замена переменной на compareWith
        if (info.theta == null) {
            info.theta = compareWith;
            info.var = this.name;
        } else if (!info.theta.equals(compareWith)
                || !info.var.equals(this.name)) {
            info.status = SubstitutionStatus.BRUH;
            return;
        }

        if (!compareWith.isFree(info.bound)) {
            info.status = SubstitutionStatus.BRUH;
            info.not_free = true;
        }
    }

    @Override
    public boolean isFree(Set<String> boundedVars) {
        return boundedVars.stream().noneMatch(var -> var.equals(this.getName()));
    }

    @Override
    public boolean isFreeForV(Variable v) {
        return !this.equals(v);
    }

    @Override
    public boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info) {
        if (this.equals(x)) {
            if (info.theta == null) {
                info.theta = expr;
                return true;
            }
            return expr.equals(info.theta); // меняем переменную на theta
        } else {
            return this.equals(expr); // проверяем что ничего не поменяли
        }
    }

    public String getName() {
        return name;
    }
}
