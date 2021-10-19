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

    public Token next() {
        return tokens.get(++curr);
    }

    public Token current() {
        return tokens.get(curr);
    }

    private void tokenize(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>') {
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
                default: {
                    int j = i;
                    while (j < s.length() && (Character.isDigit(s.charAt(j)) || Character.isLetter(s.charAt(j)) || s.charAt(j) == '\'')) {
                        j++;
                    }
                    String number = s.substring(i, j);
                    tokens.add(new Token(TokenType.VARIABLE, number));
                    i = j - 1;
                }
            }
        }
    }
}
