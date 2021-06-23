package info.kgeorgiy.ja.popov.walk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Walk {

    public static void main(String[] args) {

        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.out.println("Wrong count of arguments. Expected: \"inputFileName\" \"outputFileName\"");
            return;
        }

        String inputFileName = args[0], outputFileName = args[1];
        StringBuilder sb = new StringBuilder();

        File input = new File(inputFileName), output = new File(outputFileName);

        // NOTE2: it's better have a good reason to override default arguments
        try (BufferedReader reader = new BufferedReader(new FileReader(input, StandardCharsets.UTF_8), 1024)) {

            String str;
            while ((str = reader.readLine()) != null) {
                long hash = 0;
                // NOTE2: you create new objects for every line
                PJW pjw = new PJW(new File(str), hash);
                hash = pjw.getHash();
                // NOTE2: you should not keep everything in-memory. print hashes online
                // NOTE2: "\n" is not cross-platform.
                sb.append(String.format("%016x %s%n", hash, str));
            }

        } catch (InvalidPathException e) { // NOTE: you don't use nio, no one throws this exception
            System.out.println("Invalid path input file: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Security exception with input file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Wrong file reading: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, StandardCharsets.UTF_8))) {
            if (Files.notExists(Path.of(output.getPath()))) {
                if (Path.of(output.getPath()) != null) {
                    Files.createDirectories(Path.of(output.getParent()));
                }
            }

            writer.write(sb.toString());

        } catch (InvalidPathException e) {
            System.out.println("Invalid path output file: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Security exception with output file: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Wrong encoding of output file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Wrong file writing: " + e.getMessage());
        }
    }

}
