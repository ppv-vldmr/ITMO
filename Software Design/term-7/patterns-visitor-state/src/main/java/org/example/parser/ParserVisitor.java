package org.example.parser;

import org.example.exception.ParsingException;
import org.example.exception.UnexpectedTokenException;
import org.example.tokenizer.Token;
import org.example.tokenizer.TokenType;
import org.example.tokenizer.Tokenizer;
import org.example.visitor.Operator;
import org.example.visitor.TokenVisitor;

import java.util.*;

import static org.example.tokenizer.TokenType.*;

public class ParserVisitor extends TokenVisitor implements Parser {
    private static final Set<TokenType> NUMERIC_AVAILABLE_TYPES = Set.of(
            NUMBER, CLOSING_BRACKET, END
    );
    private static final Set<TokenType> OPERATION_AVAILABLE_TYPES = Set.of(
            PLUS, MINUS, ASTERISK, SLASH, PERCENT, END, BEGIN, OPENING_BRACKET
    );

    private final Tokenizer tokenizer;
    private final Deque<Deque<Token>> layers;
    private final Deque<Integer> precedences;

    public ParserVisitor(CharSequence charSequence) {
        this.tokenizer = new Tokenizer(charSequence);
        this.layers = new ArrayDeque<>();
        this.precedences = new ArrayDeque<>();
    }

    public List<Token> parse() {
        if (layers.isEmpty()) {
            layers.push(new ArrayDeque<>());
            while (!tokenizer.currentToken().isEnd()) {
                final Token previousToken = tokenizer.currentToken();
                final Token token = tokenizer.nextToken();
                checkTokenOrder(token, previousToken);
                if (!token.isEnd()) {
                    visit(token);
                }
            }
        }

        foldLayers(-1);
        if (layers.size() != 1) {
            throw new UnexpectedTokenException(tokenizer.currentToken());
        }

        final List<Token> result = new ArrayList<>();
        final Deque<Token> mainLayer = layers.getLast();
        for (Iterator<Token> iterator = mainLayer.descendingIterator(); iterator.hasNext(); ) {
            result.add(iterator.next());
        }
        return result;
    }

    @Override
    protected void visitNumber(Number number) {
        if (layers.peek() == null) {
            throw new ParsingException();
        }
        layers.peek().push(tokenizer.currentToken());
    }

    @Override
    protected void visitOperator(Operator operator) {
        foldLayers(operator.getPrecedence());
        if (layers.peek() == null) {
            throw new ParsingException();
        }
        layers.peek().push(tokenizer.currentToken());
        layers.push(new ArrayDeque<>());
        precedences.push(operator.getPrecedence());
    }

    @Override
    protected void visitBracket(boolean opening) {
        if (opening) {
            layers.push(new ArrayDeque<>());
            precedences.push(-2);
        } else {
            foldLayers(-1);
            if (precedences.isEmpty()) {
                throw new UnexpectedTokenException(tokenizer.currentToken());
            }
            precedences.pop();
            final Deque<Token> currentLayer = layers.pop();
            if (currentLayer.size() == 0) {
                throw new UnexpectedTokenException(tokenizer.currentToken());
            }
            final Deque<Token> nextLayer = layers.peek();
            if (nextLayer == null) {
                throw new ParsingException();
            }

            for (final Iterator<Token> iterator = currentLayer.descendingIterator(); iterator.hasNext(); ) {
                nextLayer.push(iterator.next());
            }
        }
    }

    private void checkTokenOrder(Token currentToken, Token previousToken) {
        switch (currentToken.type()) {
            case NUMBER, OPENING_BRACKET -> {
                if (NUMERIC_AVAILABLE_TYPES.contains(previousToken.type())) {
                    throw new UnexpectedTokenException(currentToken);
                }
            }
            case PLUS, MINUS, ASTERISK, SLASH, PERCENT, END, CLOSING_BRACKET -> {
                if (OPERATION_AVAILABLE_TYPES.contains(previousToken.type())) {
                    throw new UnexpectedTokenException(currentToken);
                }
            }
            case BEGIN -> throw new UnexpectedTokenException(currentToken);
        }
    }

    private void foldLayers(int precedence) {
        while (!precedences.isEmpty() && precedences.peek() >= precedence) {
            precedences.pop();
            final Deque<Token> rightOperand = layers.pop();
            final Deque<Token> leftOperand = layers.peek();
            if (leftOperand == null) {
                throw new ParsingException();
            }

            final Token operatorToken = leftOperand.pop();
            for (Iterator<Token> iterator = rightOperand.descendingIterator(); iterator.hasNext(); ) {
                leftOperand.push(iterator.next());
            }
            leftOperand.push(operatorToken);
        }
    }

}
