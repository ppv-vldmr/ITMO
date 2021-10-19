package parser;

import operation.Conjunction;
import operation.Disjunction;
import operation.Implication;
import operation.Negation;
import token.TokenType;
import token.Tokenizer;
import token.Variable;

public class ExpressionParser implements Parser {
    private final Tokenizer tokenizer;

    public ExpressionParser() {
        this.tokenizer = new Tokenizer();
    }

    public Expression parse(String expression) {
        tokenizer.setTokens(expression);
        tokenizer.next();
        return parseImplication();
    }

    private Expression parseImplication() {
        Expression returned = parseOr();
        while (tokenizer.current().getType() == TokenType.IMPL) {
            tokenizer.next();
            returned = new Implication(returned, parseImplication());
//            if (tokenizer.current().getType() == TokenType.RIGHT_BR) {
//                tokenizer.next();
//                break;
//            }
        }
        return returned;
    }

    private Expression parseOr() {
        Expression returned = parseAnd();
        while (tokenizer.current().getType() == TokenType.DISJ) {
            tokenizer.next();
//            returned = new Disjunction(returned, parseOr());
            returned = new Disjunction(returned, parseAnd());

//            if (tokenizer.current().getType() == TokenType.RIGHT_BR) {
//                tokenizer.next();
//                break;
//            }
        }
        return returned;
    }

    private Expression parseAnd() {
        Expression returned = parseToken();
        while (tokenizer.current().getType() == TokenType.CONJ) {
            tokenizer.next();
            returned = new Conjunction(returned, parseToken());
//            if (tokenizer.current().getType() == TokenType.RIGHT_BR) {
//                tokenizer.next();
//                break;
//            }
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
