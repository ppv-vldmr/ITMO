import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class Lexer implements Supplier<Token> {
    private final CharSequence input;
    private final Map<TokenType, Matcher> matchers;
    private final int length;
    private int position;

    public Lexer(CharSequence input) {
        this.input = input;
        this.position = 0;
        this.length = input.length();
        matchers = new LinkedHashMap<>();
        for (TokenType tokenType : TokenType.values()) {
            matchers.put(tokenType, tokenType.pattern().matcher(input));
        }
    }

    @Override
    public Token get() {
        for (Map.Entry<TokenType, Matcher> tokenTypeAndPattern : matchers.entrySet()) {
            TokenType tokenType = tokenTypeAndPattern.getKey();
            Matcher matcher = tokenTypeAndPattern.getValue();

            if (matcher.region(position, length).lookingAt()) {
                int end = matcher.end();
                if (!tokenType.skip()) {
                    Token token = new Token(tokenType, input.subSequence(position, end).toString());
                    position = end;
                    return token;
                } else {
                    position = end;
                    return get();
                }
            }
        }

        throw new RuntimeException("Unknown token at pos " + position);
    }
}
