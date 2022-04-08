package kek;

import java.io.File;
import java.io.IOException;

public class Kek {
    private static final String USAGE = """
            Usage: kek <input-file> <output-file>""";

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            System.out.println(USAGE);
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        File cTranslatedFile = Translator.translate(new File(inputFilePath), System.out::println);
//        Compiler.compileToExecutable(cTranslatedFile, new File(outputFilePath));
    }
}
