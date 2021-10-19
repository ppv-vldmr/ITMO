package token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Tokenizer {
    private final List<Token> tokens = new ArrayList<>();
    private int curr = -1;

    public void setTokens(String expr) {
        tokens.clear();
        curr = -1;
        tokenize(expr);
        tokens.add(new Token(TokenType.END, "end of expression"));
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

    private void tokenize(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
                continue;
            }
            if (s.charAt(i) == '.') {
                continue;
            }
            if (Character.isWhitespace(s.charAt(i))) {
                continue;
            }
            switch (s.charAt(i)) {
                case '(' :
                    tokens.add(new Token(TokenType.LEFT_BR, "("));
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RIGHT_BR, ")"));
                    break;
                case '&':
                    tokens.add(new Token(TokenType.CONJ, "&"));
                    break;
                case '|':
                    tokens.add(new Token(TokenType.DISJ, "|"));
                    break;
                case '!':
                    tokens.add(new Token(TokenType.NEG, "!"));
                    break;
                case '-':
                    tokens.add(new Token(TokenType.IMPL, "->"));
                    break;
                case '=':
                    tokens.add(new Token(TokenType.EQ, "="));
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MUL, "*"));
                    break;
                case '0':
                    tokens.add(new Token(TokenType.ZERO, "0"));
                    break;
                case '@':
                    tokens.add(new Token(TokenType.QUAN, "@"));
                    break;
                case '?':
                    tokens.add(new Token(TokenType.QUAN, "?"));
                    break;
                case '\'':
                    tokens.add(new Token(TokenType.INCREMENT, "'"));
                    break;
                default: {
                    int j = i;
                    while (j < s.length() && (s.charAt(j) == '0' || Character.isLetter(s.charAt(j)))) {
                        j++;
                    }
                    String name = s.substring(i, j);
                    if (name.toUpperCase().equals(name)) {
                        tokens.add(new Token(TokenType.PREDICATE, name));
                    } else {
                        tokens.add(new Token(TokenType.VARIABLE, name));
                    }
                    i = j - 1;
                }
            }
        }
    }
}
