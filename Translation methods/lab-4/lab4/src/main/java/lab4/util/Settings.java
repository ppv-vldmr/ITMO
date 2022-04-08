package lab4.util;

import com.google.common.base.Strings;
import org.apache.commons.cli.*;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public record Settings(String targetCodeRootDir,
                       String targetPackage,
                       String inputGrammarFile) {
    private static final Options options = new Options();

    public File getTargetCodeRootDirFile() {
        return new File(targetCodeRootDir);
    }

    public Path getTargetCodeRootDirPath() {
        return getTargetCodeRootDirFile().toPath();
    }

    public File getTargetCodeDirFile() {
        return getTargetCodeDirPath().toFile();
    }

    public Path getTargetCodeDirPath() {
        return getTargetCodeRootDirPath().resolve(getPackageSubpath());
    }

    public File getInputGrammarFile() {
        return new File(inputGrammarFile);
    }

    public Path getInputGrammarFilePath() {
        return getInputGrammarFile().toPath();
    }

    public Map<String, Object> map() {
        return new HashMap<>(Map.of(
                "targetCodeRootDir", targetCodeRootDir,
                "package", targetPackage
        ));
    }

    private String getPackageSubpath() {
        return targetPackage.replaceAll("\\.", "/");
    }

    public static Settings fromCommandLine(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("java -jar lab4.jar", options);
            throw new RuntimeException(e);
        }

        return new Settings(
                cmd.getOptionValue("o"),
                Strings.nullToEmpty(cmd.getOptionValue("p")),
                cmd.getOptionValue("i")
        );
    }

    static {
        options.addRequiredOption("o", "output", true, "Target code root directory (usually 'java' dir in your project)");
        options.addRequiredOption("i", "input", true, "Input grammar file");
        options.addOption("p", "package", true, "Target java package");
    }
}
