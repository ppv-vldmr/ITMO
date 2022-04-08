package testLab2Var12;

import java.util.regex.Pattern;

public enum TokenType {
        FOR("for", false),
        TYPE("int|long\\s+long", false),
        VAR("[a-zA-Z_][a-zA-Z_0-9]*", false),
        VALUE("(\\+|-)?(([1-9][0-9]*)|0)", false),
        COMPARE(">=|<=|>|<|!=|==", false),
        INC("\\+\\+|--", false),
        EQUALS("=", false),
        LPAR("\\(", false),
        RPAR("\\)", false),
        SEMICOLON(";", false),
        WS("[ \\n\\t\\r]+", true),
    EOF("$", false);

    private final Pattern pattern;
    private final boolean skip;

    TokenType(String pattern, boolean skip) {
        this.pattern = Pattern.compile(pattern);
        this.skip = skip;
    }

    public Pattern pattern() {
        return pattern;
    }

    public boolean skip() {
        return skip;
    }
}
