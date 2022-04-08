package lab4.grammar;

public record Terminal(String tokenTypeName) implements Node {
    private static final Terminal EPS = new Terminal("_eps");
    private static final Terminal EOF = new Terminal("EOF");

    @Override
    public String toHumanReadable() {
        return tokenTypeName;
    }

    public static Terminal eps() {
        return EPS;
    }

    public static Terminal eof() {
        return EOF;
    }
}
