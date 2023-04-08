package org.example.exception;

import org.example.tokenizer.Token;

public class UnexpectedTokenException extends IllegalStateException {
    public UnexpectedTokenException(Token token) {
        super("Unexpected token: " + token + ".");
    }
}
