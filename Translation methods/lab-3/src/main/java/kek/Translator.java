package kek;

import kek.grammar.KekLexer;
import kek.grammar.KekParser;
import kek.translation.KekContextManager;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codehaus.plexus.util.IOUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

public class Translator {
    private static final String[] RESOURCES = new String[] {
            "kek.h"
    };

    public static String translate(CharStream kekInput) {
        KekLexer kekLexer = new KekLexer(kekInput);
        KekParser kekParser = new KekParser(new CommonTokenStream(kekLexer));
        KekContextManager kekContextManager = new KekContextManager();
        kekParser.addParseListener(kekContextManager);
        KekParser.FileContext fileContext = kekParser.file(kekContextManager);
        return fileContext.kekFile.getCString();
    }

    public static File translate(File inputFile, Consumer<String> intermediateConsumer) throws IOException {
        CharStream charStream = CharStreams.fromPath(inputFile.toPath());
        String result = translate(charStream);

        intermediateConsumer.accept(result);

        Path dir = Files.createTempDirectory("compile");
        copyResources(dir);

        Path resultFilePath = Files.createTempFile(dir, "tmp", ".c");
        File resultFile = resultFilePath.toFile();
        try (FileWriter writer = new FileWriter(resultFile)) {
            writer.write(result);
        }
        return resultFile;
    }

    private static void copyResources(Path dir) throws IOException {
        for (String resource : RESOURCES) {
            InputStream resourceStream = Objects.requireNonNull(Translator.class.getResourceAsStream("/" + resource));
            Path resourcePath = Files.createFile(dir.resolve(resource));
            FileOutputStream resourceOutputStream = new FileOutputStream(resourcePath.toFile());
            IOUtil.copy(resourceStream, resourceOutputStream);
        }
    }
}
