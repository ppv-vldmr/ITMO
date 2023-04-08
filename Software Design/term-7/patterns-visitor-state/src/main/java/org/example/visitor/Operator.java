package org.example.visitor;

import org.example.tokenizer.Token;

public enum Operator {
    ADDITION(6),
    SUBTRACTION(6),
    MULTIPLICATION(7),
    DIVISION(7),
    MODULO(7),
    ;

    private final int precedence;

    Operator(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }

    public static Operator fromToken(Token token) {
        return switch (token.type()) {
            case PLUS -> Operator.ADDITION;
            case MINUS -> Operator.SUBTRACTION;
            case ASTERISK -> Operator.MULTIPLICATION;
            case SLASH -> Operator.DIVISION;
            case PERCENT -> Operator.MODULO;
            default -> null;
        };
    }
}
