package parser;

import operation.Conjunction;
import operation.Disjunction;
import operation.Implication;
import operation.Negation;
import token.TokenType;
import token.Tokenizer;
import token.Variable;

public class PerfectParser {
    private final Tokenizer tokenizer;

    public PerfectParser() {
        this.tokenizer = new Tokenizer();
    }

    public Expression parse(String expression) {
        tokenizer.setTokens(expression);
        tokenizer.next();
        return parseImplication();
    }

    private Expression parseImplication() {
        Expression returned = parseOr(null);
        if (tokenizer.current().getType() == TokenType.IMPL) {
            tokenizer.next();
            returned = new Implication(returned, parseImplication());
        }
        return returned;
    }

    private Expression parseOr(Expression left) {
        Expression returned = parseAnd(null);
        if (tokenizer.current().getType() == TokenType.DISJ) {
            tokenizer.next();
            if (left == null) {
                returned = parseOr(returned);
            } else {
                returned = parseOr(new Disjunction(left, returned));
            }
        } else if (left != null) {
            returned = new Disjunction(left, returned);
        }
        return returned;
    }

    private Expression parseAnd(Expression left) {
        Expression returned = parseToken();
        if (tokenizer.current().getType() == TokenType.CONJ) {
            tokenizer.next();
            if (left == null) {
                returned = parseAnd(returned);
            } else {
                returned = parseAnd(new Conjunction(left, returned));
            }
        } else if (left != null) {
            returned = new Conjunction(left, returned);
        }
        return returned;
    }

    private Expression parseToken() {
        Expression returned;
        if (tokenizer.current().getType() == TokenType.LEFT_BR) {
            tokenizer.next();
            returned = parseImplication();
            if (tokenizer.current().getType() == TokenType.RIGHT_BR) {
                tokenizer.next();
            }
        } else {
            if (tokenizer.current().getType() == TokenType.NEG) {
                tokenizer.next();
                returned = new Negation(parseToken());
            } else {
                returned = new Variable(tokenizer.current().getValue());
                tokenizer.next();
            }
        }
        return returned;
    }
}
