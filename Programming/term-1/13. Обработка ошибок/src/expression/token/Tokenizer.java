package expression.token;

import expression.exceptions.InvalidTokenException;
import expression.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Tokenizer {
    private List<Token> tokens = new ArrayList<>();
    private int curr = -1;
    private String expr;

    public Tokenizer(String expr) throws ParsingException {
        this.expr = expr;
        tokenize(expr);
        tokens.add(new Token(TokenType.END, "end of expression", expr.length()));
        if (tokens.size() == 1) {
            throw new ParsingException("empty expression");
        }
    }

    public boolean hasNext() {
        return curr < tokens.size() - 1;
    }

    public Token next() {
        return tokens.get(++curr);
    }

    public Token prev() {
        return tokens.get(--curr);
    }

    public Token current() {
        return tokens.get(curr);
    }

    public boolean isFirst() { return curr == 0; }

    private void tokenize(String s) throws InvalidTokenException {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                continue;
            }
            switch (s.charAt(i)) {
                case '(':
                    tokens.add(new Token(TokenType.LEFT_BR, "(", i));
                    break;

                case ')':
                    tokens.add(new Token(TokenType.RIGHT_BR, ")", i));
                    break;

                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", i));
                    break;

                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-", i));
                    break;

                case '*':
                    tokens.add(new Token(TokenType.MUL, "*", i));
                    break;

                case '/':
                    tokens.add(new Token(TokenType.DIV, "/", i));
                    break;

                case 'x':
                case 'y':
                case 'z':
                    tokens.add(new Token(TokenType.VARIABLE, String.valueOf(s.charAt(i)), i));
                    break;

                case 'l':
                    i = genToken("log2", TokenType.LOG2, i);
                    break;

                case 'p':
                    i = genToken("pow2",TokenType.POW2, i);
                    break;

                default:
                    if (!Character.isDigit(s.charAt(i))) {
                        throw new InvalidTokenException(s.charAt(i), i);
                    }
                    int j = i;
                    while (j < s.length() && Character.isDigit(s.charAt(j))) {
                        j++;
                    }
                    String number = s.substring(i, j);
                    tokens.add(new Token(TokenType.CONST, number, i));
                    i = j - 1;
            }
        }
    }

    private int genToken(String token, TokenType type, int i) throws InvalidTokenException {
        if (i + token.length() > expr.length()) {
            throw new InvalidTokenException(expr.substring(i), i);
        }
        if (expr.substring(i, i + token.length()).equals(token)) {
            int j = i + token.length();
            if (j < expr.length() && (Character.isLetter(expr.charAt(j)) ||
                    ((type == TokenType.POW2 || type == TokenType.LOG2) && !Character.isWhitespace(expr.charAt(j))))) {
                throw new InvalidTokenException(token + expr.charAt(j), i);
            }
            tokens.add(new Token(type, token, i));
            i += token.length() - 1;
            return i;
        } else {
            int j = i;
            while (j < expr.length() && !Character.isWhitespace(expr.charAt(j))) {
                j++;
            }
            throw new InvalidTokenException(expr.substring(i, j) + String.format(" (expected %s)",token), i);
        }
    }
}
