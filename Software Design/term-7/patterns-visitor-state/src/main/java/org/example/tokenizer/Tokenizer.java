package org.example.tokenizer;

import org.example.exception.UnexpectedSymbolException;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.example.tokenizer.TokenType.*;

public class Tokenizer implements Iterable<Token> {
    private static final Consumer<Character> EMPTY_ACTION = x -> {};

    private final CharSequence sequence;
    private final AtomicReference<Token> currentToken;
    private int currentIndex;

    public Tokenizer(CharSequence sequence) {
        this.sequence = Objects.requireNonNull(sequence);
        this.currentToken = new AtomicReference<>(Token.begin());
        this.currentIndex = 0;
    }

    public Token currentToken() {
        return currentToken.get();
    }

    public synchronized Token nextToken() {
        iterateWhile(Character::isWhitespace, EMPTY_ACTION);
        final int position = currentIndex;
        if (position >= sequence.length()) {
            currentToken.set(Token.end(sequence.length()));
            return currentToken.get();
        }

        final char firstSymbol = sequence.charAt(this.currentIndex);
        final TokenType type = switch (firstSymbol) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> NUMBER;
            case '+' -> PLUS;
            case '-', '–' -> MINUS;
            case '*' -> ASTERISK;
            case '/' -> SLASH;
            case '%' -> PERCENT;
            case '(' -> OPENING_BRACKET;
            case ')' -> CLOSING_BRACKET;
            default -> throw new UnexpectedSymbolException(sequence, this.currentIndex);
        };

        final String tokenValue = switch (type) {
            case NUMBER -> {
                final StringBuilder value = new StringBuilder();
                iterateWhile(Character::isDigit, value::append);
                yield value.toString();
            }
            case OPENING_BRACKET, CLOSING_BRACKET, PLUS, MINUS, ASTERISK, SLASH, PERCENT -> {
                this.currentIndex++;
                yield String.valueOf(firstSymbol);
            }
            case BEGIN, END -> throw new IllegalStateException("Unexpected default token type.");
        };
        currentToken.set(new Token(type, tokenValue, position));
        return currentToken.get();
    }

    private void iterateWhile(Predicate<Character> condition, Consumer<Character> action) {
        while (currentIndex < sequence.length() && condition.test(sequence.charAt(currentIndex))) {
            action.accept(sequence.charAt(currentIndex));
            currentIndex++;
        }
    }

    @Override
    public Iterator<Token> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !currentToken.get().isEnd();
            }

            @Override
            public Token next() {
                return nextToken();
            }
        };
    }
}
