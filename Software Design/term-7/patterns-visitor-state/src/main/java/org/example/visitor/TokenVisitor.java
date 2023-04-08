package org.example.visitor;

import org.example.tokenizer.Token;

public abstract class TokenVisitor {
    protected abstract void visitNumber(Number number);
    protected abstract void visitOperator(Operator operator);
    protected abstract void visitBracket(boolean opening);

    protected void visit(Token token) {
        switch (token.type()) {
            case NUMBER -> {
                try {
                    visitNumber(Long.parseLong(token.value()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Too big number.");
                }
            }
            case PLUS, MINUS, ASTERISK, SLASH, PERCENT -> visitOperator(Operator.fromToken(token));
            case OPENING_BRACKET -> visitBracket(true);
            case CLOSING_BRACKET -> visitBracket(false);
        }
    }
}
