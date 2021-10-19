package token;

import operation.ModusPonensInfo;
import parser.Expression;

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

    public String getName() {
        return name;
    }
}
