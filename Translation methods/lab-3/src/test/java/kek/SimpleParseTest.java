package kek;

import kek.grammar.KekLexer;
import kek.grammar.KekParser;
import kek.translation.KekContextManager;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codehaus.plexus.util.IOUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

public class SimpleParseTest {
    @Test
    public void testSimple() {
        tryParseSimple(1);
        tryParseSimple(2);
    }

    @Test
    public void testAnother() {
        tryParseAnother(1);
        tryParseAnother(2);
        tryParseAnother(3);
    }

    private void tryParseSimple(int n) {
        System.out.println("test: simple " + n +
                "\n====================================================================\n" +
                tryParse("simple/c" + n + ".kek") +
                "\n====================================================================\n");
    }

    private void tryParseAnother(int n) {
        System.out.println("test: another " + n +
                "\n====================================================================\n" +
                tryParse("simple/t" + n + ".txt") +
                "\n====================================================================\n");
    }

    private String tryParse(String name) {
        KekLexer kekLexer = new KekLexer(CharStreams.fromString(readFile(name)));
        KekParser kekParser = new KekParser(new CommonTokenStream(kekLexer));

        KekContextManager kekContextManager = new KekContextManager();
        kekParser.addParseListener(kekContextManager);

        KekParser.FileContext file = kekParser.file(kekContextManager);

        StringBuilder sb = new StringBuilder();
        file.kekFile.getC().forEach(line -> sb.append(line).append("\n"));

        if (file.exception != null)
            throw file.exception;
        return sb.toString();
    }

    private String readFile(String name) {
        try {
            return IOUtil.toString(Objects.requireNonNull(getClass().getResourceAsStream("/" + name)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
