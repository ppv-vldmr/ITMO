package lab4.lexer;

import lab4.primarylex.PrimaryToken;
import lab4.util.AbstractFileFactory;
import lab4.util.Settings;

import java.util.List;
import java.util.function.Consumer;

public class LexerFactory extends AbstractFileFactory implements Consumer<List<PrimaryToken>> {
    private static final String[] templateNames = new String[] {
            "Tree.java.ftl",
            "Token.java.ftl",
            "TokenType.java.ftl",
            "Lexer.java.ftl"
    };

    public LexerFactory(Settings settings) {
        super(settings);
    }

    @Override
    public void accept(List<PrimaryToken> primaryTokens) {
        var map = settings().map();
        map.put("tokenTypes", primaryTokens);
        writeFiles(map);
    }

    @Override
    protected String[] getTemplateNames() {
        return templateNames;
    }
}
