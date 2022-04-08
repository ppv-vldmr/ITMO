import java.util.regex.Pattern;

public enum TokenType {
        LET("let", false),
        COLON(":", false),
        SEMICOLON(";", false),
        INT("int", false),
        BOOL("bool", false),
        CHAR("char", false),
        STRING("str", false),
        IMPORT("import", false),
        DEF("def", false),
        LPAR("(", false),
        RPAR(")", false),
        COMMA(",", false),
        DEFER("defer", false),
        LBRACE("{", false),
        RBRACE("}", false),
        BREAK("break", false),
        CONTINUE("continue", false),
        IF("if", false),
        ELSE("else", false),
        WHILE("while", false),
        ASSIGN("=", false),
        RETURN("return", false),
        EXTERN("extern", false),
        OR("or", false),
        OR_SIGN_BITWISE("|", false),
        OR_SIGN("||", false),
        AND("and", false),
        AND_SIGN_BITWISE("&", false),
        AND_SIGN("&&", false),
        NOT("not", false),
        NOT_SIGN("!", false),
        NOT_SIGN_BITWISE("~", false),
        PLUS_SIGN("+", false),
        MINUS_SIGN("-", false),
        MUL_SIGN("*", false),
        DIV("div", false),
        DIV_SIGN("/", false),
        MOD("mod", false),
        MOD_SIGN("%", false),
        EQUALS("==", false),
        NOT_EQUALS("!=", false),
        LBRACKET("[", false),
        RBRACKET("]", false),
        INDEX(".", false),
        FOR("for", false),
        INT_LITERAL("(-)?[0-9]+", false),
        BOOL_LITERAL("true|false", false),
        ID("[A-Za-z_][A-Za-z0-9_]*", false),
        EOLN("\\n+", false),
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
