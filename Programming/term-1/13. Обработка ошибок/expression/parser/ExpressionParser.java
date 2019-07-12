package expression.parser;

import expression.*;
import expression.exceptions.ParsingException;
import expression.operation.checked.*;
import expression.token.*;
import expression.token.variable.Variable;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class ExpressionParser implements Parser {
    private Tokenizer tokens;
    private int lastLeftBracket = -1;

    public TripleExpression parse(String expression) throws ParsingException {
        tokens = new Tokenizer(expression);
        return expression(false);
    }

    private TripleExpression expression(boolean isLeftBracketPresent) throws ParsingException {
        TripleExpression acc = add();

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

    private TripleExpression add() throws ParsingException {
        TripleExpression acc = term();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case PLUS:
                    acc = new CheckedAdd(acc, term());
                    break;

                case MINUS:
                    acc = new CheckedSubtract(acc, term());
                    break;

                default:
                    tokens.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression term() throws ParsingException {
        TripleExpression acc = primary();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case MUL:
                    acc = new CheckedMultiply(acc, primary());
                    break;

                case DIV:
                    acc = new CheckedDivide(acc, primary());
                    break;

                default:
                    tokens.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression primary() throws ParsingException {
        Token token = tokens.next();
        TripleExpression primary;

        switch (token.getType()) {
            case END:
            case RIGHT_BR:
                if (tokens.isFirst()) {
                    throw new ParsingException("no pairing opening parenthesis for closing parenthesis", token.getIdx());
                }
                if (tokens.prev().getType()==TokenType.LEFT_BR) {
                    if (token.getType() == TokenType.RIGHT_BR) {
                        throw new ParsingException(String.format("empty parenthesis sequence at positions %d - %d", lastLeftBracket, token.getIdx()));
                    } else {
                        throw new ParsingException("no pairing closing parenthesis for opening parenthesis", lastLeftBracket);
                    }
                }
                throw new ParsingException("no last argument for operation " + tokens.current().getValue(), token.getIdx());

            case CONST:
                primary = CheckedConst.valueOf(token.getValue(), token.getIdx());
                break;

            case VARIABLE:
                primary = new Variable(token.getValue());
                break;

            case MINUS:
                if (tokens.hasNext() && tokens.next().getType() == TokenType.CONST) {
                    Token number = tokens.current();
                    primary = CheckedConst.valueOf("-" + number.getValue(), token.getIdx());
                } else {
                    tokens.prev();
                    primary = new CheckedNegate(primary());
                }
                break;

            case POW2:
                primary = new CheckedPow2(primary());
                break;

            case LOG2:
                primary = new CheckedLog2(primary());
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
                    throw new ParsingException("no first argument for operation " + token.getValue(), token.getIdx());
                }
                Token prev = tokens.prev();
                if (prev.getType() == TokenType.LEFT_BR || prev.getType() == TokenType.MINUS) {
                    throw new ParsingException("no first argument for operation " + token.getValue(), token.getIdx());
                }
                throw new ParsingException(String.format("no middle argument between operation %s " +
                                "at position %d and operation %s",
                        prev.getValue(), prev.getIdx() + 1, token.getValue()), token.getIdx());
        }

        return primary;
    }
}
