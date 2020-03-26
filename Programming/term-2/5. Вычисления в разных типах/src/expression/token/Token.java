package expression.token;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Token {
    private TokenType type;
    private String value;
    private int idx;

    public Token(TokenType type, String value, int idx) {
        this.type = type;
        this.value = value;
        this.idx = idx;
    }

    public TokenType getType() {
        return type;
    }

    public int getIdx() {
        return idx;
    }

    public String getValue() {
        return value;
    }
}
