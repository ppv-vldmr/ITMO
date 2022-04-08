package testLab2Var10;

import java.util.regex.Pattern;

public enum TokenType {
        TYPE_NAME("int|long\\s+long|void|char|float", false),
        POINTER("\\*", false),
        VALUE("(\\+|-)?(([1-9][0-9]*)|0)", false),
        NAME("[a-zA-Z_][a-zA-Z_0-9]*", false),
        LPAR("\\(", false),
        RPAR("\\)", false),
        COMMA(",", false),
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
