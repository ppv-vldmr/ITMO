package kek;

import java.io.File;
import java.io.IOException;

public class Compiler {
    public static void compileToExecutable(File cInputFile, File exeOutputFile) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(
            "g++", "-static", "-o", exeOutputFile.getAbsolutePath(), cInputFile.getAbsolutePath()
        );
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (exitCode != 0)
            throw new RuntimeException();
    }
}
