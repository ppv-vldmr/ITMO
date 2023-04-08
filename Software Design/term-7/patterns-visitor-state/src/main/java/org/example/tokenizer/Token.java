package org.example.tokenizer;

import static org.example.tokenizer.TokenType.BEGIN;
import static org.example.tokenizer.TokenType.END;

@SuppressWarnings("unused")
public record Token(TokenType type, String value, int position) {
    public static Token begin() {
        return new Token(BEGIN, "", -1);
    }

    public static Token end(int size) {
        return new Token(END, "", size);
    }

    public boolean isBegin() {
        return this.type == BEGIN;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isEnd() {
        return this.type == END;
    }
}
