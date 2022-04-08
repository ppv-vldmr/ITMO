package testLab4Calc;

import java.util.regex.Pattern;

public enum TokenType {
        FACT("\\?", false),
        PLUS("\\+", false),
        MINUS("-", false),
        DIV("/", false),
        MUL("\\*", false),
        LPAR("\\(", false),
        RPAR("\\)", false),
        NUM("(-)?[0-9]+(\\.[0-9]+)?", false),
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
