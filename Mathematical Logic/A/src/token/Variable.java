package token;

import parser.Expression;

public class Variable implements Expression {
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
}
