package expression.parser;

import expression.TripleExpression;
import expression.generic.ParsingException;
import expression.operator.*;
import expression.token.Token;
import expression.token.TokenType;
import expression.token.Tokenizer;
import expression.type.Type;
import expression.variable.Const;
import expression.variable.Variable;

import java.util.function.Function;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class ExpressionParser<T extends Number> implements Parser<T> {
    private Tokenizer tokens;
    private Function<String, Type<T>> typeParser;
    private int lastLeftBracket = -1;

    public ExpressionParser(Function<String, Type<T>> typeParser) {
        this.typeParser = typeParser;
    }

    public TripleExpression<T> parse(String expression) throws ParsingException {
        tokens = new Tokenizer(expression);
        return expression(false);
    }

    private TripleExpression<T> expression(boolean isLeftBracketPresent) throws ParsingException {
        TripleExpression<T> acc = first();

        if (tokens.hasNext()) {
            Token token = tokens.next();

            switch (token.getType()) {
                case END:
                    if (isLeftBracketPresent) {
                        throw new ParsingException("no pairing closing parenthesis for opening parenthesis", lastLeftBracket);
                    }
                    break;
                case LEFT_BR:
                    throw new ParsingException("unexpected ( found", token.getIdx());

                case RIGHT_BR:
                    if (!isLeftBracketPresent) {
                        throw new ParsingException("no pairing opening parenthesis for closing parenthesis", token.getIdx());
                    }
                    break;
                default:
                    throw new ParsingException("incorrect expression", token.getIdx());
            }
        }

        return acc;
    }

    private TripleExpression<T> first() throws ParsingException {
        TripleExpression<T> acc = add();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case MINIMUM:
                    acc = new Minimum<>(acc, add());
                    break;

                case MAXIMUM:
                    acc = new Maximum<>(acc, add());
                    break;

                default:
                    tokens.prev();
                    return acc;
            }
        }
        return acc;
    }

    private TripleExpression<T> add() throws ParsingException {
        TripleExpression<T> acc = term();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case PLUS:
                    acc = new Add<>(acc, term());
                    break;

                case MINUS:
                    acc = new Subtract<>(acc, term());
                    break;

                default:
                    tokens.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression<T> term() throws ParsingException {
        TripleExpression<T> acc = primary();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case MUL:
                    acc = new Multiply<>(acc, primary());
                    break;

                case DIV:
                    acc = new Divide<>(acc, primary());
                    break;

                case MOD:
                    acc = new Mod<>(acc, primary());
                    break;

                default:
                    tokens.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression<T> primary() throws ParsingException {
        Token token = tokens.next();
        TripleExpression<T> primary;

        switch (token.getType()) {
            case END:
            case RIGHT_BR:
                if (tokens.prev().getType() == TokenType.LEFT_BR) {
                    throw new ParsingException(String.format("empty parenthesis sequence at position %d - %d", lastLeftBracket, token.getIdx()));
                }
                throw new ParsingException("no last argument for operator " + tokens.current().getValue(), token.getIdx());

            case CONST:
                primary = new Const<>(typeParser.apply(token.getValue()));
                break;

            case VARIABLE:
                primary = new Variable<>(token.getValue());
                break;

            case ABS:
                primary = new Abs<>(primary());
                break;

            case SQUARE:
                primary = new Square<>(primary());
                break;

            case COUNT:
                primary = new Count<>(primary());
                break;

            case MINUS:
                if (tokens.hasNext() && tokens.next().getType() == TokenType.CONST) {
                    Token number = tokens.current();
                    primary = new Const<>(typeParser.apply("-" + number.getValue()));
                } else {
                    tokens.prev();
                    primary = new Negate<>(primary());
                }
                break;

            case LEFT_BR:
                lastLeftBracket = token.getIdx();
                primary = expression(true);
                if (!(tokens.current().getType() == TokenType.RIGHT_BR)) {
                    throw new ParsingException("no pairing closing parenthesis for opening parenthesis", lastLeftBracket);
                }
                return primary;

            default:
                if (token.getIdx() == 0) {
                    throw new ParsingException("no first argument for operator " + token.getValue(), token.getIdx());
                }
                Token prev = tokens.prev();
                if (prev.getType() == TokenType.LEFT_BR || prev.getType() == TokenType.MINUS) {
                    throw new ParsingException("no first argument for operator " + token.getValue(), token.getIdx());
                }
                throw new ParsingException(String.format("no middle argument between operator %s " +
                                "at position %d and operator %s",
                        prev.getValue(), prev.getIdx() + 1, token.getValue()), token.getIdx());
        }

        return primary;
    }
}
