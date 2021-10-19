package parser;

import operation.Conjunction;
import operation.Disjunction;
import operation.Implication;
import operation.Increment;
import operation.Multiplication;
import operation.Negation;
import operation.Predicate;
import operation.Summation;
import operation.WithQuantifier;
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
        Expression returned = parseUnary();
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

    private Expression parseUnary() {
        Expression returned;
        if (tokenizer.current().getType() == TokenType.NEG) {
            tokenizer.next();
            returned = new Negation(parseUnary());
        } else {
            if (tokenizer.current().getType() == TokenType.LEFT_BR) {
                tokenizer.next();
                returned = parseImplication();
                tokenizer.next();
                if (tokenizer.current().getType() == TokenType.INCREMENT) {
                    while (tokenizer.current().getType() == TokenType.INCREMENT) {
                        returned = new Increment(returned);
                        tokenizer.next();
                    }
                }
                if (tokenizer.current().getType() == TokenType.MUL) {
                    tokenizer.next();
                    returned = parseMul(returned);
                }
                if (tokenizer.current().getType() == TokenType.PLUS) {
                    tokenizer.next();
                    returned = parseSum(returned);
                }
                if (tokenizer.current().getType() == TokenType.EQ) {
                    tokenizer.next();
                    returned = new Predicate(returned, parseSum(null));
                }
            } else {
                if (tokenizer.current().getType() == TokenType.QUAN) {
                    String q = tokenizer.current().getValue();
                    tokenizer.next();
                    Variable var = new Variable(tokenizer.current().getValue());
                    tokenizer.next();
                    returned = new WithQuantifier(q, var, parseImplication());
                } else {
                    if (tokenizer.current().getType() == TokenType.PREDICATE) {
                        returned = new Predicate(tokenizer.current().getValue());
                        tokenizer.next();
                    } else {
                        returned = parseSum(null);
                        if (tokenizer.current().getType() == TokenType.EQ) {
                            tokenizer.next();
                            returned = new Predicate(returned, parseSum(null));
                        }
                    }
                }
            }
        }
        return returned;
    }

    private Expression parseSum(Expression left) {
        Expression returned = parseMul(null);
        if (tokenizer.current().getType() == TokenType.PLUS) {
            tokenizer.next();
            if (left == null) {
                returned = parseSum(returned);
            } else {
                returned = parseSum(new Summation(left, returned));
            }
        } else if (left != null) {
            returned = new Summation(left, returned);
        }
        return returned;
    }

    private Expression parseMul(Expression left) {
        Expression returned = parseInc();
        if (tokenizer.current().getType() == TokenType.MUL) {
            tokenizer.next();
            if (left == null) {
                returned = parseMul(returned);
            } else {
                returned = parseMul(new Multiplication(left, returned));
            }
        } else if (left != null) {
            returned = new Multiplication(left, returned);
        }
        return returned;
    }

    private Expression parseInc() {
        Expression returned = parseVar();
        while (tokenizer.current().getType() == TokenType.INCREMENT) {
            returned = new Increment(returned);
            tokenizer.next();
        }
        return returned;
    }

    private Expression parseVar() {
        Expression returned;
        if (tokenizer.current().getType() == TokenType.VARIABLE) {
            returned = new Variable(tokenizer.current().getValue());
        } else {
            if (tokenizer.current().getType() == TokenType.LEFT_BR) {
                tokenizer.next();
                returned = parseImplication();
            } else {
                returned = new Variable(tokenizer.current().getValue());
            }
        }
        tokenizer.next();
        return returned;
    }
}
